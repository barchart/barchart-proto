package com.barchart.translator.cfn.data.impl;

import com.barchart.translator.cfn.data.CFNMessageHeader;

public class CFNMessageHeaderImpl implements CFNMessageHeader {

	
	private byte[] array;
	private int baseOffset;

	public CFNMessageHeaderImpl(int offset, byte[] array) {
		this.baseOffset = offset;
		this.array = array;
	}
	
	
	@Override
	public ExchangeID getExchangeID() {
		byte code = signedByte(offset(0));
		return ExchangeID.fromCode(code);
	}


	@Override
	public MessageCategory getMessageCategory() {
		byte code = signedByte(offset(2));
		return MessageCategory.fromCode(code);
	}

	@Override
	public MessageType getMessageType() {
		byte code = signedByte(offset(3));
		return MessageType.fromCode(code);
	}

	@Override
	public int getMessageSequenceNumber() {
		return asciiInteger(offset(4), 8);
	}

	@Override
	public int getTime() {
		return asciiInteger(offset(12), 6);
	}


	private int asciiInteger(int offset, int charLength) {
		int value = 0;
		for (int k = 0; k < charLength; k++) {
			final int digit = array[offset + k] - (byte) '0';
			value = value * 10 + digit;
		}
		return value;
	}
	

	private byte signedByte(int offset) {
		return array[offset];
	}


	private int offset(int offset) {
		return baseOffset + offset;
	}

}
