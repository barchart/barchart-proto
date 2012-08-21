package com.barchart.translator.ice.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.xform.ice.CodecICE;
import com.barchart.proto.xform.ice.ConverterICE;
import com.barchart.proto.xform.ice.ICE;
import com.barchart.translator.common.Translator;
import com.barchart.translator.ice.ICETranslator;

public abstract class ICETranslatorTest {

	static {
		ICE.bind(new CodecICE());
		ICE.bind(new ConverterICE());
	}
	
	private final ByteArrayOutputStream bytes;
	private final DataOutputStream out;
	private Translator translator;
	private MarketPacket expectedPacket;
	private MarketPacket actual;

	public ICETranslatorTest() {
		this.bytes = new ByteArrayOutputStream();
		this.out = new DataOutputStream(bytes);
		this.translator = new ICETranslator();
		this.expectedPacket = expect();
	}
	
	

	
	protected abstract void given() throws IOException;
	protected abstract MarketPacket expect();
	
	@Before
	public void setupTestCase() throws IOException {
		given();
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes.toByteArray());
		this.actual = translator.translate(byteBuffer);
		
	}
	
	@Test
	public void testSequenceNumber() {
		assertEquals(expectedPacket.getSequence(), actual.getSequence());
	}

	
	protected void sessionNumber(int value) throws IOException {
		out.writeShort(value);
	}
	
	protected void sequenceNumber(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void numberOfMsgs(int value) throws IOException {
		out.writeShort(value);
	}
	
	protected void sendDateTime(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void messageType(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void messageBodyLength(int value) throws IOException {
		out.writeShort(value);
	}
	
	protected void marketID(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void orderID(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void orderSequenceID(int value) throws IOException {
		out.writeShort(value);
	}
	
	protected void side(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void price(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void quantity(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void isImplied(char value) throws IOException {
		out.writeByte( (byte) value);
	}
	
	protected void isRFQ(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void orderEntryDateTime(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void orderCount(int value) throws IOException {
		out.writeShort(value);
	}
	
	protected void impliedQuantity(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void impliedOrderCount(int value) throws IOException {
		out.writeShort(value);
	}
	
	protected void priceLevelPosition(int value) throws IOException {
		out.writeByte(value);
	}
	
	protected void marketType(int value) throws IOException {
		out.writeShort(value);
	}
	
	protected void tradingStatus(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void volume(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void blockVolume(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void efsVolume(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void efpVolume(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void openInterest(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void openingPrice(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void settlementPrice(int value) throws IOException {
		out.writeLong(value);
	}
	
	protected void high(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void quantity(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void vwap(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void numOfBookEntries(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void lastTradePrice(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void lastTradeQuantity(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void lastTradeDateTime(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void settlePriceDateTime(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void lastMessageSequenceID(int value) throws IOException {
		out.writeInt(value);
	}
	
	protected void reservedField1(int value) throws IOException {
		out.writeShort(value);
	}
	
	protected void tradeID(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void isSystemPricedLeg(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void blockTradeType(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void transactDateTime(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void systemPricedLegType(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void isImpliedSpreadAtMarketOpen(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void isAdjustedTrade(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	protected void dateTime(long value) throws IOException {
		out.writeLong(value);
	}
	
	protected void status(char value) throws IOException {
		out.writeByte((byte) value);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
