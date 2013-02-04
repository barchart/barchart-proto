/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package util;

class Decimal {

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
