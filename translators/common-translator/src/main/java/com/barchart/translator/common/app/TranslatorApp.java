package com.barchart.translator.common.app;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.jboss.netty.channel.socket.oio.OioDatagramChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.common.Translator;
import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.channelhandlers.BUFPacketEncoder;
import com.barchart.translator.common.channelhandlers.EndpointHandler;
import com.barchart.translator.common.channelhandlers.TranslationHandler;
import com.barchart.translator.common.config.ChannelConfig;
import com.barchart.translator.common.config.NetworkPointConfig;
import com.barchart.translator.common.config.TranslationChannel;
import com.barchart.translator.common.config.loader.NetworkConfig;
import com.barchart.translator.common.config.loader.TranslationChannels;

public class TranslatorApp {

	private static final Logger logger = LoggerFactory.getLogger(TranslatorApp.class);

	private final OioDatagramChannelFactory channelFactory;
	private final NetworkConfig networkConfig;
	private final TranslationChannels translationChannels;

	private final TranslatorFactory translatorFactory;

	public TranslatorApp(Executor threadPool, NetworkConfig networkConfig, TranslationChannels translationChannels, TranslatorFactory translatorFactory)
			throws UnknownHostException, SocketException {
		this.channelFactory = new OioDatagramChannelFactory(threadPool);
		this.networkConfig = networkConfig;
		this.translationChannels = translationChannels;
		this.translatorFactory = translatorFactory;

	}

	public void start() {
		for (ChannelConfig channelConfig : translationChannels.getAll()) {
			startChannelConfig(channelConfig);
		}
	}

	private void startChannelConfig(ChannelConfig channelConfig) {
		Collection<NetworkPointConfig> sources = networkConfig.get(channelConfig.getSources());
		Collection<NetworkPointConfig> destinationConfigs = networkConfig.get(channelConfig.getDestinations());
		Collection<Channel> destinationChannels = makeDestinationChannels(destinationConfigs);

		TranslationChannel translationChannel = new TranslationChannel(channelConfig.getChannelID(), sources, destinationConfigs);
		Translator cmeTranslator = translatorFactory.createTranslator(translationChannel);

		ConnectionlessBootstrap bootstrap = createBootstrap(cmeTranslator, destinationChannels);

		for (NetworkPointConfig sourceConfig : sources) {
			logger.info("Starting pipeline.  Source: " + sourceConfig + ", destinations: " + destinationConfigs);
			startPipeline(bootstrap, sourceConfig);
		}

	}

	private ConnectionlessBootstrap createBootstrap(Translator translator, Collection<Channel> destinationChannels) {
		ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap(channelFactory);
		ChannelPipeline pipeline = bootstrap.getPipeline();
		pipeline.addLast("Translator", new TranslationHandler(translator));
		pipeline.addLast("Endpoint", new EndpointHandler(destinationChannels));
		return bootstrap;
	}

	private void startPipeline(ConnectionlessBootstrap bootstrap, NetworkPointConfig sourceConfig) {
		try {
			// DatagramChannel datagramChannel = (DatagramChannel)
			// bootstrap.bind();
			DatagramChannel datagramChannel = (DatagramChannel) bootstrap.bind(new InetSocketAddress(20004));
			InetSocketAddress multicastAddress = sourceConfig.resolveAddress();
			NetworkInterface networkInterface = sourceConfig.resolveBindAddress();
			datagramChannel.joinGroup(multicastAddress, networkInterface);
		} catch (SocketException e) {
			throw new RuntimeException("Error while starting pipeline: " + sourceConfig, e);
		}
	}

	private Collection<Channel> makeDestinationChannels(Collection<NetworkPointConfig> destinationConfigs) {
		List<Channel> destinationChannels = new ArrayList<Channel>();
		for (NetworkPointConfig config : destinationConfigs) {
			Channel outputChannel = makeOutputChannel(config);
			destinationChannels.add(outputChannel);
		}
		return destinationChannels;
	}

	private Channel makeOutputChannel(NetworkPointConfig outputNetwork) {
		ConnectionlessBootstrap b = new ConnectionlessBootstrap(channelFactory);
		ChannelPipeline pipeline = b.getPipeline();
		pipeline.addLast("upstream", new SimpleChannelUpstreamHandler());
		// Encoders
		pipeline.addLast("protobufEncoder", new BUFPacketEncoder());
		SocketAddress remoteAddress = outputNetwork.resolveAddress();
		ChannelFuture connect = b.connect(remoteAddress);
		return connect.getChannel();
	}

	public void stop() {

	}

}
