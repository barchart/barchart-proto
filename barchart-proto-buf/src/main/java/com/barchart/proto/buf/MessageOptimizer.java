package com.barchart.proto.buf;

import java.util.List;

/**
 * 
 **/
public class MessageOptimizer {

	private static class CalcLong {

		private long minimum = Long.MAX_VALUE;
		private long maximum = Long.MIN_VALUE;

		void apply(final long value) {
			if (value == 0) {
				return;
			}
			if (value > maximum) {
				maximum = value;
			}
			if (value < minimum) {
				minimum = value;
			}
		}

		@SuppressWarnings("unused")
		long getMinimum() {
			return minimum;
		}

		long getRange() {
			return maximum - minimum;
		}

		boolean isValid() {
			return minimum <= maximum;
		}

		boolean shouldCompress(final long range) {
			return isValid() && getRange() <= range;
		}

		@SuppressWarnings("unused")
		long offset(final long value) {
			return value - minimum;
		}

	}

	private static class CalcInt {

		private int minimum = Integer.MAX_VALUE;
		private int maximum = Integer.MIN_VALUE;

		void apply(final int value) {
			if (value == 0) {
				return;
			}
			if (value > maximum) {
				maximum = value;
			}
			if (value < minimum) {
				minimum = value;
			}
		}

		int getMinimum() {
			return minimum;
		}

		int getRange() {
			return maximum - minimum;
		}

		boolean isValid() {
			return minimum <= maximum;
		}

		boolean shouldCompress(final int range) {
			return isValid() && getRange() <= range;
		}

		int offset(final int value) {
			return value - minimum;
		}

	}

	public static void applyPack(final MarketData.Builder message) {

		if (MarketData.Type.UPDATE == message.getType()) {

		}

		final CalcLong calcMarketId = new CalcLong();
		final CalcInt calcTradeDate = new CalcInt();

		final List<MarketDataEntry.Builder> entryList = message
				.getEntryBuilderList();

		for (final MarketDataEntry.Builder entry : entryList) {

			if (entry.hasMarketId()) {
				calcMarketId.apply(entry.getMarketId());
			}

			if (entry.hasTradeDate()) {
				calcTradeDate.apply(entry.getTradeDate());
			}

		}

		final boolean doMarketId = calcMarketId.shouldCompress(0);
		final boolean doTradeDate = calcTradeDate.shouldCompress(100);

		for (final MarketDataEntry.Builder entry : entryList) {

			if (doMarketId && entry.hasMarketId()) {
				entry.clearMarketId();
			}

			if (doTradeDate && entry.hasTradeDate()) {
				entry.setTradeDate(calcTradeDate.offset(entry.getTradeDate()));
			}

		}

		if (doTradeDate) {
			message.setTradeDate(calcTradeDate.getMinimum());
		}

	}

	public static void applyUnpack(final MarketData.Builder message) {

		final List<MarketDataEntry.Builder> entryList = message
				.getEntryBuilderList();

		final boolean doMarketId = message.hasMarketId();
		final long marketId = message.getMarketId();

		final boolean doTradeDate = message.hasTradeDate()
				&& message.getTradeDate() != 0;

		final int msgTradeDate = message.getTradeDate();

		for (final MarketDataEntry.Builder entry : entryList) {

			if (doMarketId && !entry.hasMarketId()) {
				entry.setMarketId(marketId);
			}

			if (doTradeDate && entry.hasTradeDate()) {
				entry.setTradeDate(msgTradeDate + entry.getTradeDate());
			}

		}

		if (doMarketId) {
			message.clearMarketId();
		}

		if (doTradeDate) {
			message.clearTradeDate();
		}

	}

}
