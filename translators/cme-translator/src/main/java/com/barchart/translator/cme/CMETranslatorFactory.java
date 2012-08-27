package com.barchart.translator.cme;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.cme.messaging.CMEMessageDecoder;
import com.barchart.translator.cme.symbols.CMEGUIDLookup;
import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.Translator;
import com.barchart.translator.common.config.TranslationChannel;

@Component(immediate = true)
public class CMETranslatorFactory implements TranslatorFactory {

	private static final Logger logger = LoggerFactory.getLogger(CMETranslatorFactory.class);

	private static final String TRANSLATOR_NAME = "CME";
	
//	private final CMEGUIDLookup guidLookup;
	
//	private final CMEMessageDecoder decoder;

//	public CMETranslatorFactory(CMEGUIDLookup guidLookup, CMEMessageDecoder decoder) {
//		this.decoder = decoder;
//		this.guidLookup = guidLookup;
//	}
	
	

	@Override
	public Translator createTranslator(TranslationChannel translationChannel) {
//		return new CMETranslator(guidLookup, decoder, translationChannel.getChannelID());
		return null;
	}

	@Activate
	protected void activate() {
		logger.info("Activate CME Factory");
	}

	@Deactivate
	protected void deactivate() {
		logger.info("Deactivate CME Factory");
	}

}
