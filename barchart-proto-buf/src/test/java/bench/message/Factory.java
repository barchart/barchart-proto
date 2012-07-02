package bench.message;

import java.util.concurrent.atomic.AtomicLong;

import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;

public class Factory {

	static final int ENTRY_COUNT = 20;

	static final long A_LONG = Long.MAX_VALUE - 3;
	static final int AN_INT = Integer.MAX_VALUE - 3;

	static final long RANGE = 1234567890;
	static final AtomicLong RANDOM = new AtomicLong(RANGE);

	static int getIntMod(final int size) {
		return (int) RANDOM.getAndIncrement() % size;
	}

	static long getLongMod(final long size) {
		return RANDOM.getAndIncrement() % size;
	}

	static final MarketDataEntry.Descriptor[] DESC_VALS = MarketDataEntry.Descriptor
			.values();

	static MarketDataEntry.Descriptor getDescriptorMod(final int size) {
		final int index = getIntMod(size) % DESC_VALS.length;
		return DESC_VALS[index];
	}

	public enum Mode {

		UPDATE_SIMPLE, //
		UPDATE_COMPLEX, //

		SNAPSHOT_SIMPLE, //
		SNAPSHOT_COMPLEX, //

		//

		SNAPSHOT_SIMPLE_DESC, //
		UPDATE_SIMPLE_DESC, //

	}

	public static MarketDataEntry newEntry(final Mode mode) {

		switch (mode) {

		case SNAPSHOT_SIMPLE: {

			final MarketDataEntry.Builder builder = MarketDataEntry
					.newBuilder();

			builder.setType(MarketDataEntry.Type.BID);
			builder.setAction(MarketDataEntry.Action.ADD);

			// builder.setMarketId(AN_INT); // SHARED
			builder.setSequence(getIntMod(10)); // OFFSET

			builder.setPriceMantissa(getLongMod(50 * 1000));
			// builder.setSizeExponent(123); // SHARED

			builder.setIndex(getIntMod(10));

			// builder.setTimeStamp(123); // SHARED
			// builder.setTradeDate(123); // SHARED

			return builder.build();

		}

		case SNAPSHOT_SIMPLE_DESC: {

			final MarketDataEntry.Builder builder = MarketDataEntry
					.newBuilder();

			builder.setType(MarketDataEntry.Type.BID);
			builder.setAction(MarketDataEntry.Action.ADD);

			// builder.setMarketId(AN_INT); // SHARED
			builder.setSequence(getIntMod(10)); // OFFSET

			builder.setPriceMantissa(getLongMod(50 * 1000));
			// builder.setSizeExponent(123); // SHARED

			builder.setIndex(getIntMod(10));

			// builder.setTimeStamp(123); // SHARED
			// builder.setTradeDate(123); // SHARED

			// XXX
			builder.addDescriptor(MarketDataEntry.Descriptor.BOOK_IMPLIED);

			return builder.build();

		}

		case SNAPSHOT_COMPLEX: {

			final MarketDataEntry.Builder builder = MarketDataEntry
					.newBuilder();

			builder.setType(MarketDataEntry.Type.BID);
			builder.setAction(MarketDataEntry.Action.ADD);

			// builder.setMarketId(AN_INT); // SHARED
			builder.setSequence(getIntMod(100)); // OFFSET

			builder.setPriceMantissa(getLongMod(1000 * 1000));
			builder.setSizeExponent(getIntMod(100)); //

			builder.setIndex(getIntMod(1000));

			// builder.setTimeStamp(123); // SHARED
			// builder.setTradeDate(123); // SHARED

			return builder.build();

		}

		case UPDATE_SIMPLE: {

			final MarketDataEntry.Builder builder = MarketDataEntry
					.newBuilder();

			builder.setType(MarketDataEntry.Type.TRADE);
			builder.setAction(MarketDataEntry.Action.ADD);

			builder.setMarketId(getIntMod(100 * 1000));
			builder.setSequence(getIntMod(10)); // OFFSET

			builder.setPriceMantissa(getLongMod(50 * 1000));
			builder.setSizeExponent(getIntMod(10));

			builder.setIndex(getIntMod(10));

			builder.setTimeStamp(getIntMod(1000)); // OFFSET
			builder.setTradeDate(getIntMod(200)); // NOT SHARED

			return builder.build();

		}

		case UPDATE_SIMPLE_DESC: {

			final MarketDataEntry.Builder builder = MarketDataEntry
					.newBuilder();

			builder.setType(MarketDataEntry.Type.TRADE);
			builder.setAction(MarketDataEntry.Action.ADD);

			builder.setMarketId(getIntMod(100 * 1000));
			builder.setSequence(getIntMod(10)); // OFFSET

			builder.setPriceMantissa(getLongMod(50 * 1000));
			builder.setSizeExponent(getIntMod(10));

			builder.setIndex(getIntMod(10));

			builder.setTimeStamp(getIntMod(1000)); // OFFSET
			builder.setTradeDate(getIntMod(200)); // NOT SHARED

			// XXX
			builder.addDescriptor(getDescriptorMod(7));
			builder.addDescriptor(getDescriptorMod(7));
			builder.addDescriptor(getDescriptorMod(7));

			return builder.build();

		}

		case UPDATE_COMPLEX: {

			final MarketDataEntry.Builder builder = MarketDataEntry
					.newBuilder();

			builder.setType(MarketDataEntry.Type.TRADE);
			builder.setAction(MarketDataEntry.Action.ADD);

			builder.setMarketId(getIntMod(1000 * 1000)); // NOT SHARED
			builder.setSequence(getIntMod(1000 * 1000)); // NOT SHARED

			builder.setPriceMantissa(getLongMod(1000 * 1000)); // NOT SHARED
			builder.setSizeExponent(getIntMod(1000)); // NOT SHARED

			builder.setIndex(getIntMod(10 * 1000));

			builder.setTimeStamp(getLongMod(86400000)); // // NOT SHARED
			builder.setTradeDate(getIntMod(365)); // NOT SHARED

			return builder.build();

		}

		}

		return null;

	}

