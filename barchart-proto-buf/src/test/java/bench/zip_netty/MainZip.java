package bench.zip_netty;

import static org.testng.internal.junit.ArrayAsserts.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.HeapByteBuf;
import io.netty.handler.codec.compression.JZlibDecoder;
import io.netty.handler.codec.compression.JZlibEncoder;

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

		byte[] dataIn = DATA_3;

		byte[] pack = null;

		byte[] dataOut = null;

		{

			final JZlibEncoder zipIn = new JZlibEncoder();

			final ByteBuf in = new HeapByteBuf(dataIn);

			final ByteBuf out = new HeapByteBuf(256);

			zipIn.encode(null, in, out);

			final byte[] array = out.array();
			final int size = out.readableBytes();

			pack = new byte[size];

			System.arraycopy(array, 0, pack, 0, size);

		}

		{

			final JZlibDecoder zipOut = new JZlibDecoder();

			final ByteBuf in = new HeapByteBuf(pack);

			final ByteBuf out = new HeapByteBuf(256);

			zipOut.decode(null, in, out);

			final byte[] array = out.array();
			final int size = out.readableBytes();

			dataOut = new byte[size];

			System.arraycopy(array, 0, dataOut, 0, size);

		}

		assertArrayEquals(dataIn, dataOut);

		log.debug("zip size in   {}", dataIn.length);
		log.debug("zip size pack {}", pack.length);
		log.debug("zip size out  {}", dataOut.length);

		dataIn = null;

	}

	private static void testZipPack() throws Exception {

		final byte[] arrayIn = DATA_3;

		final JZlibEncoder zipIn = new JZlibEncoder();

		final ByteBuf in = new HeapByteBuf(arrayIn);

		final ByteBuf out = new HeapByteBuf(256);

		//

		final long timeStart = System.nanoTime();

		for (int k = 0; k < COUNT_TEST; k++) {

			in.resetReaderIndex();
			out.resetWriterIndex();

			zipIn.encode(null, in, out);

		}

		final long timeFinish = System.nanoTime();
		final long timeChange = timeFinish - timeStart;
		final long speed = timeChange / COUNT_TEST;

		log.debug("msg zip pack speed, nano : {}", speed);

	}

}
