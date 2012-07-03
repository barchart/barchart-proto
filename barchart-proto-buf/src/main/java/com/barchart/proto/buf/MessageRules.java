/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf;

/**
 * special contract on message-entry nesting
 * 
 * serves additional compression
 */
public class MessageRules {

	/** entry overrides message; 0 is default */
	public static int getPriceExponent(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		if (entry.hasPriceExponent()) {
			return entry.getPriceExponent();
		}

		if (message.hasPriceExponent()) {
			return message.getPriceExponent();
		}

		return 0;

	}

	/** entry overrides message; 0 is default */
	public static int getSizeExponent(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		if (entry.hasSizeExponent()) {
			return entry.getSizeExponent();
		}

		if (message.hasSizeExponent()) {
			return message.getSizeExponent();
		}

		return 0;

	}

	/** Decimal.NULL for missing */
	public static Decimal getPrice(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		if (entry.hasPriceMantissa()) {

			final long mantissa = entry.getPriceMantissa();
			final int exponent = getPriceExponent(message, entry);

			return new Decimal(mantissa, exponent);

		} else {

			return Decimal.NULL;

		}

	}

	/** Decimal.NULL */
	public static Decimal getSize(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		if (entry.hasSizeMantissa()) {

			final long mantissa = entry.getPriceMantissa();
			final int exponent = getPriceExponent(message, entry);

			return new Decimal(mantissa, exponent);

		} else {

			return Decimal.NULL;
		}

	}

	/** entry is offset to message; default XXX for missing */
	public static long getTimeStamp(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		final boolean isValid = message.hasTimeStamp() || entry.hasTimeStamp();

		long millisUTC;

		if (isValid) {

			millisUTC = message.getTimeStamp() + entry.getTimeStamp();

		} else {

			millisUTC = System.currentTimeMillis(); // XXX

		}

		return millisUTC;

	}

	/** entry is offset to message; default XXX for missing */
	public static int getTradeDate(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		final boolean isValid = message.hasTradeDate() || entry.hasTradeDate();

		if (isValid) {

			final int days = message.getTradeDate() + entry.getTradeDate();

			// final MutableDateTime epoch = new
			// MutableDateTime(DateTimeZone.UTC);

			// epoch.addDays(days);

			// final long millisUTC = epoch.getMillis();

			// return ValueBuilder.newTime(millisUTC);

			return days;

		} else {

			return 0; // XXX

		}

	}

	public static boolean hasMarketId(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		return message.hasMarketId() || entry.hasMarketId();

	}

	/** entry overrides message; 0 for missing */
	public static long getMarketId(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		if (entry.hasMarketId()) {
			return entry.getMarketId();
		}

		if (message.hasMarketId()) {
			return message.getMarketId();
		}

		return 0;

	}

	/** entry is offset to message; 0 for missing */
	public static long getSequence(final MarketDataOrBuilder message,
			final MarketDataEntryOrBuilder entry) {

		return message.getSequence() + entry.getSequence();

	}

}
