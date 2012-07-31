package com.barchart.proto.buf;

import org.junit.Test;

import util.EnumUtil;

import com.barchart.proto.buf.data.PacketType;

public class TestMarketPacket {

	@Test
	public void testType() throws Exception {

		EnumUtil.testUnique(PacketType.class);

	}

}
