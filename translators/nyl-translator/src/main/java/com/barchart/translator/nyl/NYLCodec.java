package com.barchart.translator.nyl;

import com.barchart.translator.common.fields.Codec;
import com.google.common.primitives.Shorts;

public class NYLCodec implements Codec {

	@Override
	public String string(byte[] bytes, int offset, int length) {
		return new String(bytes, offset, length);
	}

	@Override
	public byte signedByte(byte[] bytes, int offset) {
		return bytes[offset];
	}

	@Override
	public short signedShort(byte[] array, int offset) {
		return Shorts.fromBytes(array[offset], array[offset + 1]);
	}

	@Override
	public int signedInteger(byte[] array, int offset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long signedLong(byte[] array, int offset) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
