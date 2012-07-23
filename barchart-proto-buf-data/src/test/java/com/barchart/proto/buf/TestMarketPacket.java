package com.barchart.proto.buf;

import org.junit.Test;

import util.EnumUtil;

import com.barchart.proto.buf.data.MarketPacket;

public class TestMarketPacket {

	@Test
	public void testType() throws Exception {

		EnumUtil.testUnique(MarketPacket.Type.class);

	}

}
