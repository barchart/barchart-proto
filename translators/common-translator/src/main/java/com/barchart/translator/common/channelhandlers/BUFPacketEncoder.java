package com.barchart.translator.common.channelhandlers;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.barchart.proto.buf.data.MarketPacket;

public class BUFPacketEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		if (msg instanceof MarketPacket) {
			MarketPacket packet = (MarketPacket) msg;
			ChannelBuffer dynamicBuffer = ChannelBuffers.dynamicBuffer();
			ChannelBufferOutputStream cbos = new ChannelBufferOutputStream(dynamicBuffer);
			packet.writeDelimitedTo(cbos);
			return cbos.buffer();
		} else {
			return msg;
		}
	}

}
