package com.barchart.translator.common.config;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public final class NetworkPoints {

	private final Map<String, NetworkPoint> networkPointsMap;
	
	public NetworkPoints(Map<String, NetworkPoint> readValue) {
		this.networkPointsMap = ImmutableMap.copyOf(readValue);
	}

	public NetworkPoint getNetworkPoint(String name) {
		return networkPointsMap.get(name);
	}
	
}
