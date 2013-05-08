/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package vendor.opra;

/**
 * 
 * OPRA has single fix/fast template, with most fields optional; message is flat
 * (no nested groups)
 * 
 * see src/test/resources/verndor/opra/opra-templates.xml
 * 
 * note: no market sequence numbers
 */
public enum FixTagOPRA {

	MESSAGE_CATEGORY, // base

	MESSAGE_TYPE, // base

	/**
	 * The Participant ID field is a 1 Byte, ASCII character that identifies the
	 * Participant or Processor that initiated the message (* = application
	 * pending): CODE A B C I M N O Q T W X Z VALUE NYSE AMEX Boston Options
	 * Exchange Chicago Board Options Exchange International Securities Exchange
	 * Miami International Securities Exchange* NYSE ARCA Options Price
	 * Reporting Authority NASDAQ Stock Market NASDAQ OMX BX Options* C2 NASDAQ
	 * OMX PHLX BATS
	 */
	PARTICIPANT_ID, // inst, exchange-id

	RETRANSMISSION_REQUESTER, // ?

	MESSAGE_SEQUENCE_NUMBER, // base

	TIME, // base; packet time-stamp

	SECURITY_SYMBOL, // inst

	EXPIRATION_DATE, // inst

	YEAR, // inst

	STRIKE_PRICE_CODE, // inst

	STRIKE_PRICE_DENOMINATOR_CODE, // inst

	EXPLICIT_STRIKE_PRICE, // inst

	/**
	 * The Premium Price Denominator Code field indicates the position of the
	 * floating decimal point within either the Premium Price, Bid Price, Offer
	 * Price, Open Price, High Price, Low Price, or Last Price fields.
	 */
	PREMIUM_PRICE_DENOMINATOR_CODE, // data; price exponent

	BID_PRICE, // data; bid

	BID_SIZE, // data; bid

	OFFER_PRICE, // data; ask

	OFFER_SIZE, // data; ask

	SESSION_INDICATOR, // data

	BBO_INDICATOR, // data; entry.index = top of book

	BEST_BID_PARTICIPANT_ID, // inst

	BEST_BID_PRICE_DENOMINATOR_CODE, //

	BEST_BID_PRICE, // data; bid; price mantissa

	BEST_BID_SIZE, // data; bid;

	BEST_OFFER_PARTICIPANT_ID, // inst

	BEST_OFFER_PRICE_DENOMINATOR_CODE, // data; price exponent

	BEST_OFFER_PRICE, // data; ask; price mantissa

	BEST_OFFER_SIZE, // data; ask

	RESERVED_FIELD_1, // discard

	RESERVED_FIELD_2, // discard

	RESERVED_FIELD_3, // discard

	OPEN_INT_VOLUME, // data

	VOLUME, // data

	/**
	 * 4 Bytes, signed integer. The Premium Price is the whole and decimal
	 * portion of the Premium Price information with the Premium Price
	 * Denominator Code determining the location of the decimal point.
	 * Represents the price of an option contract, determined in the competitive
	 * marketplace, which the buyer of the option pays to the option writer for
	 * the rights conveyed by the option contract.
	 */
	PREMIUM_PRICE, // data; trade

	/**
	 * 4 Bytes, signed integer. The Open Price is the whole and decimal portion
	 * of the Open Price information with the Premium Price Denominator Code
	 * determining the location of the decimal point. Represents the first price
	 * paid for an option during the trading day.
	 */
	OPEN_PRICE, // data

	/**
	 * 4 Bytes, signed integer. The High Price is the whole and decimal portion
	 * of the High Price information with the Premium Price Denominator Code
	 * determining the location of the decimal point. Represents the highest
	 * price paid for an option during the trading day.
	 */
	HIGH_PRICE, // data

	/**
	 * 4 Bytes, signed integer. The Low Price is the whole and decimal portion
	 * of the Low Price information with the Premium Price Denominator Code
	 * determining the location of the decimal point. Represents the lowest
	 * price paid for an option during the trading day.
	 */
	LOW_PRICE, // data

	/**
	 * 4 Bytes, signed integer. The Last Price is the whole and decimal portion
	 * of the Last Price information with the Premium Price Denominator Code
	 * determining the location of the decimal point. Represents the last price
	 * paid for an option during the trading day.
	 */
	LAST_PRICE, // data; a.k.a close

	/** XXX */
	NET_CHANGE_INDICATOR, // data

	/**
	 * 4 Bytes, signed integer. The Net Change is the whole and decimal portion
	 * of the Net Change information with the Premium Price Denominator Code
	 * determining the location of the decimal point. Represents the change in
	 * the price of an option from the closing price of one day to the closing
	 * price on the next day on which the option is traded. This value can be
	 * positive, negative or zero.
	 */
	NET_CHANGE, // data

	//

	/**
	 * 1 Byte, alphabetic. The Underlying Price Denominator Code field indicates
	 * the position of the floating decimal point within the Underlying Price
	 * field.
	 */
	UNDERLYING_PRICE_DENOM, // data; price exponent

	/**
	 * 8 Bytes, signed integer. The Underlying Stock Price is the whole and
	 * decimal portion of the Underlying Stock Price information with the
	 * Underlying Stock Price Denominator Code determining the location of the
	 * decimal point. Represents the price of the underlying security.
	 */
	UNDERLYING_STOCK_PRICE, // data; price mantissa

	//

	NUMBER_OF_INDICES_IN_GROUP, // discard

	INDEX_SYMBOL, // inst

	/**
	 * 4 Bytes, signed integer. The Index Value is the whole and decimal portion
	 * of the Index Value information with the Premium Price Denominator Code
	 * determining the location of the decimal point. Contains the index value
	 * using last sale values of index components.
	 */
	INDEX_VALUE, // data; trade; price exponent;

	/**
	 * 4 Bytes, signed integer. The Bid Index Value is the whole and decimal
	 * portion of the Bid Index Value information with the Premium Price
	 * Denominator Code determining the location of the decimal point. The Bid
	 * Index Value represents the value of the index’s calculation formula using
	 * the current bid values of the component securities.
	 */
	BID_INDEX_VALUE, // data; bid; price exponent

	/**
	 * 4 Bytes, signed integer. The Offer Index Value is the whole and decimal
	 * portion of the Offer Index Value information with the Premium Price
	 * Denominator Code determining the location of the decimal point. The Offer
	 * Index Value represents the value of the index’s calculation formula using
	 * the current Offer(ed) values of the component securities.
	 */
	OFFER_INDEX_VALUE, // data; ask; price exponent

	//

	NUMBER_OF_FOREIGN_CURRENCY_SPOT_VALUES_IN_GROUP, //

	FCO_SYMBOL, // inst

	DECIMAL_PLACEMENT_INDICATOR, // inst

	FOREIGN_CURRENCY_SPOT_VALUE, //

	//

	TEXT, //

	DEF_MSG, //

	;

}
