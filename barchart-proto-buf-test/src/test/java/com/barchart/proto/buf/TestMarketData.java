package com.barchart.proto.buf;

import org.junit.Test;

import util.EnumUtil;

public class TestMarketData {

	@Test
	public void testType() throws Exception {

		EnumUtil.testUnique(MarketData.Type.class);

	}

}
