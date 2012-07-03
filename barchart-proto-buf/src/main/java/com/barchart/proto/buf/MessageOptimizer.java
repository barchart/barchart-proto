/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.MarketDataEntry.Builder;

/**
 * 
 **/
public class MessageOptimizer {

	static final Logger log = LoggerFactory.getLogger(MessageOptimizer.class);

	private static final class CalcLong {

		private long lo = Long.MAX_VALUE;
		private long hi = Long.MIN_VALUE;

		void apply(final long value) {
			if (value > hi) {
				hi = value;
			}
			if (value < lo) {
				lo = value;
			}
		}

		long getLo() {
			return lo;
		}

		long getHi() {
			return hi;
		}

		long getRange() {
			return hi - lo;
		}

		boolean isValid() {
			return lo <= hi;
		}

		boolean shouldCompress(final long range) {
			return isValid() && getRange() <= range;
		}

		long offset(final long value) {
			return value - lo;
		}

	}

	private static final class CalcInt {

		private int lo = Integer.MAX_VALUE;
		private int hi = Integer.MIN_VALUE;

		void apply(final int value) {
			if (value > hi) {
				hi = value;
			}
			if (value < lo) {
				lo = value;
			}
		}

		int getLo() {
			return lo;
		}

		int getHi() {
			return hi;
		}

		int getRange() {
			return hi - lo;
		}

		boolean isValid() {
			return lo <= hi;
		}

		boolean shouldCompress(final int range) {
			return isValid() && getRange() <= range;
		}

		int offset(final int value) {
			return value - lo;
		}

	}

	public static void pack(final MarketData.Builder message,
			final List<Builder> entryList) {

		if (!message.hasType()) {
			return;
		}

		switch (message.getType()) {
		case UPDATE:
			packUpdate(message, entryList);
			return;
		case SNAPSHOT:
			packSnapshot(message, entryList);
			return;
		default:
			return;
		}

	}

	private static void packUpdate(final MarketData.Builder message,
			final List<Builder> entryList) {

	}

	private static void packPriceExp(final MarketDataEntry.Builder entry,
			final int priceExpLO) {

		long mantissa = entry.getPriceMantissa();
		int exponent = entry.getPriceExponent();

		while (exponent > priceExpLO) {
			exponent--;
			mantissa *= 10;
		}

		entry.setPriceMantissa(mantissa);
		entry.clearPriceExponent();

	}

	private static void packSizeExp(final MarketDataEntry.Builder entry,
			final int sizeExpLO) {

		long mantissa = entry.getSizeMantissa();
		int exponent = entry.getSizeExponent();

		while (exponent > sizeExpLO) {
			exponent--;
			mantissa *= 10;
		}

		entry.setSizeMantissa(mantissa);
		entry.clearSizeExponent();

	}

	private static void packSnapshot(final MarketData.Builder message,
			final List<Builder> entryList) {

		final CalcInt calcPriceExp = new CalcInt();
		final CalcInt calcSizeExp = new CalcInt();

		for (final MarketDataEntry.Builder entry : entryList) {

			if (entry.hasPriceMantissa()) {
				calcPriceExp.apply(entry.getPriceExponent());
			}

			if (entry.hasSizeMantissa()) {
				calcSizeExp.apply(entry.getSizeExponent());
			}

		}

		final boolean doPriceExp = calcPriceExp.isValid();
		final boolean doSizeExp = calcSizeExp.isValid();

		final int priceExpLO = calcPriceExp.getLo();
		final int sizeExpLO = calcSizeExp.getLo();

		for (final MarketDataEntry.Builder entry : entryList) {

			if (doPriceExp && entry.hasPriceMantissa()) {
				packPriceExp(entry, priceExpLO);
			}

			if (doSizeExp && entry.hasSizeMantissa()) {
				packSizeExp(entry, sizeExpLO);
			}

		}

		if (doPriceExp) {
			message.setPriceExponent(priceExpLO);
		}

		if (doSizeExp) {
			message.setSizeExponent(sizeExpLO);
		}

	}

	static void packXXX(final MarketData.Builder message) {

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
			message.setTradeDate(calcTradeDate.getLo());
		}

	}

	public static void unpack(final MarketData.Builder message) {

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
