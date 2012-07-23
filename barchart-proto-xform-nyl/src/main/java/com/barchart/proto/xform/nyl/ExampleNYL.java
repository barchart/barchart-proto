package com.barchart.proto.xform.nyl;

import java.nio.ByteBuffer;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.xform.nyl.NYL.Packet;

public class ExampleNYL {

	public static void decode(final ByteBuffer buffer) throws Exception {

		/** decode form native protocol */
		final Packet source = NYL.Packet.from(buffer, null);

		/** make barchart representation */
		final MarketPacket.Builder target = MarketPacket.newBuilder();

		/** convert into barchart protocol */
		source.into(target);

	}

}
