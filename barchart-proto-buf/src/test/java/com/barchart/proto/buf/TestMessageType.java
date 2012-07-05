package com.barchart.proto.buf;

import org.junit.Test;

import util.EnumUtil;

public class TestMessageType {

	@Test
	public void testType() throws Exception {

		EnumUtil.testUnique(MessageType.class);

	}

}
