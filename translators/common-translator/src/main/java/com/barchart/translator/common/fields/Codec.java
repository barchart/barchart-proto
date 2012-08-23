package com.barchart.translator.common.fields;

public interface Codec {

	public String string(byte[] array, int offset, int length);
	
	public byte signedByte(byte[] array, int offset);
	public short signedShort(byte[] array, int offset);
	public int signedInteger(byte[] array, int offset);
	public long signedLong(byte[] array, int offset);
	
	
}
