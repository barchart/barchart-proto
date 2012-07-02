package bench.field;

import org.joda.time.DateTime;
import org.joda.time.Days;
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

			log.debug("trade date '1' size : {}", message.getSerializedSize());

		}

		{

			final DateTime dateOne = new DateTime(0);
			final DateTime dateTwo = new DateTime();
			// log.debug("date range : {} {} ", dateOne, dateTwo);

			final Days daysRange = Days.daysBetween(dateOne, dateTwo);

			final int value = daysRange.getDays();

			final BenchTradeDate32 message = BenchTradeDate32.newBuilder()
					.setTradeDate(value).build();

			log.debug("trade date 'since' size : {}",
					message.getSerializedSize());

		}

		{

			final int value = 20120104; // 2012-01-04

			final BenchTradeDate32 message = BenchTradeDate32.newBuilder()
					.setTradeDate(value).build();

			log.debug("trade date 'fix days' size : {}",
					message.getSerializedSize());

		}

		{

			final long value = System.currentTimeMillis();

			final BenchTradeDate64 message = BenchTradeDate64.newBuilder()
					.setTradeDate(value).build();

			log.debug("trade date 'millisUTC' size : {}",
					message.getSerializedSize());

		}

		{

			final long value = 20120104081231000L; // 2012-01-04 08:12:31 000

			final BenchTradeDate64 message = BenchTradeDate64.newBuilder()
					.setTradeDate(value).build();

			log.debug("trade date 'fix millis' size : {}",
					message.getSerializedSize());

		}

		log.debug("done");

	}

}
