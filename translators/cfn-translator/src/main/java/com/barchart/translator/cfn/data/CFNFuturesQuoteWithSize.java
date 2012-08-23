package com.barchart.translator.cfn.data;

import com.barchart.translator.cfn.data.enums.CFNExpirationMonth;
import com.barchart.translator.cfn.data.enums.CFNExpirationYear;
import com.barchart.translator.cfn.data.enums.PriceDenominatorCode;
import com.barchart.translator.cfn.data.enums.SessionIndicator;

/*
 * Category: 'r'
 */
public interface CFNFuturesQuoteWithSize {

	public String getFuturesSymbol();
	
	public CFNExpirationMonth getExpirationMonth();
	
	public CFNExpirationYear getExpirationYear();
	
	public PriceDenominatorCode getPriceDenominatorCode();
	
	public int getBidQuote();
	
	public int getBidSize();
	
	public int getAskQuote();
	
	public int getAskSize();
	
	public SessionIndicator getSessionIndicator();
}
