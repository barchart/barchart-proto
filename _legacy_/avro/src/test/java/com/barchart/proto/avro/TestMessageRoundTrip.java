/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.avro;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMessageRoundTrip {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test0() throws Exception {

		final DataEntry.Builder entryBuilder = DataEntry.newBuilder();

		entryBuilder.setMarketId(1234);

		final DataEntry entry = entryBuilder.build();

	}

	@Test
	public void test1() throws Exception {

		final DataEntry.Builder entryBuilder = DataEntry.newBuilder();

		entryBuilder.setMarketId(1234);

		final DataEntry entry = entryBuilder.build();

		//

		final MarketData.Builder messageBuilder = MarketData.newBuilder();

		messageBuilder.setMarketId(123);

		final List<DataEntry> entryList = new ArrayList<DataEntry>();
		entryList.add(entry);

		messageBuilder.setEntryList(entryList);

		final MarketData messageOut = messageBuilder.build();

		//

		final Base.Builder baseBuilder = Base.newBuilder();

		baseBuilder.setType(MessageType.MarketData);
		baseBuilder.setSequence(1234567);
		baseBuilder.setMessage(messageOut);

		final Base source = baseBuilder.build();

		//

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		final Encoder encoder = EncoderFactory.get().directBinaryEncoder(
				output, null);

		final SpecificDatumWriter<Base> writer = new SpecificDatumWriter<Base>(
				Base.class);

		writer.write(source, encoder);

		final byte[] array = output.toByteArray();

		//

		final Decoder decoder = DecoderFactory.get().binaryDecoder(array, null);

		final SpecificDatumReader<Base> reader = new SpecificDatumReader<Base>(
				Base.class);

		final Base target = new Base();

		reader.read(target, decoder);

		//

		assertEquals(source, target);

		final Object messageIn = target.getMessage();

		assertTrue(messageIn instanceof MarketData);

		assertEquals(messageOut, messageIn);

	}

}
