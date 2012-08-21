package com.barchart.translator.ice.experiment.common;

import java.nio.ByteBuffer;


public abstract class LongField  extends Field {

	public long getValue(ByteBuffer buffer) {
		return buffer.getLong();
	}

}
