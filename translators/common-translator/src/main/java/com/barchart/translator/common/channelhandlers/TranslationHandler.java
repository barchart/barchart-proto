package com.barchart.translator.common.channelhandlers;

import java.nio.ByteBuffer;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.translator.common.Translator;

public class TranslationHandler extends OneToOneDecoder {

	private static final Logger logger = LoggerFactory.getLogger(TranslationHandler.class);
	
	private final Translator translator;
	
	public TranslationHandler(Translator translator) {
		this.translator = translator;
	}
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		long start = System.nanoTime();
		if (!(msg instanceof ChannelBuffer)) {
			return msg;
		}
		ChannelBuffer channelBuffer = (ChannelBuffer) msg;
		ByteBuffer byteBuffer = channelBuffer.toByteBuffer();
		MarketPacket translatedPacket = translator.translate(byteBuffer);
		long stop = System.nanoTime();
		long micros = (stop - start) / 1000;
		logger.info("Translation took: " + micros + " micros");
		return translatedPacket;
	}

	 
	
}
