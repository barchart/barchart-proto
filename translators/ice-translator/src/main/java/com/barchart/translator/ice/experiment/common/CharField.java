package com.barchart.translator.ice.experiment.common;

import java.nio.ByteBuffer;


public abstract class CharField extends Field {

	public char getValue(ByteBuffer buffer) {
		return (char) buffer.get();
	}
}
