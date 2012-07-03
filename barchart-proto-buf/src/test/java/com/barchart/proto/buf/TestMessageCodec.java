/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMessageCodec {

	private static final Logger log = LoggerFactory
			.getLogger(TestMessageCodec.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private long id1;
	private long id2;

	private final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {

		@Override
		public void apply(final MarketData message, final Void param) {
			log.debug("got MarketData {}", message);
			id1 = message.getMarketId();
		}

		@Override
		public void apply(final MarketNews message, final Void param) {
			log.debug("got MarketNews {}", message);
			id2 = message.getMarketId();
		}

	};

	@Test
	public void test1() throws Exception {

		assertEquals(id1, 0);

		final MarketData message = MarketData.newBuilder().setMarketId(123)
				.build();

		final Base base = MessageCodec.encode(message);

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		base.writeTo(output);

		final byte[] array = output.toByteArray();

		MessageCodec.decode(array, visitor, null);

		assertEquals(id1, 123);

	}

	@Test
	public void test2() throws Exception {

		assertEquals(id2, 0);

		final MarketNews message = MarketNews.newBuilder().setMarketId(456)
				.build();

		final Base base = MessageCodec.encode(message);

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		base.writeTo(output);

		final byte[] array = output.toByteArray();

		MessageCodec.decode(array, visitor, null);

		assertEquals(id2, 456);

	}

	public static void main(final String... args) throws Exception {

		log.debug("init");

		MessageCodec.decode(null);

		// new TestMessageCodec().test();

		log.debug("done");

	}

}
