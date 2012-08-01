package com.barchart.proto.util;

public class DateOnlyValue {

	private final short year;
	private final byte month;
	private final byte day;

	public DateOnlyValue(//
			final int year, //
			final int month, //
			final int day //
	) {
		this.year = (short) year;
		this.month = (byte) month;
		this.day = (byte) day;
	}

	public short getYear() {
		return year;
	}

	public byte getMonth() {
		return month;
	}

	public byte getDay() {
		return day;
	}

}
