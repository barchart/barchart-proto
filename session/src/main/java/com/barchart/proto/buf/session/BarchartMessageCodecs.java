package com.barchart.proto.buf.session;

import java.io.IOException;

import org.openfeed.messaging.MessageCodec;

import com.barchart.proto.streamingfeed.BarchartMessageType;
import com.barchart.proto.streamingfeed.MarketIdAvailabilityMessage;
import com.barchart.proto.streamingfeed.SubscribeMarketIdRequest;
import com.barchart.proto.streamingfeed.SubscribeMarketIdResponse;
import com.barchart.proto.streamingfeed.SubscribeSymbolRequest;
import com.barchart.proto.streamingfeed.SubscribeSymbolResponse;

public class BarchartMessageCodecs {

	public static final MessageCodec<SubscribeSymbolRequest> SUBSCRIBE_SYMBOL_REQUEST_CODEC = new MessageCodec<SubscribeSymbolRequest>() {

		@Override
		public SubscribeSymbolRequest decode(byte[] bytes) throws IOException {
			return SubscribeSymbolRequest.parseFrom(bytes);
		}

		@Override
		public byte[] encode(SubscribeSymbolRequest message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<SubscribeSymbolRequest> getMessageClass() {
			return SubscribeSymbolRequest.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.SUBSCRIBE_SYMBOL_REQUEST_VALUE;
		}

	};

	public static final MessageCodec<SubscribeSymbolResponse> SUBSCRIBE_SYMBOL_RESPONSE_CODEC = new MessageCodec<SubscribeSymbolResponse>() {

		@Override
		public SubscribeSymbolResponse decode(byte[] bytes) throws IOException {
			return SubscribeSymbolResponse.parseFrom(bytes);
		}

		@Override
		public byte[] encode(SubscribeSymbolResponse message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<SubscribeSymbolResponse> getMessageClass() {
			return SubscribeSymbolResponse.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.SUBSCRIBE_SYMBOL_RESPONSE_VALUE;
		}

	};

	public static final MessageCodec<SubscribeMarketIdRequest> SUBSCRIBE_MARKET_ID_REQUEST_CODEC = new MessageCodec<SubscribeMarketIdRequest>() {

		@Override
		public SubscribeMarketIdRequest decode(byte[] bytes) throws IOException {
			return SubscribeMarketIdRequest.parseFrom(bytes);
		}

		@Override
		public byte[] encode(SubscribeMarketIdRequest message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<SubscribeMarketIdRequest> getMessageClass() {
			return SubscribeMarketIdRequest.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.SUBSCRIBE_MARKET_ID_REQUEST_VALUE;
		}

	};

	public static final MessageCodec<SubscribeMarketIdResponse> SUBSCRIBE_MARKET_ID_RESPONSE_CODEC = new MessageCodec<SubscribeMarketIdResponse>() {

		@Override
		public SubscribeMarketIdResponse decode(byte[] bytes) throws IOException {
			return SubscribeMarketIdResponse.parseFrom(bytes);
		}

		@Override
		public byte[] encode(SubscribeMarketIdResponse message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<SubscribeMarketIdResponse> getMessageClass() {
			return SubscribeMarketIdResponse.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.SUBSCRIBE_MARKET_ID_RESPONSE_VALUE;
		}

	};

	public static final MessageCodec<MarketIdAvailabilityMessage> MARKET_ID_AVAILABILITY_MESSAGE_CODEC = new MessageCodec<MarketIdAvailabilityMessage>() {

		@Override
		public MarketIdAvailabilityMessage decode(byte[] bytes) throws IOException {
			return MarketIdAvailabilityMessage.parseFrom(bytes);
		}

		@Override
		public byte[] encode(MarketIdAvailabilityMessage message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<MarketIdAvailabilityMessage> getMessageClass() {
			return MarketIdAvailabilityMessage.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.MARKET_ID_AVAILABILITY_MESSAGE_VALUE;
		}

	};

}
