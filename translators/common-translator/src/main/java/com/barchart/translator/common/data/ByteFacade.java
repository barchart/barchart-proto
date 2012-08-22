package com.barchart.translator.common.data;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedInts;

public class ByteFacade {

	private static final byte ZERO_BYTE = (byte) 0;
	
	private byte[] bytes;

	public ByteFacade(byte[] bytes) {
		this.bytes = bytes;
	}
	
	
	public short signedShort(int offset) {
		return Shorts.fromBytes(bytes[offset], bytes[offset + 1]);
	}
	
	public int unsignedShort(int offset) {
		short signedShort = signedShort(offset);
		return signedShort & 0xFFFF;
	}
	
	public int signedInteger(int offset) {
		return Ints.fromBytes(bytes[offset], bytes[offset + 1], bytes[offset + 2], bytes[offset + 3]);
	}
	
	public long unsignedInt(int offset) {
		return UnsignedInts.toLong(signedInteger(offset));
	}


	public int unsignedByte(int offset) {
		return UnsignedBytes.toInt(bytes[offset]);
	}


	public String string(int offset, int length) {
		return new String(bytes, offset, length);
	}
	

	public int length() {
		return bytes.length;
	}


	public <T extends Enum<?>> T enumeration(int offset, Class<T> clazz) {
		return null;
	}
	
}
