package com.barchart.translator.ice;

import com.barchart.translator.common.Translator;
import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.config.TranslationChannel;

public class ICETranslatorFactory implements TranslatorFactory {

	private static final String TRANSLATOR_NAME = "ICE";
	
	@Override
	public Translator createTranslator(TranslationChannel translationChannel) {
		return new ICETranslator();
	}


}
