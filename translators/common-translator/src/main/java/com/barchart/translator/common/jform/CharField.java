package com.barchart.translator.common.jform;

import java.nio.ByteBuffer;

public abstract class CharField extends Field {

	public char getValue(ByteBuffer buffer) {
		return (char) buffer.get();
	}
}
