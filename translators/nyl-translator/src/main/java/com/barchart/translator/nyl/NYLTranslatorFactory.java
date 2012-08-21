package com.barchart.translator.nyl;

import com.barchart.translator.common.Translator;
import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.config.TranslationChannel;
import com.barchart.translator.nyl.config.NYLTranslatorConfig;

public class NYLTranslatorFactory extends TranslatorFactory {

	private final NYLTranslatorConfig config;

	public NYLTranslatorFactory(NYLTranslatorConfig config) {
		this.config = config;
	}

	@Override
	public Translator createTranslator(TranslationChannel translationChannel) {
		return new NYLTranslator(translationChannel.getChannelID());
	}
	
	
	
}
