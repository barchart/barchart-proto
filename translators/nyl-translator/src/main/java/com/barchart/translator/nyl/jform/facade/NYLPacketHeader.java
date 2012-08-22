package com.barchart.translator.nyl.jform.facade;

public interface NYLPacketHeader {
	
	public int getMsgSize();

	public int getMsgType();

	public long getPacketSeqNum();

	public long getSendTime();

	public int getServiceID();

	public int getDeliveryFlag();

	public int getNumberMsgEntries();
	
}
