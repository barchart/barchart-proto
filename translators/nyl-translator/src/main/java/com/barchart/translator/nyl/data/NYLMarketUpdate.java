package com.barchart.translator.nyl.data;

import com.barchart.translator.nyl.data.enums.NYLUpdateType;

public interface NYLMarketUpdate {

	public int getMsgSize();

	public int getMsgType();

	public long getSourceTime();

	public long getSereiesSequenceNumber();

	public int getSecurityIDSource();

	public String getSecurityID();

	public int getSnapshotFlag();

	public int getUpdateCount();

	public interface Entry {
		
		public NYLUpdateType getUpdateType();

		public long getPrice();

		public long getVolume();
	}

}
