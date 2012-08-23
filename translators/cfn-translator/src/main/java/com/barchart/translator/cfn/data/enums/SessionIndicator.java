package com.barchart.translator.cfn.data.enums;

public enum SessionIndicator {

	/*
	 * 6.24 SESSION INDICATOR Reserved for Future Use 1 Byte, Alphabetic, Space
	 * filled. The Session Indicator identifies the session in which the trade
	 * or quote originated. CODE VALUE a (lower case) Morning (A.M.) session
	 * (not used) Space filled Normal session
	 */

	Morning_Session('a'), // space
	Normal_Session(' '), // space

	;

	public byte getCode() {
		return code;
	}

	private final byte code;

	SessionIndicator(char code) {
		this.code = (byte) code;
	}

	public static SessionIndicator fromCode(byte code) {

		switch (code) {
		case 'a':
			return Morning_Session;
		case ' ': // space
			return Normal_Session;
		default:
			throw new IllegalArgumentException(String.format("unknown session indicator code: %1$c (ascii %1$d)", code));
		}
	}
}
