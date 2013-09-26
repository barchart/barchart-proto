package com.barchart.proto.buf.gateway;

import java.io.IOException;

import com.barhcar.proto.buf.MessageDispatcher;
import com.google.protobuf.CodedInputStream;

public abstract class GatewayMessageDispatcher extends MessageDispatcher<GatewayMessage> {

	public GatewayMessageDispatcher() {
		super(GatewayMessageType.GATEWAY_MESSAGE_VALUE);
	}

	@Override
	public void readNextMessage(CodedInputStream is) throws IOException {
		receive(GatewayMessage.parseFrom(is));
	}

	@Override
	public abstract void receive(GatewayMessage message);

}
