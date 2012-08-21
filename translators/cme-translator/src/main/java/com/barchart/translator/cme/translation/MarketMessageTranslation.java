package com.barchart.translator.cme.translation;

import com.barchart.feed.fix.enums.FixMessageType;
import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.translator.common.lookup.ValueLookup;
import com.google.common.collect.ImmutableMap.Builder;

public class MarketMessageTranslation {

	private static final ValueLookup<FixMessageType, MarketMessage.Type> MARKET_MESSAGE_TYPE_MAP = new ValueLookup<FixMessageType, MarketMessage.Type>() {
		@Override
		public void init(Builder<FixMessageType, MarketMessage.Type> builder) {
			builder.put(FixMessageType.Update, MarketMessage.Type.UPDATE);
			builder.put(FixMessageType.Snapshot, MarketMessage.Type.SNAPSHOT);
		}
	};
	
	private final MarketMessage.Builder messageBuilder;

	public MarketMessageTranslation() {
		this.messageBuilder = MarketMessage.newBuilder();
	}

	public void translateMessageType(FixMessageType fixMessageType) {
		MarketMessage.Type type = MARKET_MESSAGE_TYPE_MAP.lookup(fixMessageType);
		if (type != null) {
			messageBuilder.setType(type);
		}
	}
	
	public void translateLastMsgSeqNumProcessed(Long lastMsgSeqNumProcessed) {
		if (lastMsgSeqNumProcessed != null) {
			//messageBuilder.setLastSequenceProcessed(lastMsgSeqNumProcessed);
		}
	}

	public void translateTradeDate(Integer tradeDate) {
		if (tradeDate != null) {
			messageBuilder.setTradeDate(tradeDate);
		}
	}
	public MarketMessage build() {
		return messageBuilder.build();
	}

	public MarketMessage.Builder getBuilder() {
		return messageBuilder;
	}


	
	
	

}
