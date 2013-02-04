/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** System.nanoTime() takes about 100 ns */
public class MainTime {

	private static final Logger log = LoggerFactory.getLogger(MainTime.class);

	static final int COUNT_TEST = 1000 * 1000;

	public static void main(final String... args) throws Exception {

		log.debug("init");

		final StopWatch2 timer = new StopWatch2();

		{

			timer.startNow();
			for (int k = 0; k < COUNT_TEST; k++) {
			}

			timer.stopNow();

			log.debug("base time {}", timer.getDiff() / COUNT_TEST);

		}

		{

			timer.startNow();
			for (int k = 0; k < COUNT_TEST; k++) {
				timer.blockStart();
				timer.blockFinish();
			}

			timer.stopNow();

			log.debug("pause/resume time {}", timer.getBlockTotal()
					/ COUNT_TEST);

		}

		log.debug("done");

	}

}
