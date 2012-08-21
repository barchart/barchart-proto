package com.barchart.translator.common.config;

import java.util.ArrayList;
import java.util.Collection;

public final class TranslationChannel {

	private final int channelID;

	private final Collection<NetworkPointConfig> sources;

	private final Collection<NetworkPointConfig> destinations;

	public TranslationChannel(int channelID, Collection<NetworkPointConfig> sources, Collection<NetworkPointConfig> destinations) {
		this.channelID = channelID;
		this.sources = new ArrayList<NetworkPointConfig>(sources);
		this.destinations = new ArrayList<NetworkPointConfig>(destinations);
	}

	public int getChannelID() {
		return channelID;
	}

	public Collection<NetworkPointConfig> getSources() {
		return sources;
	}

	public Collection<NetworkPointConfig> getDestinations() {
		return destinations;
	}

}
