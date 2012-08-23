package com.barchart.translator.cfn.data.impl;

import com.barchart.translator.cfn.data.CFNFuturesQuoteWithSize;
import com.barchart.translator.cfn.data.enums.CFNExpirationMonth;
import com.barchart.translator.cfn.data.enums.CFNExpirationYear;
import com.barchart.translator.cfn.data.enums.PriceDenominatorCode;
import com.barchart.translator.cfn.data.enums.SessionIndicator;
import com.barchart.translator.common.fields.EnumField;
import com.barchart.translator.common.fields.IntegerField;
import com.barchart.translator.common.fields.OffsetCalculator;
import com.barchart.translator.common.fields.StringField;

public class CFNFuturesQuoteWithSizeImpl implements CFNFuturesQuoteWithSize {

	private static final StringField FUTURES_SYMBOL;
	
	private static final EnumField<CFNExpirationMonth> EXPIRATION_MONTH;
	
	private static final EnumField<CFNExpirationYear> EXPIRATION_YEAR;
	
	private static final EnumField<PriceDenominatorCode> PRICE_DENOMINATOR_CODE;
	
	private static final IntegerField BID_QUOTE;
	private static final IntegerField BID_SIZE;
	private static final IntegerField ASK_QUOTE;
	private static final IntegerField ASK_SIZE;
	
	
	static {
		OffsetCalculator c = new OffsetCalculator(new CFNCodec());
		FUTURES_SYMBOL = c.newStringField(6);
		c.newStringField(1); // reserved
		EXPIRATION_MONTH = EnumField.from(CFNExpirationMonth.class, c.newByteField());
		EXPIRATION_YEAR = EnumField.from(CFNExpirationYear.class, c.newByteField());
		c.newStringField(9);  // reserved
		PRICE_DENOMINATOR_CODE = EnumField.from(PriceDenominatorCode.class, c.newByteField());
		BID_QUOTE = c.intField(8);
		BID_SIZE = c.intField(5);
		ASK_QUOTE = c.intField(8);
		ASK_SIZE = c.intField(5);
	}
	
	
	
	
	private byte[] array;
	
	private int baseOffset;

	public CFNFuturesQuoteWithSizeImpl(int offset, byte[] array) {
		this.baseOffset = offset;
		this.array = array;
	}

	@Override
	public String getFuturesSymbol() {
		return FUTURES_SYMBOL.get(baseOffset, array);
	}

	@Override
	public CFNExpirationMonth getExpirationMonth() {
//		return EXPIRATION_MONTH.get(baseOffset, array);
		return null;
	}

	@Override
	public CFNExpirationYear getExpirationYear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PriceDenominatorCode getPriceDenominatorCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBidQuote() {
		return BID_QUOTE.get(baseOffset, array);
	}

	@Override
	public int getBidSize() {
		return BID_SIZE.get(baseOffset, array);
	}

	@Override
	public int getAskQuote() {
		return ASK_QUOTE.get(baseOffset, array);
	}

	@Override
	public int getAskSize() {
		return ASK_SIZE.get(baseOffset, array);
	}

	@Override
	public SessionIndicator getSessionIndicator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public String getFuturesSymbol() {
//		String paddedString = string(offset(0), 6);
//		return paddedString.trim();
//	}
//
//	@Override
//	public CFNExpirationMonth getExpirationMonth() {
//		byte code = signedByte(offset(8));
//		return CFNExpirationMonth.fromCode(code);
//	}
//
//	@Override
//	public CFNExpirationYear getExpirationYear() {
//		byte code = signedByte(offset(8));
//		return CFNExpirationYear.fromCode(code);
//	}
//
//
//	@Override
//	public PriceDenominatorCode getPriceDenominatorCode() {
//		byte code = signedByte(18);
//		return PriceDenominatorCode.fromCode(code);
//	}
//
//	
//	@Override
//	public int getBidQuote() {
//		return asciiInteger(19, 8);
//	}
//
//	@Override
//	public int getBidSize() {
//		return asciiInteger(27, 5);
//	}
//
//	@Override
//	public int getAskQuote() {
//		return asciiInteger(32, 8);
//	}
//
//	@Override
//	public int getAskSize() {
//		return asciiInteger(40, 5);
//	}
//
//	@Override
//	public SessionIndicator getSessionIndicator() {
//		byte code = signedByte(45);
//		return SessionIndicator.fromCode(code);
//	}
//	
//	private int asciiInteger(int offset, int charLength) {
//		int value = 0;
//		for (int k = 0; k < charLength; k++) {
//			final int digit = array[offset + k] - (byte) '0';
//			value = value * 10 + digit;
//		}
//		return value;
//	}
//	
//	private String string(int offset, int length) {
//		return new String(array, offset, length);
//	}
//
//	private byte signedByte(int offset) {
//		return array[offset];
//	}
//
//
//	private int offset(int offset) {
//		return baseOffset + offset;
//	}


}
