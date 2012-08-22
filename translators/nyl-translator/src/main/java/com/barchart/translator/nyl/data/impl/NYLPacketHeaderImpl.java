package com.barchart.translator.nyl.data.impl;

import com.barchart.translator.common.data.ByteFacade;
import com.barchart.translator.common.data.OffsetByteReader;
import com.barchart.translator.nyl.data.NYLPacketHeader;
import com.barchart.translator.nyl.data.enums.NYLPacketType;

public class NYLPacketHeaderImpl extends OffsetByteReader implements NYLPacketHeader {

	public static final int HEADER_SIZE = 16;

	public NYLPacketHeaderImpl(int baseOffset, ByteFacade bytes) {
		super(0, bytes);
	}

	@Override
	public int getPacketLength() {
		return bytes.unsignedShort(offset(0));
	}

	@Override
	public NYLPacketType getPacketType() {
		int code = bytes.unsignedShort(offset(2));
		return NYLPacketType.fromCode(code);
	}

	@Override
	public long getPacketSeqNum() {
		return bytes.unsignedInt(offset(4));
	}

	@Override
	public long getSendTime() {
		return bytes.unsignedInt(offset(8));
	}

	@Override
	public int getServiceID() {
		return bytes.unsignedShort(offset(12));
	}

	@Override
	public int getDeliveryFlag() {
		return bytes.unsignedByte(offset(14));
	}

	@Override
	public int getNumberMsgEntries() {
		return bytes.unsignedByte(offset(15));
	}

	@Override
	public String toString() {
		return "NYLPacketHeaderImpl [getMsgSize()=" + getPacketLength() + ", getMsgType()=" + getPacketType() + ", getPacketSeqNum()=" + getPacketSeqNum()
				+ ", getSendTime()=" + getSendTime() + ", getServiceID()=" + getServiceID() + ", getDeliveryFlag()=" + getDeliveryFlag()
				+ ", getNumberMsgEntries()=" + getNumberMsgEntries() + "]";
	}

}
