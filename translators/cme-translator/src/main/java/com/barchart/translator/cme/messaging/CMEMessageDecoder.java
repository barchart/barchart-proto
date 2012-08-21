package com.barchart.translator.cme.messaging;

import java.nio.ByteBuffer;

public interface CMEMessageDecoder {

	public CMEMessage decode(ByteBuffer byteBuffer);
	
}
