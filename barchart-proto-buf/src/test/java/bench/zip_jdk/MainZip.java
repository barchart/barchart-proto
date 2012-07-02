package bench.zip_jdk;

import static org.testng.internal.junit.ArrayAsserts.*;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** measure message heap size */
public class MainZip {

	private static final Logger log = LoggerFactory.getLogger(MainZip.class);

	static final int COUNT_TEST = 100 * 1000;

	public static void main(final String... args) throws Exception {

		log.debug("init");

		log.debug("COUNT_TEST  : {}", COUNT_TEST);

		testZipTrip();

		testZipPack();

		log.debug("done");

	}

	static final Charset UTF_8 = Charset.forName("UTF-8");

	static final byte[] DATA_1 = "1234567890".getBytes(UTF_8);

	static final byte[] DATA_2 = "12345678901234567890".getBytes(UTF_8);

	static final byte[] DATA_3 = "123456789012345678901234567890"
			.getBytes(UTF_8);

	private static void testZipTrip() throws Exception {

		log.debug("test zip trip");

		final byte[] dataIn = DATA_3;

		byte[] pack = null;

		byte[] dataOut = null;

		{

			final byte[] arrayIn = dataIn;

			final byte[] arrayOut = Util.compress(arrayIn);

			pack = arrayOut;

		}

		{

			final byte[] arrayIn = pack;

			final byte[] arrayOut = new byte[128];

			final int sizeOut = Util.decompress(arrayIn, arrayOut);

			dataOut = new byte[sizeOut];

			System.arraycopy(arrayOut, 0, dataOut, 0, sizeOut);

		}

		assertArrayEquals(dataIn, dataOut);

		log.debug("zip size in   {}", dataIn.length);
		log.debug("zip size pack {}", pack.length);
		log.debug("zip size out  {}", dataOut.length);

	}

	private static void testZipPack() throws Exception {

		final byte[] arrayIn = DATA_3;

		final long timeStart = System.nanoTime();

		for (int k = 0; k < COUNT_TEST; k++) {

			final byte[] arrayOut = Util.compress(arrayIn);

		}

		final long timeFinish = System.nanoTime();
		final long timeChange = timeFinish - timeStart;
		final long speed = timeChange / COUNT_TEST;

		log.debug("msg zip pack speed, nano : {}", speed);

	}

}
