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
//import org.osgi.service.component.annotations.Reference;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.barchart.translator.cme.fest.FESTMessageDecoder;
//import com.barchart.translator.cme.symbols.FakeCMEGUIDLookup;
//import com.barchart.translator.common.TranslatorFactory;
//import com.barchart.translator.common.api.TranslatorService;
//import com.barchart.translator.common.config2.TranslationConfig;
//import com.barchart.translator.common.config2.TranslatorConfig;
//import com.cme.fest.protocol.fast.DecoderStrategy;
//import com.cme.fest.protocol.fast.DefaultTemplateManager;
//import com.cme.fest.protocol.fast.FastTemplateIdDecoderStrategy;
//import com.cme.fest.protocol.fast.TemplateManager;
//import com.cme.fest.protocol.fast.transcoder.template.Template;
//import com.cme.fest.protocol.fast.transcoder.template.builder.TemplateBuilder;
//import com.cme.fest.protocol.fast.transcoder.template.builder.TemplateParsingException;
//
//
//@Component(immediate = true)
//public class CMEFactoryService {
//
//	private static final String TRANSLATOR_NAME = "CME";
//
//	private static final Logger logger = LoggerFactory.getLogger(CMEFactoryService.class);
//
//	private TranslatorService translatorService;
//
//	
//	@Activate
//	protected void activate() {
//		logger.info("Activate CME");
//		TranslatorConfig config = translatorService.getConfig();
//		TranslationConfig translatorConfig = config.getTranslatorConfig(TRANSLATOR_NAME);
//		TranslatorFactory factory = createTranslatorFactory(null);
//		translatorService.setTranslatorFactory(TRANSLATOR_NAME, factory);
//	}
//
//	@Deactivate
//	protected void deactivate() {
//		logger.info("Deactivate CME");
//	}
//
//	@Reference
//	public void bind(TranslatorService translatorService) {
//		this.translatorService = translatorService;
//		
//	}
//	
//	public void unbind(TranslatorService translatorService) {
//		this.translatorService = null;
//	}
//	
//	
//	private CMETranslatorFactory createTranslatorFactory(File templateFile) {
//		FESTMessageDecoder festMessageDecoder = new FESTMessageDecoder(getDecoderStategy(templateFile));
//		CMETranslatorFactory cmeTranslatorFactory = new CMETranslatorFactory(new FakeCMEGUIDLookup(), festMessageDecoder );
//		return cmeTranslatorFactory;
//	}
//	
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
//}
