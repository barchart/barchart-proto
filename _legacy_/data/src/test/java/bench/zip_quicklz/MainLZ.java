/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.zip_quicklz;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** measure message heap size */
@SuppressWarnings("all")
public class MainLZ {

	private static final Logger log = LoggerFactory.getLogger(MainLZ.class);

	static final int COUNT_TEST = 100 * 1000;

	public static void main(final String... args) throws Exception {

		log.debug("init");

		log.debug("COUNT_TEST  : {}", COUNT_TEST);

		testZipTrip();

		testZipPack();

		log.debug("done");

	}

	static final Charset UTF_8 = Charset.forName("UTF-8");

	private static void testZipTrip() throws Exception {

		log.debug("test zip trip");

		byte[] dataIn = "123456789012345678901234567890".getBytes(UTF_8);

		byte[] pack = null;

		byte[] dataOut = null;

		{

			final byte[] arrayIn = dataIn;

			final byte[] arrayOut = QuickLZ.compress(arrayIn, 1);

			pack = arrayOut;

		}

		{

			final byte[] arrayIn = pack;

			final byte[] arrayOut = QuickLZ.decompress(arrayIn);

			dataOut = arrayOut;

		}

		assertArrayEquals(dataIn, dataOut);

		log.debug("zip size in   {}", dataIn.length);
		log.debug("zip size pack {}", pack.length);
		log.debug("zip size out  {}", dataOut.length);

		dataIn = null;

	}

	private static void testZipPack() throws Exception {

		final byte[] arrayIn = "123456789012345678901234567890".getBytes(UTF_8);

		final long timeStart = System.nanoTime();

		for (int k = 0; k < COUNT_TEST; k++) {

			final byte[] arrayOut = QuickLZ.compress(arrayIn, 1);

		}

		final long timeFinish = System.nanoTime();
		final long timeChange = timeFinish - timeStart;
		final long speed = timeChange / COUNT_TEST;

		log.debug("msg zip pack speed, nano : {}", speed);

	}

}
