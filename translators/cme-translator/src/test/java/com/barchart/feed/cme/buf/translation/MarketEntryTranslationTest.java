package com.barchart.feed.cme.buf.translation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.barchart.feed.fix.enums.FixEntryType;
import com.barchart.feed.fix.enums.FixUpdateAction;
import com.barchart.proto.buf.data.MarketEntry;
import com.barchart.proto.buf.data.MarketEntry.Action;
import com.barchart.translator.cme.translation.MarketEntryTranslation;

public class MarketEntryTranslationTest {

	private MarketEntryTranslation translation;

	@Before
	public void setup() {
		this.translation = new MarketEntryTranslation();
	}
	
	@Test
	public void translateNullPrice() {
		translation.translatePrice(null);
		assertFalse(entry().hasPriceExponent());
		assertFalse(entry().hasPriceMantissa());
	}
	
	@Test
	public void translateDecimalPrice() {
		translation.translatePrice(newBigDecimal(12345L, -2));
		assertEquals(12345, entry().getPriceMantissa());
		assertEquals(-2, entry().getPriceExponent());
	}
	
	@Test
	public void translatePriceNoExponent() {
		translation.translatePrice(newBigDecimal(987L, 0));
		assertEquals(987, entry().getPriceMantissa());
		assertEquals(0, entry().getPriceExponent());
	}
	
	private BigDecimal newBigDecimal(long mantissa, int exponent) {
		return new BigDecimal(BigInteger.valueOf(mantissa), exponent);
	}
	
	
	@Test
	public void translateNullEntrySize() {
		translation.translateEntrySize(null);
		assertFalse(entry().hasSizeExponent());
		assertFalse(entry().hasSizeMantissa());
	}
	
	@Test
	public void translateEntrySize() {
		translation.translateEntrySize(10);
		assertEquals(10, entry().getSizeMantissa());
		assertEquals(0, entry().getSizeExponent());
	}
	
	@Test
	public void doesNotHaveActionIfNotSet() {
		assertFalse(entry().hasAction());
	}
	
	@Test
	public void translateActionNew() {
		testAction(FixUpdateAction.New, Action.ADD);
	}

	@Test
	public void translateActionChange() {
		testAction(FixUpdateAction.Change, Action.EDIT);
	}
	
	@Test
	public void translateActionDelete() {
		testAction(FixUpdateAction.Delete, Action.REMOVE);
	}
	
	@Test
	public void testNoEntryType() {
		assertFalse(entry().hasType());
	}
	
	@Test
	public void translateNullEntryType() {
		translation.translateEntryType(null);
		assertFalse(entry().hasType());
	}
	
	@Test
	public void translateEntryTypeBid() {
		expectEntryType(FixEntryType.Bid, MarketEntry.Type.BID);
	}

	@Test
	public void translateEntryTypeAsk() {
		expectEntryType(FixEntryType.Offer, MarketEntry.Type.ASK);
	}
	
	@Test
	public void translateEntryTypeSessionHighBid() {
		Assert.fail();
		MarketEntry entry = entry();
//		expectEntryType(FixEntryType.SessionHighBid, MarketEntry.Type.SESSION_HIGH_BID);
		
	}
	
	@Test
	public void translateEntryTypeSessionLowOffer() {
		Assert.fail();
//		expectEntryType(FixEntryType.SessionLowOffer, MarketEntry.Type.SESSION_LOW_ASK);
	}
	
	@Test
	public void translateEntryTypeSessionHighPrice() {
		expectEntryType(FixEntryType.SessionHighPrice, MarketEntry.Type.HIGH);
	}
	
	@Test
	public void translateEntryTypeSessionLowPrice() {
		expectEntryType(FixEntryType.SessionLowPrice, MarketEntry.Type.LOW);
	}
	
	@Test
	public void translateOpeningPrice() {
		expectEntryType(FixEntryType.OpeningPrice, MarketEntry.Type.OPEN);
	}
	
	@Test
	public void translateFixingPrice() {
		Assert.fail();
		//expectEntryType(FixEntryType.FixingPrice, MarketEntry.Type.FIXING_PRICE);
	}
	
	@Test
	public void translateNullPriceLevel() {
		translation.translatePriceLevel(null);
		assertFalse(entry().hasIndex());
	}
	
	@Test
	public void translatePriceLevel0() {
		expectPriceLevel(0);
	}
	
	@Test
	public void translatePriceLevel5() {
		expectPriceLevel(5);
	}
	
	
	
	
	private void expectEntryType(FixEntryType fixEntryType, MarketEntry.Type expected) {
		translation.translateEntryType(fixEntryType);
		assertEquals(expected, entry().getType());
	}
	
	private void testAction(FixUpdateAction fixAction, MarketEntry.Action expected) {
		translation.translateUpdateAction(fixAction);
		assertEquals(expected, entry().getAction() );
	}

	private void expectPriceLevel(Integer priceLevel) {
		translation.translatePriceLevel(priceLevel);
		assertEquals(priceLevel.intValue(), entry().getIndex());
	}
	
	private MarketEntry entry() {
		return translation.build();
	}
	
}
