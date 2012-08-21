package com.barchart.feed.cme.buf.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.common.config.ChannelConfig;
import com.barchart.translator.common.config.NetworkPoint;
import com.barchart.translator.common.config.NetworkPointConfig;
import com.barchart.translator.common.config.NetworkPoints;
import com.barchart.translator.common.config.loader.ConfigLoader;
import com.barchart.translator.common.config.loader.NetworkConfig;
import com.barchart.translator.common.config.loader.TranslationChannels;

public class ConfigLoaderTest {

	private static final Logger logger = LoggerFactory.getLogger(ConfigLoaderTest.class);
	
	@Ignore
	@Test
	public void testLoader() throws JsonParseException, JsonMappingException, IOException {
		ConfigLoader loader = new ConfigLoader();
		InputStream inputStream = new FileInputStream("src/main/resources/network.json");
		NetworkPoints networkPoints = loader.loadNetworkPoints(inputStream);
		NetworkPoint networkPoint = networkPoints.getNetworkPoint("cme1");
		logger.info(networkPoint.toString());
		
	}
	
	@Ignore
	@Test
	public void testNetworkConfig() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("src/main/resources/config/network/TestNetworkConfig.json");
		NetworkConfig networkConfig = NetworkConfig.load(file);
		Collection<NetworkPointConfig> all = networkConfig.getAll();
		for (NetworkPointConfig config : all) {
			logger.info(config.toString());
		}
	}
	
	@Ignore
	@Test
	public void testChannelConfigLoader() {
		File file = new File("src/main/resources/config/channels/CMEChannels.json");
		TranslationChannels translationChannels = TranslationChannels.load(file);
		Collection<ChannelConfig> all = translationChannels.getAll();
		for (ChannelConfig config : all) {
			logger.info(config.toString());
		}
		
	}
}
