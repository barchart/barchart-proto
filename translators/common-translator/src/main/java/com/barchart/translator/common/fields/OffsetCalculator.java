package com.barchart.translator.common.fields;


public class OffsetCalculator {

	private int offset;
	private final Codec codec;

	public OffsetCalculator(Codec codec) {
		this.offset = 0;
		this.codec = codec;
	}
	
	public FixedLengthField newField(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StringField newStringField(int length) {
		StringField field = new StringField(codec, offset, length);
		offset += length;
		return field;
	}
	
	public IntegerField intField(int length) {
		IntegerField field = new IntegerField(codec, offset, length);
		offset += length;
		return field;
	}

	public <T extends Enum<?>> EnumField<T> newEnumField(int length, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	public ByteField newByteField() {
		return null;
	}

}
