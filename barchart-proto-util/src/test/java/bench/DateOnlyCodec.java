package bench;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.util.DateOnlyValue;
import com.barchart.proto.util.ProtoUtil;

public class DateOnlyCodec {

	private final static Logger log = LoggerFactory
			.getLogger(DateOnlyCodec.class);

	static final int COUNT = 1 * 1000 * 1000 * 1000;

	static void testBinary() {

		final long timeStart = System.nanoTime();

		final DateOnlyValue value = new DateOnlyValue(2012, 11, 24);

		for (int k = 0; k < COUNT; k++) {

			final int binary = ProtoUtil.intoBinaryDateOnly(value);

			ProtoUtil.fromBinaryDateOnly(binary);

		}

		final long timeFinish = System.nanoTime();

		final long timeDiff = timeFinish - timeStart;

		log.debug("binary  speed : {}", 1.0D * timeDiff / COUNT);

	}

	static void testDecimal() {

		final long timeStart = System.nanoTime();

		final DateOnlyValue value = new DateOnlyValue(2012, 11, 24);

		for (int k = 0; k < COUNT; k++) {

			final int decimal = ProtoUtil.intoDecimalDateOnly(value);

			ProtoUtil.fromDecimalDateOnly(decimal);

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
