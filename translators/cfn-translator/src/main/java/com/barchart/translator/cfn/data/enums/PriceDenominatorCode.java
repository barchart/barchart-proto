package com.barchart.translator.cfn.data.enums;

import com.barchart.translator.common.fields.Keyable;



public enum PriceDenominatorCode implements Keyable<Object> {

	/*
	 * 6.21 PRICE DENOMINATOR CODE and PREMIUM PRICE DENOMINATOR CODE 1 Byte,
	 * Alphanumeric. The Price Denominator Code field indicates the position of
	 * the floating decimal point.
	 */

	/*
	 * 6.26 UNDERLYING PRICE DENOMINATOR CODE 1 Byte, Alphanumeric. The
	 * Underlying Price Denominator field indicates the position of the floating
	 * decimal point.
	 */

	/* NOTE: 'P' stands for "Power"; so, P7 means 10 in the power of 7 */

	P1('A', 10), //
	P2('B', 100), //
	P3('C', 1000), // 1K
	P4('D', 10000), // 10K
	P5('E', 100000), // 100K
	P6('F', 1000000), // 1M
	P7('G', 10000000), // 10M
	P8('H', 100000000), // 100M
	P9('I', 1000000000), // 1T

	;

	private final byte code;
	
	private final int denominator;

	public byte getCode() {
		return code;
	}

	public int getDenominator() {
		return denominator;
	}

	PriceDenominatorCode(char code, int denominator) {
		this.code = (byte) code;
		this.denominator = denominator;
	}

	public static PriceDenominatorCode fromCode(byte code2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getKey() {
		return code;
	}

	
	
}
