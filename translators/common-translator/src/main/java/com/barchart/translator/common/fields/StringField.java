package com.barchart.translator.common.fields;

public final class StringField {

	private final Codec codec;
	private int offset;
	private int length;

	public StringField(Codec codec, int offset, int length) {
		this.codec = codec;
		this.offset = offset;
		this.length = length;
	}
	

	public String get(byte[] array) {
		return codec.string(array, offset, length);
	}


	public String get(int baseOffset, byte[] array) {
		// TODO Auto-generated method stub
		return null;
	}

}
