//package com.barchart.feed.cme.buf;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import com.barchart.feed.cme.buf.messaging.IncrementalRefreshGroup;
//import com.barchart.feed.cme.buf.messaging.IncrementalRefreshMessage;
//import com.barchart.feed.cme.buf.messaging.MockHeader;
//import com.barchart.feed.cme.buf.messaging.MockIncrementalRefreshBody;
//import com.barchart.feed.cme.buf.messaging.MockIncrementalRefreshGroup;
//import com.barchart.proto.buf.Packet;
//import com.cme.fest.framework.util.ScaledDecimal;
//import com.google.common.collect.ImmutableList;
//
//public class CMETranslatorTest {
//
//	@Test
//	public void testCapturedMessage1() {
//		MockHeader header = new MockHeader();
//		header.setMsgSeqNum(17971908L);
//		header.setMsgType('X');
//		header.setSenderCompID("CME");
//		header.setSendingTime(20120809173142305L);
//		header.setApplVerID("9");
//		
//		MockIncrementalRefreshBody body = new MockIncrementalRefreshBody();
//		body.setNoMDEntries(1);
//		body.setTradeDate(20120809L);
//
//		MockIncrementalRefreshGroup group = new MockIncrementalRefreshGroup();
//		group.setSecurityIDSource('8');
//		group.setSecurityID("33234");
//		group.setRptSeq(4909014);
//		group.setMdEntryType('0');
//		group.setMdEntryPx(new ScaledDecimal(15618, 0));
//		group.setMdEntrySize(12);
//		group.setMdEntryTime(173142000L);
//		group.setMdUpdateAction('1');
//		group.setTradingSessionID("2");
//		group.setNumberOfOrders(10);
//		group.setMdPriceLevel(1);
//		
//		
//		List<MockIncrementalRefreshGroup> of = ImmutableList.of(group);
//		IncrementalRefreshMessage message = new IncrementalRefreshMessage(header, body, of);
//		CMETranslator translator = new CMETranslator(message);
//		Packet packet = translator.translate();
//	}
//	
//	private void checkHeader(MockHeader header, Packet packet) {
////		assertEquals(1, packet.getMessageCount());
//	}
//	
//	
//	
//	
//	
//	
//}
