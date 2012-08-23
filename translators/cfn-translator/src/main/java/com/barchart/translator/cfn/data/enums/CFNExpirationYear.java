package com.barchart.translator.cfn.data.enums;

import com.barchart.translator.common.fields.Keyable;

public enum CFNExpirationYear implements Keyable<Object> {

	YEAR_0('0'),
	YEAR_1('1'),
	YEAR_2('2'),
	YEAR_3('3'),
	YEAR_4('4'),
	YEAR_5('5'),
	YEAR_6('6'),
	YEAR_7('7'),
	YEAR_8('8'),
	YEAR_9('9');
	
	
	private final byte code;

	CFNExpirationYear(char charCode) {
		this.code = (byte) charCode;
	}

	public byte getCode() {
		return code;
	}

	public static CFNExpirationYear fromCode(byte code2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getKey() {
		return code;
	}
	
	
}
