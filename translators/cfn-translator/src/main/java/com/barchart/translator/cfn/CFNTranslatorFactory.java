package com.barchart.translator.cfn;

import com.barchart.translator.common.Translator;
import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.config.TranslationChannel;

public class CFNTranslatorFactory extends TranslatorFactory {

	@Override
	public Translator createTranslator(TranslationChannel translationChannel) {
		return new CFNTranslator();
	}

}
