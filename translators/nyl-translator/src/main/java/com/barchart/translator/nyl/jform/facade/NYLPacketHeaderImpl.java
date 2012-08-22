package com.barchart.translator.nyl.jform.facade;


public class NYLPacketHeaderImpl implements NYLPacketHeader {

	private final ByteFacade bytes;
	private final int baseOffset;

	public NYLPacketHeaderImpl(int baseOffset, ByteFacade bytes) {
		this.baseOffset = baseOffset;
		this.bytes = bytes;
	}

	@Override
	public int getMsgSize() {
		return bytes.unsignedShort(offset(0));
	}

	@Override
	public int getMsgType() {
		return bytes.unsignedShort(offset(2));
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


	private int offset(int offset) {
		return baseOffset + offset;
	}

	public void accept(NYLPacketVisitor visitor) {
		visitor.visitPacketHeader(this);
	}

	@Override
	public String toString() {
		return "NYLPacketHeaderImpl [getMsgSize()=" + getMsgSize() + ", getMsgType()=" + getMsgType() + ", getPacketSeqNum()=" + getPacketSeqNum()
				+ ", getSendTime()=" + getSendTime() + ", getServiceID()=" + getServiceID() + ", getDeliveryFlag()=" + getDeliveryFlag()
				+ ", getNumberMsgEntries()=" + getNumberMsgEntries() + "]";
	}
	
	

}
