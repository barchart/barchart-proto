package com.barchart.translator.cfn.data;

public interface CFNMessageHeader {

	public ExchangeID getExchangeID();

	public MessageCategory getMessageCategory();

	public MessageType getMessageType();
	
	public int getMessageSequenceNumber();
	
	public int getTime();

	public enum MessageCategory {

		OPTIONS_ON_FUTURES_LAST_SALE('a'), //
		OPTIONS_ON_FUTURES_END_OF_DAY_SUMMARY('f'), //
		OPTIONS_ON_FUTURES_QUOTE_WITH_SIZE('k'), //
		LAST_SALE('m'), //
		OPEN_INTEREST('n'), //
		END_OF_DAY_SUMMARY('o'), //
		QUOTE_WITH_SIZE('r'), //
		SETTLEMENT_PRICE('s'), //
		BOOK_DEPTH_FULL_REFRESH('t'), //
		BOOK_DEPTH_UPDATE('u'), //
		ADMINISTRATIVE('C'), //
		CONTROL('H'), //
		UNDERLYING_VALUE_MESSAGE('Y'), //
		UNKNOWN('?');//

		private final byte code;

		MessageCategory(char charCode) {
			this.code = (byte) charCode;
		}

		public byte getCode() {
			return code;
		}

		public static MessageCategory fromCode(byte code2) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public enum MessageType {
		/*
		 * Indicates that the transaction was a regular sale and was made
		 * without stated conditions
		 */
		REGULAR(' '), //

		/**
		 * Transaction previously reported is now to be cancelled
		 */
		CANCELLED('A'), //

		/**
		 * Transaction is being reported late and is out of sequence; i.e.,
		 * later transactions have been reported for the particular contract.
		 */
		OUT_OF_SEQUENCE('B'), //

		/**
		 * Transaction is being reported late, but is in the correct sequence;
		 * i.e., no later transactions have been reported for the particular
		 * contract.
		 */
		LATE('D'), //

		/**
		 * Transaction was the first one (opening) reported this day for the
		 * particular contract. Although later transactions have been reported,
		 * this transaction is now to be cancelled.
		 */
		CNCO('E'), //

		/**
		 * Transaction is a late report of the opening trade and is out of
		 * sequence; i.e., other transactions have been reported for the
		 * particular contract.
		 */
		OPEN('F'), //

		/**
		 * Transaction is a late report of the opening trade, but is in the
		 * correct sequence; i.e., no other transactions have been reported for
		 * the particular contract.
		 */
		OPNL('H'), //

		/**
		 * Transaction is a reopening of a contract in which trading has been
		 * previously halted. Prefix appears solely for information; process as
		 * a regular transaction.
		 */
		REOP('J'), //

		/**
		 * Transaction represents a trade in two contract months in the same
		 * class (a buy and a sell in the same class). Prefix appears solely for
		 * information; process as a regular transaction.
		 */
		SPRD('L'), //

		/**
		 * Block Trade
		 */
		BLOCK_TRADE('R'), //

		/**
		 * Exchange Future for Physical
		 */
		EXCHANGE_FUTURE_FOR_PHYSICAL('S');

		private final byte code;

		MessageType(char charCode) {
			this.code = (byte) charCode;
		}

		public byte getCode() {
			return code;
		}

		public static MessageType fromCode(byte code2) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	public enum ExchangeID {
		CBOE((byte) 'C');

		private final byte code;

		ExchangeID(byte code) {
			this.code = code;
		}

		public byte getCode() {
			return code;
		}

		public static ExchangeID fromCode(byte code) {
			return null;
		}
	}

}
