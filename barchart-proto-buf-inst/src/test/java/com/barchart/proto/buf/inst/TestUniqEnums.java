/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
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
