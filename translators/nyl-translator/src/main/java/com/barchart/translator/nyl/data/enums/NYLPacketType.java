package com.barchart.translator.nyl.data.enums;

public enum NYLPacketType {

	GENERIC_DERIVATIVES_MESSAGE(799);
	
	
	private final int code;

	NYLPacketType(int code) {
		this.code = code;
	}
	
	
	public int getCode() {
		return code;
	}
	
	public static NYLPacketType fromCode(int code) {
		for (NYLPacketType packetType : values()) {
			if (packetType.getCode() == code) {
				return packetType;
			}
		}
		throw new IllegalArgumentException("Unknown packet type: " + code);
	}
}
