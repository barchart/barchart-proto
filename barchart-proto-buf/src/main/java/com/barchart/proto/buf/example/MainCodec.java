/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf.example;

import java.math.BigInteger;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.Base;
import com.barchart.proto.buf.BaseHeader;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;
import com.barchart.proto.buf.MarketDataEntry.Action;
import com.barchart.proto.buf.MarketDataEntry.Descriptor;
import com.barchart.proto.buf.MessageCodec;
import com.barchart.proto.buf.MessageVisitor;

public class MainCodec {

	private static final Logger log = LoggerFactory.getLogger(MainCodec.class);

	public static String toHexString(final byte[] bytes) {

		final BigInteger number = new BigInteger(1, bytes);

		return String.format("%0" + (bytes.length << 1) + "x", number);

	}

	/** local market date in "FIX format" : 2012-07-04 as integer 20120704 */
	static int getTradeDate() {

		final Calendar date = Calendar.getInstance();

		final int year = date.get(Calendar.YEAR);
		final int month = date.get(Calendar.MONTH) + 1;
		final int day = date.get(Calendar.DAY_OF_MONTH);

		int tradeDate = year;
		tradeDate *= 100;
		tradeDate += month;
		tradeDate *= 100;
		tradeDate += day;

		return tradeDate;

	}

	/** milliseconds UTC */
	static long getTimeStamp() {

		return System.currentTimeMillis();

	}

	/** message processor that does only logging */
	static final MessageVisitor<Void> visitor = new LoggingVisitor();

	/**
	 * emulate encoder/decoder life cycle
	 */
	public static void main(final String... args) throws Exception {

		log.debug("init");

		log.debug("########################## ENCODE ########################## ");

		final MarketData.Builder message = MarketData.newBuilder();
		message.setType(MarketData.Type.UPDATE);

		{
			final MarketDataEntry.Builder entry = MarketDataEntry.newBuilder();

			entry.setMarketId(123);

			entry.setType(MarketDataEntry.Type.STATUS);
			entry.setAction(Action.EDIT);

			entry.addDescriptor(Descriptor.STATUS_MARKET_OPEN);
			entry.addDescriptor(Descriptor.PERIOD_DAY);

			entry.setTimeStamp(getTimeStamp());
			entry.setTradeDate(getTradeDate());

			message.addEntry(entry);

		}

		{
			final MarketDataEntry.Builder entry = MarketDataEntry.newBuilder();

			entry.setMarketId(123);

			entry.setType(MarketDataEntry.Type.TRADE);
			entry.setAction(Action.ADD);

			entry.setPriceMantissa(1000123);
			entry.setPriceExponent(-3);

			entry.setSizeMantissa(100);

			entry.setTimeStamp(getTimeStamp());
			entry.setTradeDate(getTradeDate());

			message.addEntry(entry);

		}

		//

		final Base.Builder base = MessageCodec.encode(message.build());

		base.setChannel(101);
		base.setSequence(1234567);
		base.setTimeStamp(getTimeStamp());

		final Base packet = base.build();

		log.debug("packet : \n{}", packet);

		final byte[] array = MessageCodec.encode(packet);

		log.debug("array size : {}", array.length);
		log.debug("array as hex: \n{}", toHexString(array));

		//

		/** send/receive array over the wire here */

		//

		log.debug("########################## DECODE ########################## ");

		final BaseHeader header = BaseHeader.from(array);

		log.debug("header : \n\t{}", header);

		MessageCodec.decode(array, visitor, null);

		//

		log.debug("done");

	}
}
