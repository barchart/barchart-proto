package com.barchart.translator.ice.experiment.common;

import java.nio.ByteBuffer;


public abstract class ShortField extends Field {

	public short getValue(ByteBuffer buffer) {
		return buffer.getShort();
	}
	
	
}
