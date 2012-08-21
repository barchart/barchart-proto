package com.barchart.feed.cme.buf.translation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.translator.cme.translation.PacketTranslation;

public class PacketTranslationTest {

	private PacketTranslation translation;

	@Before
	public void setup() {
		this.translation = new PacketTranslation();
	}
	
	
	@Test
	public void testChannel() {
		
	}
	
	@Test
	public void testSequence() {
		translation.translateSequence(123456789L);
		assertEquals(123456789L, entry().getSequence());
	}
	
	@Test
	public void translateTimestamp() {
		translation.translateSendingTime(20120809173142305L);
		assertEquals(20120809173142305L, entry().getTimeStamp());
	}
	
	
	public MarketPacket.Builder entry() {
		return translation.getBuilder();
	}
	
}
