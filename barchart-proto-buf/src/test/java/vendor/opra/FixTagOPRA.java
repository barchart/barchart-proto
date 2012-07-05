package vendor.opra;

public enum FixTagOPRA {

	MESSAGE_CATEGORY, // base

	MESSAGE_TYPE, // base

	PARTICIPANT_ID, // inst

	RETRANSMISSION_REQUESTER, // ?

	MESSAGE_SEQUENCE_NUMBER, // base

	TIME, // base

	SECURITY_SYMBOL, // inst

	EXPIRATION_DATE, // inst

	YEAR, // inst

	STRIKE_PRICE_CODE, // inst

	STRIKE_PRICE_DENOMINATOR_CODE, // inst

	EXPLICIT_STRIKE_PRICE, // inst

	PREMIUM_PRICE_DENOMINATOR_CODE, // inst

	BID_PRICE, // data

	BID_SIZE, // data

	OFFER_PRICE, // data

	OFFER_SIZE, // data

	SESSION_INDICATOR, // data

	BBO_INDICATOR, //

	BEST_BID_PARTICIPANT_ID, //

	BEST_BID_PRICE_DENOMINATOR_CODE, //

	BEST_BID_PRICE, //

	BEST_BID_SIZE, //

	BEST_OFFER_PARTICIPANT_ID, //

	BEST_OFFER_PRICE_DENOMINATOR_CODE, //

	BEST_OFFER_PRICE, //

	BEST_OFFER_SIZE, //

	RESERVED_FIELD_1, //

	RESERVED_FIELD_2, //

	RESERVED_FIELD_3, //

	OPEN_INT_VOLUME, // data

	VOLUME, // data

	PREMIUM_PRICE, //

	OPEN_PRICE, // data

	HIGH_PRICE, // data

	LOW_PRICE, // data

	LAST_PRICE, // data

	NET_CHANGE_INDICATOR, // data

	NET_CHANGE, // data

	//

	UNDERLYING_PRICE_DENOM, //

	UNDERLYING_STOCK_PRICE, //

	//

	NUMBER_OF_INDICES_IN_GROUP, //

	INDEX_SYMBOL, //

	INDEX_VALUE, //

	BID_INDEX_VALUE, //

	OFFER_INDEX_VALUE, //

	//

	NUMBER_OF_FOREIGN_CURRENCY_SPOT_VALUES_IN_GROUP, //

	FCO_SYMBOL, //

	DECIMAL_PLACEMENT_INDICATOR, //

	FOREIGN_CURRENCY_SPOT_VALUE, //

	//

	TEXT, //

	DEF_MSG, //

	;

}
