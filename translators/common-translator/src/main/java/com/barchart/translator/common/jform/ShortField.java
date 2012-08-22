package com.barchart.translator.common.jform;

import java.nio.ByteBuffer;


public abstract class ShortField extends Field {

	public short getValue(ByteBuffer buffer) {
		return buffer.getShort();
	}
	
	
}
