package com.barchart.translator.nyl.data;

import com.barchart.translator.nyl.data.enums.NYLMessageType;
import com.barchart.translator.nyl.data.enums.NYLUpdateType;

public interface NYLValueAddedParameters {

	/**
	 * Length of the message body, excluding the 2 byte MsgSize field.
	 */
	public int getMsgSize();

	/**
	 * Numeric message type identifier:
	 * 
	 * ‘771’ - Value-Added Parameters v1
	 */
	public NYLMessageType getMsgType();

	/**
	 * Milliseconds since the previous Sunday 00:00 (UTC)
	 */
	public long getSourceTime();

	/**
	 * Type of the security code ‘8’ - AMR
	 */
	public int getSecurityIDSource();

	/**
	 * Security code (source of code indicated by SecurityIDSource field) If
	 * SecurityIDSource = 8 then this field will contain the AMR code
	 */
	public String getSecurityID();

	/**
	 * Number of updates. Indicates number of times the following group of three
	 * fields (Update Type, Price and Volume) will be repeated in the message.
	 */
	public int getUpdateCount();

	
	public interface Entry {

		/**
		 * Type of update
		 */
		public UpdateType getUpdateType();
		
		
		/**
		 * Price. See notes below for full explanation of contents.
		 */
		public long getPrice();

		/**
		 * Volume. See notes below for full explanation of contents.
		 */
		public long getVolume();
		
	}

	public enum UpdateType {

		DAILY_HIGH(1),
		DAILY_LOW(2),
		YEARLY_HIGH(3),
		YEARLY_LOW(4),
		LIFETIME_HIGH(5),
		LIFETIME_LOW(6),
		CUMULATIVE_VOLUME_BOOK(7),
		CUMULATIVE_VOLUME_OTC(8),
		CUMULATIVE_VOLUME(9),
		OPEN_PRICE(10),
		TRADE_COUNT(11),
		PERCENTAGE_CHANGE(12);
		
		private final int code;
		
		UpdateType(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
		
		public static UpdateType fromCode(int code) {
			for (UpdateType value : values()) {
				if (value.getCode() == code) {
					return value;
				}
			}
			throw new IllegalArgumentException("Unknown code: " + code);
		}
	}

}
