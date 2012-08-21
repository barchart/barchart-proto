package com.barchart.translator.common;

import com.barchart.translator.common.config.TranslationChannel;

public abstract class TranslatorFactory {

	public abstract Translator createTranslator(TranslationChannel translationChannel);
	
}
