package com.barchart.translator.nyl.data.enums;

public enum NYLMessageType {

	MARKET_UPDATE_V1(701),
	VALUE_ADDED_PARAMETERS(771);

	private final int code;

	NYLMessageType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static NYLMessageType fromCode(int code) {
		for (NYLMessageType packetType : values()) {
			if (packetType.getCode() == code) {
				return packetType;
			}
		}
		throw new IllegalArgumentException("Unknown message type: " + code);
	}

}
