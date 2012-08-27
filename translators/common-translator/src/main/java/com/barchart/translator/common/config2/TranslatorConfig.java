package com.barchart.translator.common.config2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;

public final class TranslatorConfig {

	private static final String NAME = "name";

	private static final String EXTERNAL_NETWORKS = "externalNetworks";

	private static final String INTERNAL_NETWORKS = "internalNetworks";

	private static final String TRANSLATORS = "translators";
	
	private Config hoconConfig;

	public TranslatorConfig(Config hoconConfig) {
		this.hoconConfig = hoconConfig;
	}

	public String getName() {
		return hoconConfig.getString(NAME);
	}
	
	public List<NetworkPoint> getExternalNetworks() {
		List<? extends Config> configList = hoconConfig.getConfigList(EXTERNAL_NETWORKS);
		return makeNetworkPointList(configList);
	}
	
	public List<NetworkPoint> getInternalNetworks() {
		List<? extends Config> configList = hoconConfig.getConfigList(INTERNAL_NETWORKS);
		return makeNetworkPointList(configList);
	}
	
	private List<NetworkPoint> makeNetworkPointList(List<? extends Config> configs) {
		List<NetworkPoint> points = new ArrayList<NetworkPoint>();
		for (Config networkConfig : configs) {
			points.add(new NetworkPoint(networkConfig));
		}
		return points;
	}

	public Map<String, TranslationConfig> getTranslators() {
		ConfigObject translators = hoconConfig.getObject(TRANSLATORS);

		Config config = hoconConfig.getConfig(TRANSLATORS);
		
		Set<Entry<String, ConfigValue>> entrySet = config.entrySet();
		
		Map<String, Object> unwrapped = translators.unwrapped();
		
		System.out.println(unwrapped);
		
		Map<String, TranslationConfig> map = new HashMap<String, TranslationConfig>();
		for (String key : unwrapped.keySet()) {
			ConfigValue configValue = translators.get(key);
			map.put(key, new TranslationConfig(translators.get(key)));
		}
		
		return map;
	}

	public TranslationConfig getTranslatorConfig(String translatorName) {
		return null;
	}
	
}
