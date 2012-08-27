//package com.barchart.translator.cme;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.osgi.framework.BundleActivator;
//import org.osgi.framework.BundleContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.barchart.translator.cme.fest.FESTMessageDecoder;
//import com.barchart.translator.cme.symbols.FakeCMEGUIDLookup;
//import com.barchart.translator.common.app.TranslatorApp;
//import com.barchart.translator.common.config.loader.NetworkConfig;
//import com.barchart.translator.common.config.loader.TranslationChannels;
//import com.cme.fest.protocol.fast.DecoderStrategy;
//import com.cme.fest.protocol.fast.DefaultTemplateManager;
//import com.cme.fest.protocol.fast.FastTemplateIdDecoderStrategy;
//import com.cme.fest.protocol.fast.TemplateManager;
//import com.cme.fest.protocol.fast.transcoder.template.Template;
//import com.cme.fest.protocol.fast.transcoder.template.builder.TemplateBuilder;
//import com.cme.fest.protocol.fast.transcoder.template.builder.TemplateParsingException;
//
//public class CMEActivator implements BundleActivator {
//
//	private static final Logger logger = LoggerFactory.getLogger(CMEActivator.class);
//	private TranslatorApp app;
//	
//	public CMEActivator() {
//		
//	}
//	
//	@Override
//	public void start(BundleContext context) throws Exception {
//		logger.info("Staring CME Buf");
////		File networkFile = context.getDataFile("TestNetworkConfig.json");
////		File channelsFile = context.getDataFile("CMEChannels.json");
////		File templateFile = context.getDataFile("templates.xml");
////		ExecutorService threadPool = Executors.newCachedThreadPool();
////		NetworkConfig networkConfig = NetworkConfig.load(networkFile);
////		TranslationChannels translationChannels = TranslationChannels.load(channelsFile);
////		runApp(threadPool, networkConfig, translationChannels, templateFile);
//	}
//
//	private CMETranslatorFactory createTranslatorFactory(File templateFile) {
//		FESTMessageDecoder festMessageDecoder = new FESTMessageDecoder(getDecoderStategy(templateFile));
//		CMETranslatorFactory cmeTranslatorFactory = new CMETranslatorFactory(new FakeCMEGUIDLookup(), festMessageDecoder );
//		return cmeTranslatorFactory;
//	}
//	
//	private DecoderStrategy getDecoderStategy(File templateFile) {
//		try {
//			TemplateManager templateManager = getTemplateManager(templateFile);
//			FastTemplateIdDecoderStrategy strategy = new FastTemplateIdDecoderStrategy(templateManager);
//			return strategy;
//		} catch (DocumentException e) {
//			throw new RuntimeException("Could not load create decoder strategy", e);
//		} catch (TemplateParsingException e) {
//			throw new RuntimeException("Could not load create decoder strategy", e);
//		}
//	}
//
//	private TemplateManager getTemplateManager(File templateFile) throws DocumentException, TemplateParsingException {
//		SAXReader sax = new SAXReader();
//		Document document = sax.read(templateFile);
//		Element xmlTemplates = document.getRootElement();
//		TemplateBuilder templateBuilder = new TemplateBuilder();
//		List<Template> templateList = templateBuilder.createTemplates(xmlTemplates);
//		for (Template t : templateList) {
//			logger.info("Loaded template: " + t.toString());
//		}
//		TemplateManager templateManager = new DefaultTemplateManager(templateList);
//		return templateManager;
//	}
//	
//	@Override
//	public void stop(BundleContext context) throws Exception {
//		logger.info("Stopping CME Buf");
//		app.stop();
//	}
//
//	public void runApp(ExecutorService threadPool, NetworkConfig networkConfig, TranslationChannels translationChannels, File templateFile) throws UnknownHostException, SocketException {
//		CMETranslatorFactory translatorFactory = createTranslatorFactory(templateFile);
//		this.app = new TranslatorApp(threadPool, networkConfig, translationChannels, translatorFactory);
//		app.start();		
//	}
//	
//	
//	public static void main(String[] args) throws IOException {
//		CMEActivator cmeActivator = new CMEActivator();
//		ExecutorService threadPool = Executors.newCachedThreadPool();
//		NetworkConfig networkConfig = NetworkConfig.load(new File("src/main/resources/config/network/TestNetworkConfig.json"));
//		TranslationChannels translationChannels = TranslationChannels.load(new File("src/main/resources/config/channels/CMEChannels.json"));
//		File templateFile = new File("src/main/resources/templates.xml");
//		cmeActivator.runApp(threadPool, networkConfig, translationChannels, templateFile);
//	}
//
//}
