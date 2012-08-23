package com.barchart.translator.common.fields;

public class IntegerField {
	
	private final Codec codec;
	private int offset;
	private int length;

	public IntegerField(Codec codec, int offset, int length) {
		this.codec = codec;
		this.offset = offset;
		this.length = length;
	}

	public int get(byte[] array) {
		return codec.signedInteger(array, offset);
	}

	public int get(int baseOffset, byte[] array) {
		return 0;
	}

}
