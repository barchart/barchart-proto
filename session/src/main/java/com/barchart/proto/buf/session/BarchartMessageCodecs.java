package com.barchart.proto.buf.session;

import java.io.IOException;

import org.openfeed.messaging.MessageCodec;

import com.barchart.proto.streamingfeed.BarchartMessageType;
import com.barchart.proto.streamingfeed.SubscriptionRequest;
import com.barchart.proto.streamingfeed.SubscriptionResponse;

public class BarchartMessageCodecs {

	public static final MessageCodec<SubscriptionRequest> SUBSCRIPTION_REQUEST_CODEC = new MessageCodec<SubscriptionRequest>() {

		@Override
		public SubscriptionRequest decode(byte[] bytes) throws IOException {
			return SubscriptionRequest.parseFrom(bytes);
		}

		@Override
		public byte[] encode(SubscriptionRequest message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<SubscriptionRequest> getMessageClass() {
			return SubscriptionRequest.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.SUBSCRIPTION_REQUEST_VALUE;
		}

	};

	public static final MessageCodec<SubscriptionResponse> SUBSCRIPTION_RESPONSE_CODEC = new MessageCodec<SubscriptionResponse>() {

		@Override
		public SubscriptionResponse decode(byte[] bytes) throws IOException {
			return SubscriptionResponse.parseFrom(bytes);
		}

		@Override
		public byte[] encode(SubscriptionResponse message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<SubscriptionResponse> getMessageClass() {
			return SubscriptionResponse.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.SUBSCRIPTION_RESPONSE_VALUE;
		}

	};

}
