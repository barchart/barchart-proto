/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.buf_message;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bench.buf_message.Factory.Mode;
import bench.zip_jdk.ZipUtil;

import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.buf.data.PacketHeader;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.WireFormat;

/** measure message heap size */
public class MainPacketHeaderSpeed {

	private static final Logger log = LoggerFactory
			.getLogger(MainPacketHeaderSpeed.class);

	static final int COUNT_TEST = 100 * 1000;

	static MarketPacket buildBase(final Mode mode) {

		final MarketMessage.Builder message = Factory.newMessage(mode);

		final MarketPacket.Builder packet = MarketPacket.newBuilder();

		packet.setChannel(123);
		packet.setSequence(100 * 1000);
		packet.setTimeStamp(1000 * 1000);

		packet.addMessage(message);

		return packet.build();
	}

	static void testWireSize(final Mode mode) throws Exception {

		final MarketPacket base = buildBase(mode);

		final int wireSize = base.getSerializedSize();

		log.debug("msg wireSize, total, byte : {}", wireSize);

		//

		final byte[] arrayIn = base.toByteArray();
		final byte[] arrayOut = ZipUtil.compress(arrayIn);

		log.debug("zip arrayIn.length  : {}", arrayIn.length);
		log.debug("zip arrayOut.length : {}", arrayOut.length);

		final float ratio = 1.0F * arrayIn.length / arrayOut.length;

		log.debug("zip ratio : {}", ratio);

	}

	@SuppressWarnings("unused")
	static void testHeaderDebug(final Mode mode) throws Exception {

		final MarketPacket base = buildBase(mode);

		final byte[] array = base.toByteArray();

		final CodedInputStream input = CodedInputStream.newInstance(array);

		int count = 0;

		while (count <= 4) {

			final int tag = input.readTag();

			final int num = WireFormat.getTagFieldNumber(tag);
			final long value = input.readRawVarint64();

			// log.debug("tag : {} {}", tag, num);
			// log.debug("value : {}", value);

			count++;

		}

	}

	static void testHeaderRoundTrip(final Mode mode) throws Exception {

		final MarketPacket.Builder builder = MarketPacket.newBuilder();

		final int channelIn = 12345;
		final long sequenceIn = 123 * 1000;
		final long timeStampIn = 13 * 1000 * 1000;

		builder.setChannel(channelIn);
		builder.setSequence(sequenceIn);
		builder.setTimeStamp(timeStampIn);

		final MarketPacket base = builder.build();

		log.debug("base : \n{}", base);

		final byte[] array = base.toByteArray();

		final PacketHeader header = PacketHeader.from(array);

		assertEquals(channelIn, header.getChannel());
		assertEquals(sequenceIn, header.getSequence());
		assertEquals(timeStampIn, header.getTimeStamp());

	}

	static void testHeaderSpeed1(final Mode mode) throws Exception {

		final byte[] array = buildBase(mode).toByteArray();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			final MarketPacket base = MarketPacket.parseFrom(array);
			base.getChannel();
			base.getSequence();
			base.getTimeStamp();
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			final MarketPacket base = MarketPacket.parseFrom(array);
			base.getChannel();
			base.getSequence();
			base.getTimeStamp();
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg header speed.1, nano : {}", buildSpeed);

	}

	static void testHeaderSpeed2(final Mode mode) throws Exception {

		final byte[] array = buildBase(mode).toByteArray();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {

			final PacketHeader header = PacketHeader.from(array);
			header.getChannel();
			header.getSequence();
			header.getTimeStamp();

		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {

			final PacketHeader header = PacketHeader.from(array);
			header.getChannel();
			header.getSequence();
			header.getTimeStamp();

		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg header speed.2, nano : {}", buildSpeed);

	}

	public static void main(final String... args) throws Exception {

		log.debug("init");

		final Mode mode = Mode.SNAPSHOT_SIMPLE;

		testHeaderDebug(mode);

		testHeaderRoundTrip(mode);

		testWireSize(mode);

		testHeaderSpeed1(mode);

		testHeaderSpeed2(mode);

		log.debug("done");

	}

}
