package com.barchart.proto.xform.nyl;

import com.barchart.proto.buf.data.MarketEntry;
import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.xform.ConverterAdapter;
import com.barchart.proto.xform.nyl.NYL.SnapshotFlag;

public class ConverterNYL extends ConverterAdapter {

	public boolean isKnown(final int type) {
		return true;
	}

	public void applyType(final int type, final MarketMessage.Builder message,
			final MarketEntry.Builder entry) {
		entry.setType(MarketEntry.Type.BID);
	}

	public void applyPrice(final int price,
			final MarketMessage.Builder message, final MarketEntry.Builder entry) {
		entry.setPriceMantissa(price);
		entry.setPriceExponent(0);
	}

	public void applySize(final int size, final MarketMessage.Builder message,
			final MarketEntry.Builder entry) {
		entry.setSizeMantissa(size);
		entry.setSizeExponent(0);
	}

	public MarketMessage.Type dataType(final SnapshotFlag snapshotFlagEnum) {
		return MarketMessage.Type.SNAPSHOT;
	}

	public Long targetId(final byte sourceId, final String securityId) {
		return 1234L;
	}

	public long timeStamp(final long timeStamp) {
		return timeStamp;
	}

}
