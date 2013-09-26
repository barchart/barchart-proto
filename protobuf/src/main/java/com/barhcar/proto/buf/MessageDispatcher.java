package com.barhcar.proto.buf;

import com.google.protobuf.CodedInputStream;

public abstract class MessageDispatcher<T> {

	private final int messageCode;

	public MessageDispatcher(int messageCode) {
		this.messageCode = messageCode;
	}

	public final int getMessageCode() {
		return messageCode;
	}

	public abstract void readNextMessage(CodedInputStream is) throws Exception;

	public abstract void receive(T message);

}
