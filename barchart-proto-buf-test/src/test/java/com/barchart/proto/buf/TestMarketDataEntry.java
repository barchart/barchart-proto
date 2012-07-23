package com.barchart.proto.buf;

import org.junit.Test;

import util.EnumUtil;

public class TestMarketDataEntry {

	@Test
	public void testType() throws Exception {

		EnumUtil.testUnique(MarketDataEntry.Type.class);

	}

	@Test
	public void testAction() throws Exception {

		EnumUtil.testUnique(MarketDataEntry.Action.class);

	}

	@Test
	public void testDescriptor() throws Exception {

		EnumUtil.testUnique(MarketDataEntry.Descriptor.class);

	}

}
