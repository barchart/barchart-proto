/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.buf_field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** measure message heap size */
public class MainAnIntSpeed {

	private static final Logger log = LoggerFactory
			.getLogger(MainAnIntSpeed.class);

	static final int COUNT = 10 * 1000 * 1000;

	static final int VALUE = Integer.MAX_VALUE - 3;

	public static void main(final String... args) throws Exception {

		log.debug("compare signed vs non-signed integer types");

		test();

	}

	static void test() throws Exception {

		MainInt32Speed.test();

		MainSint32Speed.test();

		MainInt64Speed.test();

		MainSint64Speed.test();

	}

}
