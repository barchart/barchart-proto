package bench;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.util.DateTimeValue;
import com.barchart.proto.util.ProtoUtil;

public class DateTimeCodec {

	private final static Logger log = LoggerFactory
			.getLogger(DateTimeCodec.class);

	static final int COUNT = 1 * 1000 * 1000 * 1000;

	static void testBinary() {

		final long timeStart = System.nanoTime();

		final DateTimeValue value = new DateTimeValue(2012, 11, 24, 23, 51, 59,
				123);

		for (int k = 0; k < COUNT; k++) {

			final long binary = ProtoUtil.intoBinaryDateTime(value);

			ProtoUtil.fromBinaryDateTime(binary);

		}

		final long timeFinish = System.nanoTime();

		final long timeDiff = timeFinish - timeStart;

		log.debug("binary  speed : {}", 1.0D * timeDiff / COUNT);

	}

	static void testDecimal() {

		final long timeStart = System.nanoTime();

		final DateTimeValue value = new DateTimeValue(2012, 11, 24, 23, 51, 59,
				123);

		for (int k = 0; k < COUNT; k++) {

			final long decimal = ProtoUtil.intoDecimalDateTime(value);

			ProtoUtil.fromDecimalDateTime(decimal);

		}

		final long timeFinish = System.nanoTime();

		final long timeDiff = timeFinish - timeStart;

		log.debug("decimal speed : {}", 1.0D * timeDiff / COUNT);

	}

	public static void main(final String[] args) {

		log.debug("init");

		for (int k = 0; k < 10; k++) {
			testBinary();
			testDecimal();
		}

		log.debug("done");

	}

}
