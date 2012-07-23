package com.barchart.proto.xform.cfn;

import java.nio.ByteBuffer;
import java.util.List;

import com.barchart.proto.xform.cfn.CFN;
import com.barchart.proto.xform.cfn.CFN.Body;
import com.barchart.proto.xform.cfn.CFN.Body.Type;
import com.barchart.proto.xform.cfn.CFN.Head;
import com.barchart.proto.xform.cfn.CFN.Message;
import com.barchart.proto.xform.cfn.CFN.Node;
import com.barchart.proto.xform.cfn.CFN.Packet;

public class ExampleCFN {

	public static void decode(final ByteBuffer buffer,
			final CFN.Body.Visitor<String> visitor) throws Exception {

		final String context = "";

		final Packet packet = CFN.Packet.from(buffer, null);

		final List<Message> messageList = packet.messageList();

		for (final Message message : messageList) {

			final Head head = message.head();

			final Body body = message.body();

			final Type type = body.type();

			final Node option = body.value();

			type.apply(visitor, option, context);

		}

	}

}