//package com.barchart.translator.nyl;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.osgi.framework.BundleActivator;
//import org.osgi.framework.BundleContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.barchart.translator.common.app.TranslatorApp;
//import com.barchart.translator.common.config.loader.NetworkConfig;
//import com.barchart.translator.common.config.loader.TranslationChannels;
//
//public class NYLActivator implements BundleActivator {
//
//	private static final Logger logger = LoggerFactory.getLogger(NYLActivator.class);
//	private TranslatorApp app;
//
//	@Override
//	public void start(BundleContext context) throws Exception {
//		logger.info("Starting NYL Translator");
//		File networkFile = context.getDataFile("TestNetworkConfig.json");
//		File channelsFile = context.getDataFile("CMEChannels.json");
//		ExecutorService threadPool = Executors.newCachedThreadPool();
//		NetworkConfig networkConfig = NetworkConfig.load(networkFile);
//		TranslationChannels translationChannels = TranslationChannels.load(channelsFile);
//		runApp(threadPool, networkConfig, translationChannels);
//	}
//
//	public void runApp(ExecutorService threadPool, NetworkConfig networkConfig, TranslationChannels translationChannels) throws UnknownHostException,
//			SocketException {
//		NYLTranslatorFactory translatorFactory = new NYLTranslatorFactory(null);
//		this.app = new TranslatorApp(threadPool, networkConfig, translationChannels, translatorFactory);
//		app.start();
//	}
//
//	@Override
//	public void stop(BundleContext context) throws Exception {
//		logger.info("Stopping NYL Translator");
//	}
//
//	public static void main(String[] args) throws IOException {
//		logger.info("Starting NYL Translator from main()");
//		ExecutorService threadPool = Executors.newCachedThreadPool();
//		NetworkConfig networkConfig = NetworkConfig.load(new File("src/main/resources/config/network/TestNetworkConfig.json"));
//		TranslationChannels translationChannels = TranslationChannels.load(new File("src/main/resources/config/channels/CMEChannels.json"));
//		NYLActivator activator = new NYLActivator();
//		activator.runApp(threadPool, networkConfig, translationChannels);
//	}
//}
