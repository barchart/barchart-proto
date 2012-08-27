package com.barchart.translator.common.api;

import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.config2.TranslatorConfig;

public interface TranslatorService {

	public TranslatorConfig getConfig();

	public void setTranslatorFactory(String translatorName, TranslatorFactory factory);

	
	
}
