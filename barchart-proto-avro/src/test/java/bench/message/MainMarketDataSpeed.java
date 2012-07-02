package bench.message;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.avro.Base;
import com.barchart.proto.avro.DataEntry;
import com.barchart.proto.avro.DataType;
import com.barchart.proto.avro.MarketData;

/** measure message heap size */
public class MainMarketDataSpeed {

	private static final Logger log = LoggerFactory
			.getLogger(MainMarketDataSpeed.class);

	static final int COUNT_TEST = 100 * 1000;

	static final int COUNT_ENTRY = 20;

	static final long A_LONG = Long.MAX_VALUE - 3;
	static final int AN_INT = Integer.MAX_VALUE - 3;

	static DataEntry buildFieldEasy() {

		final DataEntry.Builder builder = DataEntry.newBuilder();

		builder.setType(DataType.BID);

		return builder.build();

	}

	static MarketData buildMessageEasy() {

		final MarketData.Builder builder = MarketData.newBuilder();

		final List<DataEntry> entryList = new ArrayList<DataEntry>(COUNT_ENTRY);

		for (int k = 0; k < COUNT_ENTRY; k++) {
			entryList.add(buildFieldEasy());
		}

		builder.setEntryList(entryList);

		return builder.build();

	}

	static MarketData buildMessage() {

		return buildMessageEasy();
	}

	static Base buildBase() {

		final MarketData message = buildMessageEasy();

		final Base.Builder builder = Base.newBuilder();

		builder.setMessage(message);

		return builder.build();
	}

	static void testWireSize() throws Exception {

		final Base base = buildBase();

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

	}

	static void testSpeedBuild() {

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			final Base base = buildBase();
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			final Base base = buildBase();
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg build speed, nano : {}", buildSpeed);

	}

	static void testSpeedWrite() throws Exception {

		final Base messageOut = buildBase();

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			output.reset();
			// messageOut.writeTo(output);
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			// messageOut.writeTo(new ByteArrayOutputStream());
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg write speed, nano : {}", buildSpeed);

	}

	static void testSpeedDecode() throws Exception {

		// final MessageVisitor<Void> visitor = new
		// MessageVisitor.Adaptor<Void>();

		final Base messageOut = buildBase();

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		// messageOut.writeTo(output);

		final byte[] array = output.toByteArray();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			// MessageCodec.decode(visitor, null, array);
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			// MessageCodec.decode(visitor, null, array);
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg decode speed, nano : {}", buildSpeed);

	}

	static void testSpeedAll() throws Exception {

		// final MessageVisitor<Void> visitor = new
		// MessageVisitor.Adaptor<Void>();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {

			final Base messageOut = buildBase();

			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			// messageOut.writeTo(output);

			final byte[] array = output.toByteArray();

			// MessageCodec.decode(visitor, null, array);

		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {

			final Base messageOut = buildBase();

			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			// messageOut.writeTo(output);

			final byte[] array = output.toByteArray();

			// MessageCodec.decode(visitor, null, array);

		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg build/write/decode speed, nano : {}", buildSpeed);

	}

	public static void main(final String... args) throws Exception {

		log.debug("init");

		log.debug("COUNT_TEST  : {}", COUNT_TEST);
		log.debug("COUNT_ENTRY : {}", COUNT_ENTRY);

		testWireSize();

		testSpeedBuild();

		// testSpeedWrite();

		// testSpeedDecode();

		// testSpeedAll();

		log.debug("done");

	}

}
