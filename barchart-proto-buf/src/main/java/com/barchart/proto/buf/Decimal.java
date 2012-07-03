package com.barchart.proto.buf;

public class Decimal {

	public static Decimal NULL = new Decimal(0, 0);

	public boolean isNull() {
		return this == NULL;
	}

	private final long mantissa;
	private final int exponent;

	public Decimal(final long mantissa, final int exponent) {
		this.mantissa = mantissa;
		this.exponent = exponent;
	}

	public long getMantissa() {
		return mantissa;
	}

	public int getExponent() {
		return exponent;
	}

}
