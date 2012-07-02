package bench.fix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** measure message heap size */
public class MainFixCodec {

	private static final Logger log = LoggerFactory
			.getLogger(MainFixCodec.class);

	static final int COUNT_TEST = 100 * 1000;

	static final int COUNT_ENTRY = 20;

	static final long A_LONG = Long.MAX_VALUE - 3;
	static final int AN_INT = Integer.MAX_VALUE - 3;

	public static void main(final String... args) throws Exception {

		log.debug("init");

		log.debug("done");

	}

}
