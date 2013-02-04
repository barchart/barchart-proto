/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.buf_field;

import java.lang.management.ManagementFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.util.bench.size.JavaSize;
import com.sun.management.OperatingSystemMXBean;

/** measure message heap size */
@SuppressWarnings("restriction")
public class MainString {

	private static final Logger log = LoggerFactory.getLogger(MainString.class);

	private static final Runtime runtime = Runtime.getRuntime();

	static void heapGC() throws Exception {
		runtime.gc();
		Thread.sleep(1000);
		runtime.gc();
		Thread.sleep(1000);
		runtime.gc();
		Thread.sleep(1000);
		runtime.gc();
		Thread.sleep(1000);
		runtime.gc();
		Thread.sleep(1000);
	}

	final static OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory
			.getOperatingSystemMXBean();

	static long heapSize() {

		// long size = bean.getTotalPhysicalMemorySize();

		final long size = runtime.totalMemory();

		return size;
	}

	static final int COUNT = 1 * 1000 * 1000;

	static final String[] array = new String[COUNT];

	public static void main(final String... args) throws Exception {

		log.debug("init");

		{

			final String message = new String("" + COUNT);

			log.debug("string heap estimated size : {}", JavaSize.of(message));

		}

		{

			/** warm up */
			for (int index = 0; index < COUNT; index++) {
				new String("" + COUNT + index);
			}

			heapGC();

			final long heapStart = heapSize();

			for (int index = 0; index < COUNT; index++) {
				array[index] = new String("" + COUNT + index);
			}

			heapGC();

			final long heapFinish = heapSize();

			final long heapChange = heapFinish - heapStart;

			final long messageSize = heapChange / COUNT;

			log.debug("string heap measured size : {}", messageSize);

			Thread.sleep(1000 * 1000);

		}

		log.debug("done");

	}
}
