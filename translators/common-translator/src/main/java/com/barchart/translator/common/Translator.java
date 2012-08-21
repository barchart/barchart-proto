package com.barchart.translator.common;

import java.nio.ByteBuffer;

import com.barchart.proto.buf.data.MarketPacket;

public interface Translator {

	public MarketPacket translate(ByteBuffer byteBuffer);
	
	
}
