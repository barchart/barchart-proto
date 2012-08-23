package com.barchart.translator.common.fields;

public class ByteField extends Field {

	private final Codec codec;
	private int offset;
	private int length;

	public ByteField(Codec codec, int offset, int length) {
		this.codec = codec;
		this.offset = offset;
		this.length = length;
	}
	

	public byte get(byte[] array) {
		return codec.signedByte(array, offset);
	}


	@Override
	public Object getAsObject(byte[] bytes) {
		return Byte.valueOf(get(bytes));
	}
}
