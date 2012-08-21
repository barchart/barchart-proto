package com.barchart.translator.cme;

import com.barchart.translator.cme.messaging.CMEMessageDecoder;
import com.barchart.translator.cme.symbols.CMEGUIDLookup;
import com.barchart.translator.common.TranslatorFactory;
import com.barchart.translator.common.Translator;
import com.barchart.translator.common.config.TranslationChannel;

public class CMETranslatorFactory extends TranslatorFactory {

	private final CMEGUIDLookup guidLookup;
	private final CMEMessageDecoder decoder;

	public CMETranslatorFactory(CMEGUIDLookup guidLookup, CMEMessageDecoder decoder) {
		this.decoder = decoder;
		this.guidLookup = guidLookup;
	}
	
	@Override
	public Translator createTranslator(TranslationChannel translationChannel) {
		return new CMETranslator(guidLookup, decoder, translationChannel.getChannelID());
	}

}
