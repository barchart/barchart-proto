package com.barchart.translator.common.jform;

import java.nio.ByteBuffer;

public class StringField extends Field {

	private int stringLength;

	public StringField(int stringLength) {
		this.stringLength = stringLength;
	}
	
	public String getValue(ByteBuffer buffer) {
		byte[] bytes = new byte[stringLength];
		buffer.get(bytes);
		return new String(bytes);
	}
	
}
