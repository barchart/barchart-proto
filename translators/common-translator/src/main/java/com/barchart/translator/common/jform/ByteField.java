package com.barchart.translator.common.jform;

import java.nio.ByteBuffer;

public abstract class ByteField extends Field {

	public byte getValue(ByteBuffer buffer) {
		return buffer.get();
	}
	
}
