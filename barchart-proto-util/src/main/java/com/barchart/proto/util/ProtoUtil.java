package com.barchart.proto.util;

public final class ProtoUtil {

	public static int YEAR_BITS = 10;
	public static int YEAR_MASK = 1 << YEAR_BITS;

	public static int MONTH_BITS = 4;
	public static int MONTH_MASK = 1 << MONTH_BITS;

	public static int DAY_BITS = 5;
	public static int DAY_MASK = 1 << DAY_BITS;

	public static int HOUR_BITS = 5;
	public static int HOUR_MASK = 1 << HOUR_BITS;

	public static int MINUTE_BITS = 6;
	public static int MINUTE_MASK = 1 << MINUTE_BITS;

	public static int SECOND_BITS = 6;
	public static int SECOND_MASK = 1 << SECOND_BITS;

	public static int MILLIS_BITS = 10;
	public static int MILLIS_MASK = 1 << MILLIS_BITS;

	/** year.month.date in bits : 10.4.5 */
	public static DateOnlyValue decodeTradeDate(int value) {

		final int day = value & DAY_MASK;
		value >>= DAY_BITS;

		final int month = value & MONTH_MASK;
		value >>= MONTH_BITS;

		final int year = value & YEAR_MASK;

		return new DateOnlyValue(year, month, day);

	}

	public static int encodeTradeDate(final DateOnlyValue value) {
		return encodeTradeDate(value.getYear(), value.getMonth(),
				value.getDay());
	}

	public static int encodeTradeDate(final int year, final int month,
			final int day) {
		return (year << (MONTH_BITS + DAY_BITS)) | (month << DAY_BITS) | (day);
	}

	private ProtoUtil() {
	}

}
