/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf;

import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.Message;

/**
 * 
 * make sure MessageType.{enum} and MessageSpec.{extension} are properly mapped
 * 
 */
public class TestMessageCodecTypeMatch {

	static final Logger log = LoggerFactory
			.getLogger(TestMessageCodecTypeMatch.class);

	private static final AtomicInteger typeCount = new AtomicInteger(0);

	private static final ConcurrentMap<Class<Message>, Object> typeMap = //
	new ConcurrentHashMap<Class<Message>, Object>();

	private static final Object NONE = new Object();

	@BeforeClass
	public static void init() throws Exception {

		typeCount.set(0);

		typeMap.clear();

	}

	@AfterClass
	public static void done() throws Exception {

		assertEquals(typeCount.get(), MessageType.values().length);

		assertEquals(typeCount.get(), typeMap.size());

	}

	@SuppressWarnings("unchecked")
	private static void countTest(final Message message) {
		typeCount.incrementAndGet();
		typeMap.put((Class<Message>) message.getClass(), NONE);
	}

	@Test
	public void testMarketData() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final MarketData message = MarketData.newBuilder().build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final MarketData message, final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testMarketNews() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final MarketNews message = MarketNews.newBuilder().build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final MarketNews message, final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testHeartBeat() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final HeartBeat message = HeartBeat.newBuilder().build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final HeartBeat message, final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testLoginRequest() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final LoginRequest message = LoginRequest.newBuilder().build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final LoginRequest message, final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testLoginResponse() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final LoginResponse message = LoginResponse.newBuilder().build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final LoginResponse message, final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testInstrumentRequest() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final InstrumentRequest message = InstrumentRequest.newBuilder()
				.build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final InstrumentRequest message, final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testInstrumentResponse() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final InstrumentResponse message = InstrumentResponse.newBuilder()
				.build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final InstrumentResponse message, final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testDataSubscribeRequest() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final DataSubscribeRequest message = DataSubscribeRequest.newBuilder()
				.build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final DataSubscribeRequest message,
					final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testDataSubscribeResponse() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final DataSubscribeResponse message = DataSubscribeResponse
				.newBuilder().build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final DataSubscribeResponse message,
					final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testNewsSubscribeRequest() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final NewsSubscribeRequest message = NewsSubscribeRequest.newBuilder()
				.build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final NewsSubscribeRequest message,
					final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

	@Test
	public void testNewsSubscribeResponse() throws Exception {

		final Wrapper<Boolean> status = new Wrapper<Boolean>();
		status.set(false);

		assertEquals(status.get(), false);

		final NewsSubscribeResponse message = NewsSubscribeResponse
				.newBuilder().build();

		final Base base = MessageCodec.encode(message);

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>() {
			@Override
			public void apply(final NewsSubscribeResponse message,
					final Void param) {
				status.set(true);
			}
		};

		MessageCodec.decode(base, visitor, null);

		assertEquals(status.get(), true);

		countTest(message);

	}

}
