package com.barchart.translator.common.config;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.barchart.translator.common.config2.NetworkPoint;
import com.barchart.translator.common.config2.TranslatorConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigTest {

	private static final String DEV_CONFIG_FILE = "config/translators.dev.conf";
	private static TranslatorConfig translatorConfig;

	@BeforeClass
	public static void loadConfig() {
		Config hoconConfig = ConfigFactory.parseResources(DEV_CONFIG_FILE);
		translatorConfig = new TranslatorConfig(hoconConfig);
	}
	
	
	@Test
	public void testName() {
		assertEquals("Translator Config (Joel-Dev)", translatorConfig.getName());
		
	}
	
	@Test
	public void testExternalNetworks() {
		List<NetworkPoint> networks = translatorConfig.getExternalNetworks();
		assertEquals(4, networks.size());
	}
	
	@Test
	public void testInternalNetworks() {
		List<NetworkPoint> networks = translatorConfig.getInternalNetworks();
		assertEquals(1, networks.size());
	}
	
	@Test
	public void testTranslators() {
		translatorConfig.getTranslators();
	}
}
