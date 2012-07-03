/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.buf_message;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bench.buf_message.Factory.Mode;
import bench.zip_jdk.ZipUtil;

import com.barchart.proto.buf.Base;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry.Builder;
import com.barchart.proto.buf.MessageCodec;
import com.barchart.proto.buf.MessageVisitor;

/** measure message heap size */
public class MainMarketDataSpeed {

	private static final Logger log = LoggerFactory
			.getLogger(MainMarketDataSpeed.class);

	static final int COUNT_TEST = 100 * 1000;

	static final int COUNT_ENTRY = 20;

	static final long A_LONG = Long.MAX_VALUE - 3;
	static final int AN_INT = Integer.MAX_VALUE - 3;

	static Base buildBase(final Mode mode) {

		final MarketData.Builder builder = Factory.newMessage(mode);

		final Base base = MessageCodec.encode(builder.build());

		return base;
	}

	static void testWireSize(final Mode mode) throws Exception {

		final Base message = buildBase(mode);

		final int wireSize = message.getSerializedSize();

		log.debug("msg wireSize, total, byte : {}", wireSize);
		log.debug("msg wireSize, entry, byte : {}", wireSize / COUNT_ENTRY);

		//

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		message.writeTo(output);

		final byte[] arrayIn = output.toByteArray();
		final byte[] arrayOut = ZipUtil.compress(arrayIn);

		log.debug("zip arrayIn.length  : {}", arrayIn.length);
		log.debug("zip arrayOut.length : {}", arrayOut.length);

		final float ratio = 1.0F * arrayIn.length / arrayOut.length;

		log.debug("zip ratio : {}", ratio);

	}

	static void testSpeedBuild(final Mode mode) {

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			final Base message = buildBase(mode);
			message.getType();
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			final Base message = buildBase(mode);
			message.getType();
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg build speed, nano : {}", buildSpeed);

	}

	static void testSpeedOptimize(final Mode mode) {

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {

			final MarketData.Builder message = Factory.newMessage(mode);

			switch (mode) {
			case SNAPSHOT_SIMPLE:
			case SNAPSHOT_COMPLEX:
				message.setType(MarketData.Type.SNAPSHOT);
				break;
			case UPDATE_SIMPLE:
			case UPDATE_COMPLEX:
				message.setType(MarketData.Type.UPDATE);
				break;
			default:
				return;
			}

			final List<Builder> entryList = Factory.newEntryList(mode);

		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {

			final MarketData.Builder message = Factory.newMessage(mode);

			switch (mode) {
			case SNAPSHOT_SIMPLE:
			case SNAPSHOT_COMPLEX:
			case SNAPSHOT_SIMPLE_DESC:
				message.setType(MarketData.Type.SNAPSHOT);
				break;
			case UPDATE_SIMPLE:
			case UPDATE_COMPLEX:
			case UPDATE_SIMPLE_DESC:
				message.setType(MarketData.Type.UPDATE);
				break;
			default:
				return;
			}

			final List<Builder> entryList = Factory.newEntryList(mode);

		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg optimize speed, nano : {}", buildSpeed);

	}

	static void testSpeedWrite(final Mode mode) throws Exception {

		final Base messageOut = buildBase(mode);

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			output.reset();
			messageOut.writeTo(output);
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			messageOut.writeTo(new ByteArrayOutputStream());
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg write speed, nano : {}", buildSpeed);

	}

	static void testSpeedDecode(final Mode mode) throws Exception {

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>();

		final Base messageOut = buildBase(mode);

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		messageOut.writeTo(output);

		final byte[] array = output.toByteArray();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			MessageCodec.decode(array, visitor, null);
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			MessageCodec.decode(array, visitor, null);
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg decode speed, nano : {}", buildSpeed);

	}

	static void testSpeedAll(final Mode mode) throws Exception {

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {

			final Base messageOut = buildBase(mode);

			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			messageOut.writeTo(output);

			final byte[] array = output.toByteArray();

			MessageCodec.decode(array, visitor, null);

		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {

			final Base messageOut = buildBase(mode);

			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			messageOut.writeTo(output);

			final byte[] array = output.toByteArray();

			MessageCodec.decode(array, visitor, null);

		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long speed = timeChange / COUNT_TEST;

		log.debug("msg build/write/decode speed, nano : {}", speed);

	}

	static void testProfile(final Factory.Mode mode) throws Exception {

		log.debug("#############################");

		log.debug("MODE  : {}", mode);

		log.debug("COUNT_TEST  : {}", COUNT_TEST);

		testWireSize(mode);

		testSpeedBuild(mode);

		testSpeedOptimize(mode);

		testSpeedWrite(mode);

		testSpeedDecode(mode);

		testSpeedAll(mode);

		log.debug("#############################");

	}

	public static void main(final String... args) throws Exception {

		log.debug("init");

		testProfile(Mode.SNAPSHOT_SIMPLE);

		testProfile(Mode.SNAPSHOT_COMPLEX);

		testProfile(Mode.SNAPSHOT_SIMPLE_DESC);

		//

		testProfile(Mode.UPDATE_SIMPLE);

		testProfile(Mode.UPDATE_COMPLEX);

		testProfile(Mode.UPDATE_SIMPLE_DESC);

		log.debug("done");

	}

}
