/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
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

		return ((year * 100) + month) * 100 + day;

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

		/** make a message builder */
		final MarketData.Builder message = MarketData.newBuilder();

		/** specify snapshot vs update distinction */
		message.setType(MarketData.Type.UPDATE);

		/** make a market state change entry */
		{
			final MarketDataEntry.Builder entry = MarketDataEntry.newBuilder();

			entry.setMarketId(123321);
			entry.setSequence(1001);

			entry.setType(MarketDataEntry.Type.STATUS);
			entry.setAction(Action.EDIT);

			entry.addDescriptor(Descriptor.STATUS_MARKET_OPEN);
			entry.addDescriptor(Descriptor.PERIOD_DAY);

			entry.setTimeStamp(getTimeStamp());
			entry.setTradeDate(getTradeDate());

			message.addEntry(entry);

		}

		/** add a new trade entry */
		{
			final MarketDataEntry.Builder entry = MarketDataEntry.newBuilder();

			entry.setMarketId(123321);
			entry.setSequence(1002);

			entry.setType(MarketDataEntry.Type.TRADE);
			entry.setAction(Action.ADD);

			entry.setPriceMantissa(1000123);
			entry.setPriceExponent(-3);

			entry.setSizeMantissa(100);

			entry.setTimeStamp(getTimeStamp());
			entry.setTradeDate(getTradeDate());

			message.addEntry(entry);

		}

		/** make a packet wrapper */
		final Base.Builder base = MessageCodec.encode(message.build());

		/** setup packet header */
		base.setChannel(101);
		base.setSequence(1234567);
		base.setTimeStamp(getTimeStamp());

		/** produce a packet */
		final Base packet = base.build();

		log.debug("packet : \n{}", packet);

		/** produce packet wire representation */
		final byte[] array = MessageCodec.encode(packet);

		log.debug("array size : {}", array.length);
		log.debug("array as hex: \n{}", toHexString(array));

		//

		/** send/receive array over the wire here */

		//

		log.debug("########################## DECODE ########################## ");

		/**
		 * peek header fields for message routing and channel arbitrage
		 */
		final BaseHeader header = BaseHeader.from(array);

		log.debug("header : {}", header);

		/**
		 * process message via type-save distributor; logging visitor prints
		 * message for individula message type
		 */
		MessageCodec.decode(array, visitor, null);

		//

		log.debug("done");

	}

}
