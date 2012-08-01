package com.barchart.proto.util;

public class DateTimeValue extends DateOnlyValue {

	private final byte hour;
	private final byte minute;
	private final byte second;
	private final short millis;

	public DateTimeValue(//
			final int year, //
			final int month, //
			final int day, //
			final int hour, //
			final int minute, //
			final int second, //
			final int millis //
	) {
		super(year, month, day);
		this.hour = (byte) hour;
		this.minute = (byte) minute;
		this.second = (byte) second;
		this.millis = (short) millis;
	}

	public byte getHour() {
		return hour;
	}

	public byte getMinute() {
		return minute;
	}

	public byte getSecond() {
		return second;
	}

	public short getMillis() {
		return millis;
	}

}
