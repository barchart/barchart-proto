package com.barchart.proto.buf.inst;

import org.junit.Test;

import util.EnumUtil;

public class TestUniqEnums {

	@Test
	public void testBookType() throws Exception {

		EnumUtil.testUnique(BookType.class);

	}

	@Test
	public void testInstType() throws Exception {

		EnumUtil.testUnique(InstrumentType.class);

	}

	@Test
	public void testOptionType() throws Exception {

		EnumUtil.testUnique(OptionType.class);

	}

	@Test
	public void testOptionStyle() throws Exception {

		EnumUtil.testUnique(OptionStyle.class);

	}

	@Test
	public void testSpreadType() throws Exception {

		EnumUtil.testUnique(SpreadType.class);

	}

	@Test
	public void testPriceFraction() throws Exception {

		EnumUtil.testUnique(PriceFraction.class);

	}

}
