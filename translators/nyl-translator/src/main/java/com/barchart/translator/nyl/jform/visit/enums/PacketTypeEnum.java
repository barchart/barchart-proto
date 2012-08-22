package com.barchart.translator.nyl.jform.visit.enums;

import com.barchart.translator.common.util.Keyable;

public enum PacketTypeEnum implements Keyable {

	GENERIC_DERIVATIVES_MESSAGE(799);
	
	private final int code;

	PacketTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	@Override
	public Object getKey() {
		return Integer.valueOf(code);
	}
	
	
	
	
}
