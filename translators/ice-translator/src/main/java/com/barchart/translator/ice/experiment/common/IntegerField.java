package com.barchart.translator.ice.experiment.common;

import java.nio.ByteBuffer;


public abstract class IntegerField extends Field {

	public int getValue(ByteBuffer buffer) {
		return buffer.getInt();
	}
	
}
