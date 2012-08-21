package com.barchart.translator.common.config.configurator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.oio.OioDatagramChannelFactory;

import com.barchart.translator.common.config.loader.NetworkConfig;
import com.barchart.translator.common.config.loader.TranslationChannels;

public class NetworkConfigurator {

	private final Map<String, Channel> destinationChannelMap;

	private final Map<String, Channel> sourceChannelMap;
	
	private final OioDatagramChannelFactory channelFactory;
	
	public NetworkConfigurator(Executor threadPool, NetworkConfig networkConfig, TranslationChannels translationChannels) {
		this.channelFactory = new OioDatagramChannelFactory(threadPool);
		this.destinationChannelMap = new HashMap<String, Channel>();
		this.sourceChannelMap = new HashMap<String, Channel>();
	}
	
//	public void doSomething(TranslationConfig config) {
//		List<String> sources = config.getSources();
//		List<String> destinations = config.getDestinations();
//		Collection<Channel> destinationChannels = findDestinations(destinations);
//		String codec = config.getCodec();
//		
//	}
//	
//	private Collection<Channel> findSources(List<String> sources) {
//		List<Channel> sourceChannels = new ArrayList<Channel>();
//		for (String destination : sources) {
//			Channel channel = destinationChannelMap.get(destination);
//			if (channel == null) {
//				channel = createSourceChannel(destination);
//			}
//			sourceChannels.add(channel);
//		}
//		return sourceChannels;
//	}
//
//
//	private Collection<Channel> findDestinations(List<String> destinations) {
//		List<Channel> destinationChannels = new ArrayList<Channel>();
//		for (String destination : destinations) {
//			Channel channel = destinationChannelMap.get(destination);
//			if (channel == null) {
//				channel = createDestinationChannel(destination);
//			}
//			destinationChannels.add(channel);
//		}
//		return destinationChannels;
//	}
//
//	private Channel createDestinationChannel(String destination) {
//		ConnectionlessBootstrap b = new ConnectionlessBootstrap(channelFactory);
//		ChannelPipeline pipeline = b.getPipeline();
//
//		pipeline.addLast("upstream", new SimpleChannelUpstreamHandler());
//		
//		// Encoders
//		pipeline.addLast("protobufEncoder", new BUFPacketEncoder());
//		ChannelFuture connect = b.connect(new InetSocketAddress(InetAddress.getByName("230.1.61.111"), 20111));
//		return connect.getChannel();
//	}
//	
//	private Channel createSourceChannel(String destination) {
//		ConnectionlessBootstrap b = new ConnectionlessBootstrap(channelFactory);
//		ChannelPipeline pipeline = b.getPipeline();
//		pipeline.addLast("Translator", new TranslationHandler(new CMETranslator()));
//		pipeline.addLast("Endpoint", new EndpointHandler(outputChannel));
//		
//		DatagramChannel datagramChannel = (DatagramChannel) b.bind(new InetSocketAddress(20004));
//		InetSocketAddress multicastAddress = new InetSocketAddress("233.156.208.4", 20004 );
//		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getByName("10.222.4.88"));
//		datagramChannel.joinGroup(multicastAddress, networkInterface);
//	}

	
}
