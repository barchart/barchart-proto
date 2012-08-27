package com.barchart.translator.nyl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.common.Translator;
import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.config.TranslationChannel;

@Component(immediate = true)
public class NYLTranslatorFactory implements TranslatorFactory {

	private static final Logger logger = LoggerFactory.getLogger(NYLTranslatorFactory.class);
	
	private static final String TRANSLATOR_NAME = "NYL";
	

	public NYLTranslatorFactory() {
	}

	@Override
	public Translator createTranslator(TranslationChannel translationChannel) {
		return new NYLTranslatorXForm(translationChannel.getChannelID());
	}
	
	@Activate
	protected void activate() {
		logger.info("Activate NYL Factory");
	}

	@Deactivate
	protected void deactivate() {
		logger.info("Deactivate NYL Factory");
	}

	
	
}
