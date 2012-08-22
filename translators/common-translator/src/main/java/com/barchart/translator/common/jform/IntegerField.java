package com.barchart.translator.common.jform;

import java.nio.ByteBuffer;


public abstract class IntegerField extends Field {

	public int getValue(ByteBuffer buffer) {
		return buffer.getInt();
	}
	
}
