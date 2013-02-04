/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.barchart.proto.buf.MarketDataEntry.Builder;

public class TestMessageOptimizer {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSnapshot0() {

		final MarketData.Builder message = MarketData.newBuilder();

		message.setType(MarketData.Type.SNAPSHOT);

		final MarketDataEntry.Builder entry = MarketDataEntry.newBuilder();

		// entry.setPriceMantissa(123);
		entry.setPriceExponent(-1);

		final List<MarketDataEntry.Builder> entryList = new ArrayList<Builder>();

		entryList.add(entry);

		//

		assertFalse(message.hasPriceExponent());
		assertTrue(entry.hasPriceExponent());

		MessageOptimizer.pack(message, entryList);

		assertFalse(message.hasPriceExponent());
		assertTrue(entry.hasPriceExponent());

	}

	@Test
	public void testSnapshot1() {

		final MarketData.Builder message = MarketData.newBuilder();

		message.setType(MarketData.Type.SNAPSHOT);

		final MarketDataEntry.Builder entry = MarketDataEntry.newBuilder();

		entry.setPriceMantissa(123);
		entry.setPriceExponent(-1);

		final List<MarketDataEntry.Builder> entryList = new ArrayList<Builder>();

		entryList.add(entry);

		//

		assertFalse(message.hasPriceExponent());
		assertTrue(entry.hasPriceExponent());

		MessageOptimizer.pack(message, entryList);

		assertTrue(message.hasPriceExponent());
		assertFalse(entry.hasPriceExponent());

		//

		assertEquals(message.getPriceExponent(), -1);

	}

	@Test
	public void testSnapshot2() {

		final MarketDataEntry.Builder entry1 = MarketDataEntry.newBuilder();

		entry1.setPriceMantissa(123);
		entry1.setPriceExponent(-1);

		//

		final MarketDataEntry.Builder entry2 = MarketDataEntry.newBuilder();

		entry2.setPriceMantissa(123);
		entry2.setPriceExponent(-2);

		//

		final List<MarketDataEntry.Builder> entryList = new ArrayList<Builder>();
		entryList.add(entry1);
		entryList.add(entry2);

		//

		final MarketData.Builder message = MarketData.newBuilder();
		message.setType(MarketData.Type.SNAPSHOT);

		//

		assertFalse(message.hasPriceExponent());
		assertTrue(entry1.hasPriceExponent());
		assertTrue(entry2.hasPriceExponent());

		MessageOptimizer.pack(message, entryList);

		assertTrue(message.hasPriceExponent());
		assertFalse(entry1.hasPriceExponent());
		assertFalse(entry2.hasPriceExponent());

		//

		assertEquals(message.getPriceExponent(), -2);

		assertEquals(entry1.getPriceMantissa(), 1230);
		assertEquals(entry2.getPriceMantissa(), 123);

	}

}
