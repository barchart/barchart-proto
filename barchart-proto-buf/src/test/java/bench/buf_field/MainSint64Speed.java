/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.buf_field;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.test.BenchSint32;

/** measure message heap size */
public class MainSint64Speed {

	private static final Logger log = LoggerFactory
			.getLogger(MainSint64Speed.class);

	static final int COUNT = 10 * 1000 * 1000;

	static final int VALUE = Integer.MAX_VALUE - 3;

	public static void main(final String... args) throws Exception {

		test();

		test();

		test();

	}

	static void test() throws Exception {

		log.debug("init");

		// 6 bytes
		{

			final BenchSint32 message = BenchSint32.newBuilder()
					.setValue(VALUE).build();

			log.debug("Sint64 wire size : {}", message.getSerializedSize());

		}

		// 0 ns
		{

			/** warm up */
			for (int index = 0; index < COUNT; index++) {
				BenchSint32.newBuilder().setValue(index).build();
			}

			final long timeStart = System.nanoTime();

			/** measure */
			for (int index = 0; index < COUNT; index++) {
				BenchSint32.newBuilder().setValue(index).build();
			}

			final long timeFinish = System.nanoTime();

			final long timeChange = timeFinish - timeStart;

			final long buildSpeed = timeChange / COUNT;

			log.debug("Sint64 build speed, nano : {}", buildSpeed);

		}

		// 75 ns
		{

			final BenchSint32 messageOut = BenchSint32.newBuilder()
					.setValue(VALUE).build();

			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			/** warm up */
			for (int index = 0; index < COUNT; index++) {
				output.reset();
				messageOut.writeTo(output);
			}

			final long timeStart = System.nanoTime();

			/** measure */
			for (int index = 0; index < COUNT; index++) {

				// 25 ns + 50 ns
				// output.reset();
				// messageOut.writeTo(output);

				// 50 ns
				messageOut.writeTo(new ByteArrayOutputStream());

			}

			final long timeFinish = System.nanoTime();

			final long timeChange = timeFinish - timeStart;

			final long buildSpeed = timeChange / COUNT;

			log.debug("Sint64 write speed, nano : {}", buildSpeed);

		}

		// 50 ns
		{

			final BenchSint32 messageOut = BenchSint32.newBuilder()
					.setValue(VALUE).build();

			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			messageOut.writeTo(output);

			final byte[] array = output.toByteArray();

			/** warm up */
			for (int index = 0; index < COUNT; index++) {
				final BenchSint32 messageIn = BenchSint32.parseFrom(array);
				messageIn.getValue();
			}

			final long timeStart = System.nanoTime();

			/** measure */
			for (int index = 0; index < COUNT; index++) {
				final BenchSint32 messageIn = BenchSint32.parseFrom(array);
				messageIn.getValue();
			}

			final long timeFinish = System.nanoTime();

			final long timeChange = timeFinish - timeStart;

			final long buildSpeed = timeChange / COUNT;

			log.debug("Sint64 parse speed, nano : {}", buildSpeed);

		}

		// 125 ns
		{

			/** warm up */
			for (int index = 0; index < COUNT; index++) {

				final BenchSint32 messageOut = BenchSint32.newBuilder()
						.setValue(VALUE).build();

				final ByteArrayOutputStream output = new ByteArrayOutputStream();

				messageOut.writeTo(output);

				final byte[] array = output.toByteArray();

				final BenchSint32 messageIn = BenchSint32.parseFrom(array);

				messageIn.getValue();

			}

			final long timeStart = System.nanoTime();

			/** measure */
			for (int index = 0; index < COUNT; index++) {

				final BenchSint32 messageOut = BenchSint32.newBuilder()
						.setValue(VALUE).build();

				final ByteArrayOutputStream output = new ByteArrayOutputStream();

				messageOut.writeTo(output);

				final byte[] array = output.toByteArray();

				final BenchSint32 messageIn = BenchSint32.parseFrom(array);

				messageIn.getValue();

			}

			final long timeFinish = System.nanoTime();

			final long timeChange = timeFinish - timeStart;

			final long buildSpeed = timeChange / COUNT;

			log.debug("Sint64 build/write/parse speed, nano : {}", buildSpeed);

		}

		log.debug("done");

	}

}
