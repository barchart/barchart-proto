package com.barchart.translator.common.channelhandlers;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class EndpointHandler extends SimpleChannelUpstreamHandler {

	
	private final Iterable<Channel> endpointChannels;

	public EndpointHandler(Iterable<Channel> destinationChannels) {
		this.endpointChannels = destinationChannels;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		for (Channel channel : endpointChannels) {
			channel.write(e.getMessage());
		}
	}
	
	
}
