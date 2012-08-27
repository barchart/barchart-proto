//package com.barchart.translator.cme;
//
//import java.io.File;
//import java.util.List;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.osgi.service.component.annotations.Activate;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Deactivate;
//import org.osgi.service.component.annotations.Property;
//import org.osgi.service.component.annotations.Reference;
//import org.osgi.service.event.Event;
//import org.osgi.service.event.EventConstants;
//import org.osgi.service.event.EventHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.barchart.conf.event.ConfigEvent;
//import com.barchart.conf.repo.api.ConfigService;
//import com.barchart.conf.sync.api.ConfigManager;
//import com.barchart.translator.cme.fest.FESTMessageDecoder;
//import com.barchart.translator.cme.symbols.FakeCMEGUIDLookup;
//import com.cme.fest.protocol.fast.DecoderStrategy;
//import com.cme.fest.protocol.fast.DefaultTemplateManager;
//import com.cme.fest.protocol.fast.FastTemplateIdDecoderStrategy;
//import com.cme.fest.protocol.fast.TemplateManager;
//import com.cme.fest.protocol.fast.transcoder.template.Template;
//import com.cme.fest.protocol.fast.transcoder.template.builder.TemplateBuilder;
//import com.cme.fest.protocol.fast.transcoder.template.builder.TemplateParsingException;
//import com.typesafe.config.Config;
//
//@Component(immediate = true)
//public class CMEStart implements EventHandler { 
//
//	private static final Logger logger = LoggerFactory.getLogger(CMEStart.class);
//
//	@Property(name = EventConstants.EVENT_TOPIC)
//	static final String TOPIC = ConfigEvent.CONFIG_CHANGE;
//
//	private ConfigManager configManager;
//
//	@Activate
//	protected void activate() {
//		logger.info("Activate CME");
//		setup();
//	}
//
//	@Deactivate
//	protected void deactivate() {
//		logger.info("Deactivate CME");
//	}
//
//	@Reference
//	public void bind(ConfigManager configManager) {
//		logger.info("Got config manager");
//		this.configManager = configManager;
//	}
//	
//	public void unbind(ConfigManager configManager) {
//		this.configManager = null;
//	}
//	
//	
//	
//	@Override
//	public void handleEvent(final Event event) {
//		logger.info("Configuration changed.  Setting up CME Translator.");
//		setup();
//	}
//	
//	private void setup() {
//		Config config = configManager.getConfig();
//		logger.info("Got config: " + config);
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
//
//
//
//}
