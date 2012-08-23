package com.barchart.translator.cfn.data.enums;

import com.barchart.translator.common.fields.Keyable;

public enum CFNExpirationMonth implements Keyable<Object>{

	JAN('F'),
	FEB('G'),
	MAR('H'),
	APR('J'),
	MAY('K'),
	JUN('M'),
	JUL('N'),
	AUG('Q'),
	SEP('U'),
	OCT('V'),
	NOV('X'),
	DEC('Z');
	
	private final byte code;

	CFNExpirationMonth(char charCode) {
		this.code = (byte) charCode;
	}
	
	public int getCode() {
		return code;
	}

	public static CFNExpirationMonth fromCode(byte code2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getKey() {
		return code;
	}
	
}
