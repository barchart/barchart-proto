package com.barchart.translator.common.config;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;


public interface NetworkPointConfig {

	public String getDescription();
	public boolean isReadOnly();
	public String getEnvironment();
	public String getID();
	public InetSocketAddress resolveAddress();
	public NetworkInterface resolveBindAddress() throws SocketException;
	
	
}
