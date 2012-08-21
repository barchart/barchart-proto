package com.barchart.translator.common.config;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public final class NetworkPoint {

	// @JsonProperty(value="ip")
	private final String ip;

	// @JsonProperty(value="port")
	private final int port;

	// @JsonProperty(value="iface")
	private final String iface;

	// private boolean isReadOnly = true;

	@JsonCreator
	public NetworkPoint(@JsonProperty(value = "ip") String ip, @JsonProperty(value = "port") int port, @JsonProperty(value = "iface") String iface) {
		this.ip = ip;
		this.port = port;
		this.iface = iface;
	}

	public InetSocketAddress resolveSocketAddress() {
		return null;
	}

	public NetworkInterface resolveInterface() throws SocketException {
		return NetworkInterface.getByName(iface);
	}

	@Override
	public String toString() {
		return "NetworkPoint [ip=" + ip + ", port=" + port + ", iface=" + iface + "]";
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getIface() {
		return iface;
	}

}
