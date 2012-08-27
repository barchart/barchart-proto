package com.barchart.translator.common.config2;

import com.typesafe.config.ConfigValue;

public class TranslationConfig {

	public TranslationConfig(ConfigValue configValue) {
		configValue.atKey("asd");
		configValue.unwrapped();
	}

	
	
}
