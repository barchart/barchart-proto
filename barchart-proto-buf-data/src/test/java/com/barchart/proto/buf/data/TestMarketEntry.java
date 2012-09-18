package com.barchart.proto.buf.data;

import org.junit.Test;

import util.EnumUtil;

import com.barchart.proto.buf.data.MarketEntry;

public class TestMarketEntry {

	@Test
	public void testType() throws Exception {

		EnumUtil.testUnique(MarketEntry.Type.class);

	}

	@Test
	public void testAction() throws Exception {

		EnumUtil.testUnique(MarketEntry.Action.class);

	}

	@Test
	public void testDescriptor() throws Exception {

		EnumUtil.testUnique(MarketEntry.Descriptor.class);

	}

}
