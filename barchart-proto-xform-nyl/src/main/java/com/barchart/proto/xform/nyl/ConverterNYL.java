package com.barchart.proto.xform.nyl;

import com.barchart.proto.buf.data.MarketEntry;
import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.xform.ConverterAdapter;
import com.barchart.proto.xform.nyl.NYL.SnapshotFlag;

public class ConverterNYL extends ConverterAdapter {

	public boolean isKnown(final int type) {
		// TODO Auto-generated method stub
		return false;
	}

	public void applyType(final int type, final MarketMessage.Builder message,
			final MarketEntry.Builder entry) {
		// TODO Auto-generated method stub

	}

	public void applyPrice(final int price,
			final MarketMessage.Builder message, final MarketEntry.Builder entry) {
		// TODO Auto-generated method stub

	}

	public void applySize(final int size, final MarketMessage.Builder message,
			final MarketEntry.Builder entry) {
		// TODO Auto-generated method stub

	}

	public MarketMessage.Type dataType(final SnapshotFlag snapshotFlagEnum) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long targetId(final byte sourceId, final String securityId) {
		// TODO Auto-generated method stub
		return null;
	}

	public long timeStamp(final long timeStamp) {
		// TODO Auto-generated method stub
		return 0;
	}

}
