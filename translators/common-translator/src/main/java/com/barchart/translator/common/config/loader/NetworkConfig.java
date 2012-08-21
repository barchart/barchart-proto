package com.barchart.translator.common.config.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.barchart.translator.common.config.NetworkPointConfig;
import com.google.common.collect.ImmutableMap;

public class NetworkConfig {

	private final ImmutableMap<String, NetworkPointConfig> networkPointConfigMap;

	public NetworkConfig(List<NetworkPointConfig> allConfigs) {
		this.networkPointConfigMap = ImmutableMap.copyOf(mapify(allConfigs));
	}

	private Map<String, NetworkPointConfig> mapify(List<NetworkPointConfig> allConfigs) {
		Map<String, NetworkPointConfig> map = new HashMap<String, NetworkPointConfig>();
		for (NetworkPointConfig config : allConfigs) {
			if (map.get(config.getID()) != null) {
				throw new IllegalStateException("NetworkPointConfig ID is duplicated: " + config.getID());
			}
			map.put(config.getID(), config);
		}
		return map;
	}

	public NetworkPointConfig getByID(String id) {
		return networkPointConfigMap.get(id);
	}

	public Collection<NetworkPointConfig> getAll() {
		return networkPointConfigMap.values();
	}
	
	public Collection<NetworkPointConfig> get(Iterable<String> ids) {
		List<NetworkPointConfig> result = new ArrayList<NetworkPointConfig>();
		for (String id : ids) {
			NetworkPointConfig networkPointConfig = networkPointConfigMap.get(id);
			if (networkPointConfig != null) {
				result.add(networkPointConfig);
			} else {
				throw new IllegalStateException("Destination network is unknown: " + id);
			}
		}
		return result;
	}
	
	
	public static NetworkConfig load(File file) throws JsonParseException, JsonMappingException, IOException {
		Loader loader = new Loader(file);
		return loader.load();
	}

	private static final class Loader {
		private ObjectMapper mapper;
		private File aggregateFile;

		Loader(File aggregateFile) {
			this.aggregateFile = aggregateFile;
			this.mapper = new ObjectMapper();
		}

		NetworkConfig load() throws JsonParseException, JsonMappingException, IOException {
			try {
				List<NetworkPointConfig> allConfigs = new ArrayList<NetworkPointConfig>();
				FileInputStream fileInputStream = new FileInputStream(aggregateFile);
				AggregateNetworkConfig aggregate = mapper.readValue(fileInputStream, AggregateNetworkConfig.class);
				for (String include : aggregate.includes) {
					File includeFile = makeIncludeFile(aggregateFile, include);
					List<NetworkPointConfig> configs = include(includeFile);
					allConfigs.addAll(configs);
				}
				return new NetworkConfig(allConfigs);
			} catch (JsonParseException e) {
				throw new RuntimeException("Error while processing file: " + aggregateFile, e);
			} catch (JsonMappingException e) {
				throw new RuntimeException("Error while processing file: " + aggregateFile, e);
			} catch (IOException e) {
				throw new RuntimeException("Error while processing file: " + aggregateFile, e);
			}
		}

		private List<NetworkPointConfig> include(File includeFile) throws JsonParseException, JsonMappingException, IOException {
			try {
				FileInputStream fileInputStream = new FileInputStream(includeFile);
				IndividualNetworkConfig individualNetworkConfig = mapper.readValue(fileInputStream, IndividualNetworkConfig.class);
				List<NetworkPointConfig> result = new ArrayList<NetworkPointConfig>();
				for (NestedNetworkDetail detail : individualNetworkConfig.details) {
					result.add(new CompositeNetworkConfig(individualNetworkConfig, detail));
				}
				return result;
			} catch (JsonParseException e) {
				throw new RuntimeException("Error while processing file: " + includeFile, e);
			} catch (JsonMappingException e) {
				throw new RuntimeException("Error while processing file: " + includeFile, e);
			} catch (IOException e) {
				throw new RuntimeException("Error while processing file: " + includeFile, e);
			}
		}
	}

	private static File makeIncludeFile(File file, String fileName) {
		File directory = file.getParentFile();
		return new File(directory, fileName);
	}

	private static final class AggregateNetworkConfig {

		@JsonProperty(value = "description")
		private String description;

		@JsonProperty(value = "include")
		private List<String> includes;

	}

	private static final class IndividualNetworkConfig {

		@JsonProperty(value = "description")
		private String description;

		@JsonProperty(value = "readonly")
		private boolean readonly;

		@JsonProperty(value = "environment")
		private String environment;

		@JsonProperty(value = "binding")
		private String binding;

		@JsonProperty(value = "networkPoints")
		private List<NestedNetworkDetail> details;
	}

	private static final class NestedNetworkDetail {
		
		@JsonProperty(value = "id")
		private String id;

		@JsonProperty(value = "address")
		private String address;

		@JsonProperty(value = "description")
		private String description;
	}

	private static final class CompositeNetworkConfig implements NetworkPointConfig {

		private final String description;
		private final boolean readOnly;
		private final String id;
		private final String environment;
		private final String address;
		private final String bindAddressName;
		
		public CompositeNetworkConfig(IndividualNetworkConfig parent, NestedNetworkDetail detail) {
			this.description = detail.description;
			this.id = detail.id;
			this.readOnly = parent.readonly;
			this.environment = parent.environment;
			this.address = detail.address;
			this.bindAddressName = parent.binding;
		}

		@Override
		public String getDescription() {
			return description;
		}

		@Override
		public boolean isReadOnly() {
			return readOnly;
		}

		@Override
		public String getEnvironment() {
			return environment;
		}

		@Override
		public String getID() {
			return id;
		}


		@Override
		public InetSocketAddress resolveAddress() {
			String[] tokens = address.split(":");
			String hostname = tokens[0];
			Integer port = Integer.valueOf(tokens[1]);
			return new InetSocketAddress(hostname, port);
		}

		@Override
		public NetworkInterface resolveBindAddress() throws SocketException {
			NetworkInterface networkInterface = NetworkInterface.getByName(bindAddressName);
			return networkInterface;
		}

		@Override
		public String toString() {
			return "CompositeNetworkConfig [description=" + description + ", readOnly=" + readOnly + ", id=" + id + ", environment=" + environment
					+ ", address=" + address + ", bindAddressName=" + bindAddressName + "]";
		}

		
		
		
		
	}


}
