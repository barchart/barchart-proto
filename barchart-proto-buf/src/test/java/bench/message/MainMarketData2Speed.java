package bench.message;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bench.Util;

import com.barchart.proto.buf.Base;
import com.barchart.proto.buf.Decimal;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketData2;
import com.barchart.proto.buf.MarketDataEntry;
import com.barchart.proto.buf.MarketDataEntry2;
import com.barchart.proto.buf.MessageCodec;
import com.barchart.proto.buf.MessageVisitor;

/** measure message heap size */
public class MainMarketData2Speed {

	private static final Logger log = LoggerFactory
			.getLogger(MainMarketData2Speed.class);

	static final int COUNT_TEST = 100 * 1000;

	static final int COUNT_ENTRY = 20;

	static final long A_LONG = Long.MAX_VALUE - 3;
	static final int AN_INT = Integer.MAX_VALUE - 3;

	static MarketDataEntry buildFieldHard() {

		final MarketDataEntry.Builder builder = MarketDataEntry.newBuilder();

		builder.setType(MarketDataEntry.Type.OPEN);
		builder.setAction(MarketDataEntry.Action.EDIT);

		builder.setMarketId(A_LONG);
		builder.setSequence(A_LONG);

		final Decimal price = Decimal.newBuilder().setMantissa(A_LONG)
				.setExponent(AN_INT).build();

		final Decimal size = Decimal.newBuilder().setMantissa(A_LONG)
				.setExponent(AN_INT).build();

		builder.setPrice(price);
		builder.setPrice(size);
		builder.setIndex(A_LONG);

		builder.setTimeStamp(A_LONG);
		builder.setTradeDate(AN_INT);

		return builder.build();

	}

	static MarketDataEntry2 buildFieldEasy() {

		final MarketDataEntry2.Builder builder = MarketDataEntry2.newBuilder();

		builder.setType(MarketDataEntry2.Type.BID);
		builder.setAction(MarketDataEntry2.Action.ADD);

		// builder.setMarketId(AN_INT);
		// builder.setSequence(AN_INT);

		builder.setPriceMantissa(12345);
		// builder.setSizeExponent(123);
		builder.setIndex(123);

		// builder.setTimeStamp(123);
		// builder.setTradeDate(123);

		return builder.build();

	}

	static MarketData buildMessageHard() {

		final MarketData.Builder builder = MarketData.newBuilder();

		builder.setType(MarketData.Type.SNAPSHOT);
		builder.setMarketId(A_LONG);

		// builder.setTimeStamp(A_LONG);
		// builder.setTradeDate(AN_INT);

		for (int k = 0; k < COUNT_ENTRY; k++) {
			builder.addEntry(buildFieldHard());
		}

		return builder.build();

	}

	static MarketData2 buildMessageEasy() {

		final MarketData2.Builder builder = MarketData2.newBuilder();

		builder.setType(MarketData2.Type.SNAPSHOT);
		builder.setMarketId(AN_INT);

		for (int k = 0; k < COUNT_ENTRY; k++) {
			builder.addEntry(buildFieldEasy());
		}

		return builder.build();

	}

	static MarketData2 buildMessage() {

		return buildMessageEasy();
	}

	static Base buildBase() {

		final MarketData2 message = buildMessageEasy();

		final Base base = MessageCodec.encode(message);

		return base;
	}

	static void testWireSize() throws Exception {

		final Base message = buildBase();

		final int wireSize = message.getSerializedSize();

		log.debug("msg wireSize, total, byte : {}", wireSize);
		log.debug("msg wireSize, entry, byte : {}", wireSize / COUNT_ENTRY);

		//

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		message.writeTo(output);

		final byte[] arrayIn = output.toByteArray();
		final byte[] arrayOut = Util.compress(arrayIn);

		log.debug("zip arrayIn.length  : {}", arrayIn.length);
		log.debug("zip arrayOut.length : {}", arrayOut.length);
		log.debug("zip ratio : {}", arrayIn.length / arrayOut.length);

	}

	static void testSpeedBuild() {

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			final Base message = buildBase();
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			final Base message = buildBase();
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
			messageOut.writeTo(output);
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			messageOut.writeTo(new ByteArrayOutputStream());
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg write speed, nano : {}", buildSpeed);

	}

	static void testSpeedDecode() throws Exception {

		final MessageVisitor visitor = new MessageVisitor.Adaptor();

		final Base messageOut = buildBase();

		final ByteArrayOutputStream output = new ByteArrayOutputStream();

		messageOut.writeTo(output);

		final byte[] array = output.toByteArray();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			MessageCodec.decode(visitor, null, array);
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			MessageCodec.decode(visitor, null, array);
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg decode speed, nano : {}", buildSpeed);

	}

	static void testSpeedAll() throws Exception {

		final MessageVisitor<Void> visitor = new MessageVisitor.Adaptor<Void>();

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {

			final Base messageOut = buildBase();

			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			messageOut.writeTo(output);

			final byte[] array = output.toByteArray();

			MessageCodec.decode(visitor, null, array);

		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {

			final Base messageOut = buildBase();

			final ByteArrayOutputStream output = new ByteArrayOutputStream();

			messageOut.writeTo(output);

			final byte[] array = output.toByteArray();

			MessageCodec.decode(visitor, null, array);

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

		testSpeedWrite();

		testSpeedDecode();

		testSpeedAll();

		log.debug("done");

	}

}
