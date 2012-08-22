package com.barchart.translator.nyl.jform.facade;

public interface MarketUpdate {
	
	public int getMsgSize();

	public int getMsgType();

	public long getSourceTime();

	public long getSereiesSequenceNumber();

	public int getSecurityIDSource();

	public String getSecurityID();

	public int getSnapshotFlag();

	public int getUpdateCount();

	public interface Entry {
		public UpdateType getUpdateType();

		public long getPrice();

		public long getVolume();
	}

	public enum UpdateType {
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
		
		UpdateType(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
		
		public static UpdateType fromCode(int code) {
			for (UpdateType updateType : values()) {
				if (updateType.getCode() == code) {
					return updateType;
				}
			}
			throw new IllegalArgumentException("Unknown update type: " + code);
		}
	}
	
	
}
