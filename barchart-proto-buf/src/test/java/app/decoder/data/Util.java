package app.decoder.data;

import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;

import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;
import com.barchart.util.values.provider.ValueBuilder;
import com.barchart.util.values.provider.ValueConst;

/** special contract on message-entry nesting */
public class Util {

	/** entry overrides message; 0 is default */
	public static int getPriceExponent(final MarketData message,
			final MarketDataEntry entry) {

		if (entry.hasPriceExponent()) {
			return entry.getPriceExponent();
		}

		if (message.hasPriceExponent()) {
			return message.getPriceExponent();
		}

		return 0;

	}

	/** entry overrides message; 0 is default */
	public static int getSizeExponent(final MarketData message,
			final MarketDataEntry entry) {

		if (entry.hasSizeExponent()) {
			return entry.getSizeExponent();
		}

		if (message.hasSizeExponent()) {
			return message.getSizeExponent();
		}

		return 0;

	}

	/** NULL_PRICE for missing */
	public static PriceValue getPrice(final MarketData message,
			final MarketDataEntry entry) {

		if (entry.hasPriceMantissa()) {

			final long mantissa = entry.getPriceMantissa();
			final int exponent = getPriceExponent(message, entry);

			return ValueBuilder.newPrice(mantissa, exponent);

		} else {

			return ValueConst.NULL_PRICE;

		}

	}

	/** NULL_SIZE for missing; need change XXX type */
	public static SizeValue getSize(final MarketData message,
			final MarketDataEntry entry) {

		if (entry.hasSizeMantissa()) {

			final long mantissa = entry.getPriceMantissa();
			final int exponent = getPriceExponent(message, entry); // XXX

			return ValueBuilder.newSize(mantissa);

		} else {

			return ValueConst.NULL_SIZE;
		}

	}

	/** entry is offset to message; default XXX for missing */
	public static TimeValue getTimeStamp(final MarketData message,
			final MarketDataEntry entry) {

		final boolean isValid = message.hasTimeStamp() || entry.hasTimeStamp();

		long millisUTC;

		if (isValid) {

			millisUTC = message.getTimeStamp() + entry.getTimeStamp();

		} else {

			millisUTC = System.currentTimeMillis(); // XXX

		}

		return ValueBuilder.newTime(millisUTC);

	}

	/** entry is offset to message; default XXX for missing */
	public static TimeValue getTradeDate(final MarketData message,
			final MarketDataEntry entry) {

		final boolean isValid = message.hasTradeDate() || entry.hasTradeDate();

		if (isValid) {

			final int days = message.getTradeDate() + entry.getTradeDate();

			final MutableDateTime epoch = new MutableDateTime(DateTimeZone.UTC);

			epoch.addDays(days);

			final long millisUTC = epoch.getMillis();

			return ValueBuilder.newTime(millisUTC);

		} else {

			return getTimeStamp(message, entry); // XXX

		}

	}

	public static boolean hasMarketId(final MarketData message,
			final MarketDataEntry entry) {

		return message.hasMarketId() || entry.hasMarketId();

	}

	/** entry overrides message; 0 for missing */
	public static long getMarketId(final MarketData message,
			final MarketDataEntry entry) {

		if (entry.hasMarketId()) {
			return entry.getMarketId();
		}

		if (message.hasMarketId()) {
			return message.getMarketId();
		}

		return 0;

	}

	/** entry is offset to message; 0 for missing */
	public static long getSequence(final MarketData message,
			final MarketDataEntry entry) {

		return message.getSequence() + entry.getSequence();

	}

}
