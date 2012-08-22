package com.barchart.translator.nyl.jform.facade;

public abstract class OffsetByteReader {

	private final int baseOffset;
	protected final ByteFacade bytes;

	protected OffsetByteReader(int baseOffset, ByteFacade bytes) {
		this.baseOffset = baseOffset;
		this.bytes = bytes;
	}
	
	protected int offset(int offset) {
		return baseOffset + offset;
	}

}
