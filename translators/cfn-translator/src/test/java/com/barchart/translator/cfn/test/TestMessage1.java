package com.barchart.translator.cfn.test;

import java.io.IOException;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.buf.data.MarketPacket.Builder;

public class TestMessage1 extends CFNTranslatorTest {

	@Override
	protected void given() throws IOException {
		
	}
	
	@Override
	protected MarketPacket expect() {
		Builder builder = MarketPacket.newBuilder();
		builder.setSequence(152432L);
		
		return builder.build(); 
	}

}
