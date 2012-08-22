package com.barchart.translator.nyl.data.enums;


public enum NYLUpdateType {
	BEST_BID(1),
	BEST_OFFER(2),
	BID(3),
	OFFER(4),
	TOTAL_TRADED_VOLUME(5),
	CONVENTIONAL_TRADE(6),
	BLOCK_TRADE(7),
	BASIS_TRADE(8),
	PROFESSIONAL_TRADE(9),
	GUARANTEED_CROSS_TRADE(10),
	AGAINST_ACTUAL_TRADE(11),
	ASSET_ALLOCATION_TRADE(12),
	EXTERNAL_MATCH_TRADE(13),
	EXCHANGE_FOR_SWAP_TRADE(14),
	EXCHANGE_FOR_PHYSICAL_TRADE(15),
	STRATEGY_LEG_TRADE_PRICE(16),
	IMPLIED_BID(17),
	IMPLIED_OFFER(18),
	INDICATIVE_OPEN_PRICE(19);
	
	private final int code;
	
	NYLUpdateType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static NYLUpdateType fromCode(int code) {
		for (NYLUpdateType updateType : values()) {
			if (updateType.getCode() == code) {
				return updateType;
			}
		}
		throw new IllegalArgumentException("Unknown update type: " + code);
	}
}
