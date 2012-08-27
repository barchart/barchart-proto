package com.barchart.translator.common;

import org.osgi.service.component.annotations.Component;

import com.barchart.translator.common.config.TranslationChannel;

@Component(immediate = true)
public interface TranslatorFactory {

	public Translator createTranslator(TranslationChannel translationChannel);
	
}
