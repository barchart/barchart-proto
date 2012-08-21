package com.barchart.translator.common.config;

import java.util.List;

public interface ChannelConfig {

	public int getChannelID();
	public List<String> getSources();
	public List<String> getDestinations();
	
}
