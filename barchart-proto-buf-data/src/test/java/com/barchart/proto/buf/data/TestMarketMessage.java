package com.barchart.proto.buf.data;

import org.junit.Test;

import util.EnumUtil;

import com.barchart.proto.buf.data.MarketMessage;

public class TestMarketMessage {

	@Test
	public void testType() throws Exception {

		EnumUtil.testUnique(MarketMessage.Type.class);

	}

}
