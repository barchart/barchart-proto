package com.barchart.translator.common.config2;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;

public final class NetworkPoint {

	private static final String DESCRIPTION = "description";
	private static final String ID = "id";
	private static final String ADDRESS = "address";

	private final String description;
	private final String id;
	private final String address;
	private final int port;

	public NetworkPoint(Config networkConfig) {
		this.description = networkConfig.getString(DESCRIPTION);
		this.id = networkConfig.getString(ID);
		String address = networkConfig.getString(ADDRESS);
		String[] tokens = address.split(":");
		this.address = tokens[0];
		this.port = Integer.valueOf(tokens[1]);
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

}
