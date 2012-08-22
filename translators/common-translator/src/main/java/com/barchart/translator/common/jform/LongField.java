package com.barchart.translator.common.jform;

import java.nio.ByteBuffer;

public abstract class LongField extends Field {

	public long getValue(ByteBuffer buffer) {
		return buffer.getLong();
	}

}
