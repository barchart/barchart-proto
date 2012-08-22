package com.barchart.translator.nyl.jform.facade;

public final class MarketUpdateImpl extends OffsetByteReader implements MarketUpdate {

	public MarketUpdateImpl(int baseOffset, ByteFacade bytes) {
		super(baseOffset, bytes);
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
	public long getSourceTime() {
		return bytes.unsignedInt(offset(4));
	}

	@Override
	public long getSereiesSequenceNumber() {
		return bytes.unsignedInt(offset(8));
	}

	@Override
	public int getSecurityIDSource() {
		return bytes.unsignedByte(offset(12));
	}

	@Override
	public String getSecurityID() {
		return bytes.string(offset(13), 15);
	}

	@Override
	public int getSnapshotFlag() {
		return bytes.unsignedByte(offset(28));
	}

	@Override
	public int getUpdateCount() {
		return bytes.unsignedShort(offset(30));
	}

	public static class EntryImpl extends OffsetByteReader implements MarketUpdate.Entry {

		public EntryImpl(int baseOffset, ByteFacade bytes) {
			super(baseOffset, bytes);
		}

		@Override
		public UpdateType getUpdateType() {
			int code = bytes.unsignedShort(offset(0));
			return UpdateType.BASIS_TRADE;
//			return UpdateType.fromCode(code);
		}

		@Override
		public long getPrice() {
			return bytes.unsignedInt(offset(4));
		}

		@Override
		public long getVolume() {
			return bytes.unsignedInt(offset(8));
		}

		@Override
		public String toString() {
			return "EntryImpl [getUpdateType()=" + getUpdateType() + ", getPrice()=" + getPrice() + ", getVolume()=" + getVolume() + "]";
		}
		
		

	}

	public int accept(NYLPacketVisitor visitor) {
		visitor.visitMarketUpdate(this);
		int entryOffset = 32;
		for (int i = 0; i < getUpdateCount(); i++) {
			EntryImpl entry = new EntryImpl(entryOffset, bytes);
			visitor.visitMarketUpdateEntry(entry);
			entryOffset += 12;
		}
		return getMsgSize();
	}

	@Override
	public String toString() {
		return "MarketUpdateImpl [getMsgSize()=" + getMsgSize() + ", getMsgType()=" + getMsgType() + ", getSourceTime()=" + getSourceTime()
				+ ", getSereiesSequenceNumber()=" + getSereiesSequenceNumber() + ", getSecurityIDSource()=" + getSecurityIDSource() + ", getSecurityID()="
				+ getSecurityID() + ", getSnapshotFlag()=" + getSnapshotFlag() + ", getUpdateCount()=" + getUpdateCount() + "]";
	}

	
	
	
}
