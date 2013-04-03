/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf;

import org.junit.Test;

import util.EnumUtil;

public class TestMarketData {

	@Test
	public void testType() throws Exception {

		EnumUtil.testUnique(MarketData.Type.class);

	}

}
