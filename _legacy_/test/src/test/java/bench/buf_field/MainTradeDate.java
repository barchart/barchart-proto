/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.buf_field;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.test.BenchEmpty;
import com.barchart.proto.buf.test.BenchTradeDate32;
import com.barchart.proto.buf.test.BenchTradeDate64;

/** choose optimal trade date encoding */
public class MainTradeDate {

	private static final Logger log = LoggerFactory
			.getLogger(MainTradeDate.class);

	public static void main(final String... args) {

		log.debug("init");

		{

			final BenchEmpty message = BenchEmpty.newBuilder().build();

			log.debug("empty size : {}", message.getSerializedSize());

		}

		{

			final int value = 1;

			final BenchTradeDate32 message = BenchTradeDate32.newBuilder()
					.setTradeDate(value).build();

			final int size = message.getSerializedSize();

			log.debug(" '1' value : {} size : {}", value, size);

		}

		{

			final LocalDate dateOne = new LocalDate(1970, 1, 1);
			final LocalDate dateTwo = new LocalDate();
			// log.debug("date range : {} {} ", dateOne, dateTwo);

			final Days daysRange = Days.daysBetween(dateOne, dateTwo);

			final int value = daysRange.getDays();

			final BenchTradeDate32 message = BenchTradeDate32.newBuilder()
					.setTradeDate(value).build();

			final int size = message.getSerializedSize();

			log.debug(" 'since' value : {} size : {}", value, size);

		}

		{

			final int value = 20120104; // 2012-01-04

			final BenchTradeDate32 message = BenchTradeDate32.newBuilder()
					.setTradeDate(value).build();

			final int size = message.getSerializedSize();

			log.debug(" 'fix days' value : {} size : {}", value, size);

		}

		{

			final long value = System.currentTimeMillis();

			final BenchTradeDate64 message = BenchTradeDate64.newBuilder()
					.setTradeDate(value).build();

			final int size = message.getSerializedSize();

			log.debug(" 'millisUTC' value : {} size : {}", value, size);

		}

		{

			final long value = 20120104081231000L; // 2012-01-04 08:12:31 000

			final BenchTradeDate64 message = BenchTradeDate64.newBuilder()
					.setTradeDate(value).build();

			final int size = message.getSerializedSize();

			log.debug(" 'fix millis' value : {} size : {}", value, size);

		}

		{

			log.debug("############################");

			final int yearBase = 1970;

			log.debug(" base : {}", yearBase);

			for (int yearThis = 2010; yearThis < 2020; yearThis++) {

				final LocalDate dateOne = new LocalDate(yearBase, 1, 1);
				final LocalDate dateTwo = new LocalDate(yearThis, 1, 1);
				// log.debug("date range : {} {} ", dateOne, dateTwo);

				final Days daysRange = Days.daysBetween(dateOne, dateTwo);

				final int value = daysRange.getDays();

				final BenchTradeDate32 message = BenchTradeDate32.newBuilder()
						.setTradeDate(value).build();

				final int size = message.getSerializedSize();

				log.debug(" this : {} ; size : {} ;", yearThis, size);

			}

		}

		log.debug("done");

	}

}
