package com.barchart.translator.ice.test;

import java.io.IOException;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.buf.data.MarketPacket.Builder;

public class TestMessage1 extends ICETranslatorTest {

	@Override
	protected void given() throws IOException {
		sessionNumber(1234);
		sequenceNumber(152432);
		numberOfMsgs(123);
		sendDateTime(12423423L);
		
	}
	
	@Override
	protected MarketPacket expect() {
		Builder builder = MarketPacket.newBuilder();
		builder.setSequence(152432L);
		
		return builder.build(); 
	}

}
