/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
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
