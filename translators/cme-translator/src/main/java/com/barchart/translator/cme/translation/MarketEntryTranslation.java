package com.barchart.translator.cme.translation;

import java.math.BigDecimal;

import com.barchart.feed.fix.enums.FixEntryType;
import com.barchart.feed.fix.enums.FixUpdateAction;
import com.barchart.proto.buf.data.MarketEntry;
import com.barchart.proto.buf.data.MarketEntry.Action;
import com.barchart.proto.buf.data.MarketEntry.Builder;
import com.barchart.proto.buf.data.MarketEntry.Type;
import com.barchart.translator.common.lookup.ValueLookup;
import com.google.common.collect.ImmutableMap;

public class MarketEntryTranslation {

	private static final ValueLookup<FixUpdateAction, MarketEntry.Action> ACTION_MAP = new ValueLookup<FixUpdateAction, Action>() {
		@Override
		public void init(ImmutableMap.Builder<FixUpdateAction, MarketEntry.Action> builder) {
			builder.put(FixUpdateAction.New, Action.ADD);
			builder.put(FixUpdateAction.Delete, Action.REMOVE);
			builder.put(FixUpdateAction.Change, Action.EDIT);
		}
	};

	private static final ValueLookup<FixEntryType, MarketEntry.Type> ENTRY_TYPE_MAP = new ValueLookup<FixEntryType, MarketEntry.Type>() {
		@Override
		public void init(ImmutableMap.Builder<FixEntryType, Type> builder) {
			builder.put(FixEntryType.Bid, MarketEntry.Type.BID);
			builder.put(FixEntryType.Offer, MarketEntry.Type.ASK);
			builder.put(FixEntryType.Trade, MarketEntry.Type.TRADE);

//			builder.put(FixEntryType.SessionHighBid, MarketEntry.Type.SESSION_HIGH_BID);
//			builder.put(FixEntryType.SessionLowOffer, MarketEntry.Type.SESSION_LOW_ASK);
			builder.put(FixEntryType.SessionHighPrice, MarketEntry.Type.HIGH);
			builder.put(FixEntryType.SessionLowPrice, MarketEntry.Type.LOW);
			builder.put(FixEntryType.OpeningPrice, MarketEntry.Type.OPEN);
//			builder.put(FixEntryType.FixingPrice, MarketEntry.Type.FIXING_PRICE);
		}
	};

	private final MarketEntry.Builder entryBuilder;

	public MarketEntryTranslation() {
		this.entryBuilder = MarketEntry.newBuilder();
	}

	public void translatePrice(BigDecimal mdEntryPx) {
		if (mdEntryPx != null) {
			entryBuilder.setPriceMantissa(mdEntryPx.unscaledValue().longValue());
			entryBuilder.setPriceExponent(mdEntryPx.scale());
		}
	}

	public MarketEntry build() {
		return entryBuilder.build();
	}

	public void translateEntrySize(Integer entrySize) {
		if (entrySize != null) {
			entryBuilder.setSizeMantissa(entrySize);
			entryBuilder.setSizeExponent(0);
		}

	}

	public void translateUpdateAction(FixUpdateAction updateAction) {
		Action action = ACTION_MAP.lookup(updateAction);
		if (action != null) {
			entryBuilder.setAction(action);
		}
	}

	public void translateEntryType(FixEntryType entryType) {
		MarketEntry.Type type = ENTRY_TYPE_MAP.lookup(entryType);
		if (type != null) {
			entryBuilder.setType(type);
		}
	}

	public void translatePriceLevel(Integer priceLevel) {
		if (priceLevel != null) {
			entryBuilder.setIndex(priceLevel);
		}
	}

	public Builder getBuilder() {
		return entryBuilder;
	}

}
