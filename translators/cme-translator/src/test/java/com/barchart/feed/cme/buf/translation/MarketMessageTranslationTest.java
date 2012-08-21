package com.barchart.feed.cme.buf.translation;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.barchart.feed.fix.enums.FixMessageType;
import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.translator.cme.translation.MarketMessageTranslation;

public class MarketMessageTranslationTest {

	
	private MarketMessageTranslation translation;

	@Before
	public void setup() {
		this.translation = new MarketMessageTranslation();
	}
	
	@Test
	public void translateMessageTypeUpdate() {
		expectMessageType(FixMessageType.Update, MarketMessage.Type.UPDATE);
	}

	@Test
	public void translateMessageTypeSnapshot() {
		expectMessageType(FixMessageType.Snapshot, MarketMessage.Type.SNAPSHOT);
	}
	
	@Test
	public void translateLastMsgSeqNumProcessed() {
		translation.translateLastMsgSeqNumProcessed(14521902L);
//		assertEquals(14521902L, entry().getLastSequenceProcessed());
		Assert.fail();
	}
	
	@Test
	public void translateTradeDate() {
		translation.translateTradeDate(20120809);
		assertEquals(20120809, entry().getTradeDate());
	}
	
	private void expectMessageType(FixMessageType fixMessageType, MarketMessage.Type expected) {
		translation.translateMessageType(fixMessageType);
		MarketMessage entry = entry();
		assertTrue(entry.hasType());
		assertEquals(expected, entry.getType());
	}

	private MarketMessage entry() {
		return translation.build();
	}
	
}


