package com.barchart.translator.cfn;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.common.Translator;
import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.config.TranslationChannel;

@Component(immediate = true)
public class CFNTranslatorFactory implements TranslatorFactory {

	private static final Logger logger = LoggerFactory.getLogger(CFNTranslatorFactory.class);
	
	private static final String TRANSLATOR_NAME = "CFN";
	
	@Override
	public Translator createTranslator(TranslationChannel translationChannel) {
		return new CFNTranslator();
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
