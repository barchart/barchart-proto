package com.barchart.translator.nyl.data;

import com.barchart.translator.nyl.data.enums.NYLPacketType;

public interface PacketHeader {
	
	public int getPacketLength();

	public NYLPacketType getPacketType();

	public long getPacketSeqNum();

	public long getSendTime();

	public int getServiceID();

	public int getDeliveryFlag();

	public int getNumberMsgEntries();
	
}
