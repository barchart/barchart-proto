package com.barchart.translator.nyl.data.impl;

import com.barchart.translator.common.data.ByteFacade;
import com.barchart.translator.common.data.OffsetByteReader;
import com.barchart.translator.nyl.data.NYLMarketUpdate;
import com.barchart.translator.nyl.data.enums.NYLUpdateType;
import com.barchart.translator.nyl.data.parse.NYLPacketVisitor;

public final class NYLMarketUpdateImpl extends OffsetByteReader implements NYLMarketUpdate {

	public NYLMarketUpdateImpl(int baseOffset, ByteFacade bytes) {
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

	public static class EntryImpl extends OffsetByteReader implements NYLMarketUpdate.Entry {

		public EntryImpl(int baseOffset, ByteFacade bytes) {
			super(baseOffset, bytes);
		}

		@Override
		public NYLUpdateType getUpdateType() {
			int code = bytes.unsignedShort(offset(0));
			return NYLUpdateType.fromCode(code);
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


	@Override
	public String toString() {
		return "MarketUpdateImpl [getMsgSize()=" + getMsgSize() + ", getMsgType()=" + getMsgType() + ", getSourceTime()=" + getSourceTime()
				+ ", getSereiesSequenceNumber()=" + getSereiesSequenceNumber() + ", getSecurityIDSource()=" + getSecurityIDSource() + ", getSecurityID()="
				+ getSecurityID() + ", getSnapshotFlag()=" + getSnapshotFlag() + ", getUpdateCount()=" + getUpdateCount() + "]";
	}

	
	
	
}
