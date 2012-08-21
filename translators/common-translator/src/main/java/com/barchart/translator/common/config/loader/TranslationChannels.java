package com.barchart.translator.common.config.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.barchart.translator.common.config.ChannelConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public final class TranslationChannels {

	private final ImmutableMap<Integer, ChannelConfig> channelConfigMap;

	public TranslationChannels(List<ChannelConfig> channelConfigs) {
		this.channelConfigMap = ImmutableMap.copyOf(mapify(channelConfigs));
	}

	private Map<Integer, ChannelConfig> mapify(List<ChannelConfig> channelConfigs) {
		Map<Integer, ChannelConfig> map = new HashMap<Integer, ChannelConfig>();
		for (ChannelConfig channelConfig : channelConfigs) {
			if (map.get(channelConfig.getChannelID()) != null) {
				throw new IllegalStateException("ChannelConfig's channelID is duplicated: " + channelConfig.getChannelID());
			}
			map.put(channelConfig.getChannelID(), channelConfig);
		}
		return map;
	}

	public ChannelConfig getChannelConfig(int channelID) {
		return channelConfigMap.get(channelID);
	}

	public Collection<ChannelConfig> getAll() {
		return channelConfigMap.values();
	}

	public static TranslationChannels load(File file) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<ChannelConfig> channelConfigs = new ArrayList<ChannelConfig>();
			FileInputStream fileInputStream = new FileInputStream(file);
			JSONMapping mapping = objectMapper.readValue(fileInputStream, JSONMapping.class);
			for (JSONChannelMapping channelMapping : mapping.channelMappingList) {
				channelConfigs.add(new ConcreteChannelConfig(channelMapping));
			}
			return new TranslationChannels(channelConfigs);
		} catch (JsonParseException e) {
			throw new RuntimeException("Error while processing file: " + file, e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("Error while processing file: " + file, e);
		} catch (IOException e) {
			throw new RuntimeException("Error while processing file: " + file, e);
		}

	}

	private static final class JSONMapping {
		
		@JsonProperty(value = "description")
		private String description;
		
		@JsonProperty(value = "channels")
		private List<JSONChannelMapping> channelMappingList;
	}

	private static final class JSONChannelMapping {

		@JsonProperty(value = "channel")
		private int channelID;

		@JsonProperty(value = "sources")
		private List<String> sources;

		@JsonProperty(value = "destinations")
		private List<String> destinations;

	}

	private static final class ConcreteChannelConfig implements ChannelConfig {

		private final int channelID;
		private final ImmutableList<String> sources;
		private final ImmutableList<String> destinations;

		public ConcreteChannelConfig(JSONChannelMapping channelMapping) {
			this.channelID = channelMapping.channelID;
			this.sources = ImmutableList.copyOf(channelMapping.sources);
			this.destinations = ImmutableList.copyOf(channelMapping.destinations);
		}

		@Override
		public int getChannelID() {
			return channelID;
		}

		@Override
		public List<String> getSources() {
			return sources;
		}

		@Override
		public List<String> getDestinations() {
			return destinations;
		}

		@Override
		public String toString() {
			return "ConcreteChannelConfig [channelID=" + channelID + ", sources=" + sources + ", destinations=" + destinations + "]";
		}

	}
}
