package com.barchart.translator.common.fields;

import org.junit.Test;

public class OffsetCalculatorTest {

	@Test
	public void testOffsetCalculator() {
		OffsetCalculator calculator = new OffsetCalculator(null);
		
		FixedLengthField futuresSymbol = calculator.newField(6);
		FixedLengthField reserved1 = calculator.newField(1);
		FixedLengthField month = calculator.newField(1);
		FixedLengthField year = calculator.newField(1);
		FixedLengthField reserved2 = calculator.newField(9);
		FixedLengthField priceDenominator = calculator.newField(1);
	}
	
	private static class Fields extends OffsetCalculator {
		public Fields(Codec codec) {
			super(codec);
			// TODO Auto-generated constructor stub
		}
		public final StringField futuresSymbol = super.newStringField(6);
		public final StringField reserved1 = super.newStringField(1);
		public final IntegerField bidQuote = super.intField(8); 
	}
	
	private static final class Message {
		
		private static final Fields fields = new Fields(null);
		
		public Message() {
		}
		
	}
	
}