	public static MarketData newMessage(final Mode mode) {

		switch (mode) {

		case SNAPSHOT_SIMPLE_DESC:
		case SNAPSHOT_SIMPLE: {

			final MarketData.Builder builder = MarketData.newBuilder();

			builder.setType(MarketData.Type.SNAPSHOT);

			builder.setMarketId(getIntMod(100000));

			builder.setTimeStamp(getLongMod(86400000 / 10));
			builder.setTradeDate(getIntMod(200));

			for (int k = 0; k < ENTRY_COUNT; k++) {
				builder.addEntry(newEntry(mode));
			}

			return builder.build();

		}

		case SNAPSHOT_COMPLEX: {

			final MarketData.Builder builder = MarketData.newBuilder();

			builder.setType(MarketData.Type.SNAPSHOT);

			builder.setMarketId(getLongMod(RANGE / 10));

			builder.setTimeStamp(getLongMod(86400000));
			builder.setTradeDate(getIntMod(200));

			for (int k = 0; k < ENTRY_COUNT; k++) {
				builder.addEntry(newEntry(mode));
			}

			return builder.build();

		}

		case UPDATE_SIMPLE_DESC:
		case UPDATE_SIMPLE: {

			final MarketData.Builder builder = MarketData.newBuilder();

			builder.setType(MarketData.Type.UPDATE);

			// builder.setMarketId(getLongMod(RANGE / 10)); // NOT SHARED

			builder.setTimeStamp(getLongMod(86400000 / 10)); // SHARED BASE

			// builder.setTradeDate(getIntMod(200)); // NOT SHARED

			for (int k = 0; k < ENTRY_COUNT; k++) {
				builder.addEntry(newEntry(mode));
			}

			return builder.build();

		}

		case UPDATE_COMPLEX: {

			final MarketData.Builder builder = MarketData.newBuilder();

			builder.setType(MarketData.Type.UPDATE);

			// builder.setMarketId(getLongMod(RANGE / 10)); // NOT SHARED

			// builder.setTimeStamp(getLongMod(86400000 / 10)); // NOT SHARED

			// builder.setTradeDate(getIntMod(200)); // NOT SHARED

			for (int k = 0; k < ENTRY_COUNT; k++) {
				builder.addEntry(newEntry(mode));
			}

			return builder.build();

		}

		}

		return null;

	}
}
