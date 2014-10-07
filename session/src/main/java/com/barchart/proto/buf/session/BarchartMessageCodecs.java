package com.barchart.proto.buf.session;

import java.io.IOException;

import org.openfeed.messaging.MessageCodec;

import com.barchart.proto.streamingfeed.BarchartMessageType;
import com.barchart.proto.streamingfeed.LoginRequest;
import com.barchart.proto.streamingfeed.LoginResponse;
import com.barchart.proto.streamingfeed.MarketIdAvailabilityMessage;
import com.barchart.proto.streamingfeed.SubscribeMarketIdRequest;
import com.barchart.proto.streamingfeed.SubscribeMarketIdResponse;
import com.barchart.proto.streamingfeed.SubscribeSymbolRequest;
import com.barchart.proto.streamingfeed.SubscribeSymbolResponse;
import com.barchart.proto.streamingfeed.SymbolLookupRequest;
import com.barchart.proto.streamingfeed.SymbolLookupResponse;
import com.barchart.proto.streamingfeed.UnsubscribeMarketIdRequest;
import com.barchart.proto.streamingfeed.UnsubscribeMarketIdResponse;

public class BarchartMessageCodecs {

	public static final MessageCodec<LoginRequest> LOGIN_REQUEST_CODEC = new MessageCodec<LoginRequest>() {

		@Override
		public LoginRequest decode(byte[] bytes) throws IOException {
			return LoginRequest.parseFrom(bytes);
		}

		@Override
		public byte[] encode(LoginRequest message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<LoginRequest> getMessageClass() {
			return LoginRequest.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.LOGIN_REQUEST_VALUE;
		}

	};
	
	
	public static final MessageCodec<LoginResponse> LOGIN_RESPONSE_CODEC = new MessageCodec<LoginResponse>() {

		@Override
		public LoginResponse decode(byte[] bytes) throws IOException {
			return LoginResponse.parseFrom(bytes);
		}

		@Override
		public byte[] encode(LoginResponse message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<LoginResponse> getMessageClass() {
			return LoginResponse.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.LOGIN_RESPONSE_VALUE;
		}

	};
	
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

	
	public static final MessageCodec<UnsubscribeMarketIdRequest> UNSUBSCRIBE_MARKET_ID_REQUEST_CODEC = new MessageCodec<UnsubscribeMarketIdRequest>() {

		@Override
		public UnsubscribeMarketIdRequest decode(byte[] bytes) throws IOException {
			return UnsubscribeMarketIdRequest.parseFrom(bytes);
		}

		@Override
		public byte[] encode(UnsubscribeMarketIdRequest message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<UnsubscribeMarketIdRequest> getMessageClass() {
			return UnsubscribeMarketIdRequest.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.UNSUBSCRIBE_MARKET_ID_REQUEST_VALUE;
		}

	};

	public static final MessageCodec<UnsubscribeMarketIdResponse> UNSUBSCRIBE_MARKET_ID_RESPONSE_CODEC = new MessageCodec<UnsubscribeMarketIdResponse>() {

		@Override
		public UnsubscribeMarketIdResponse decode(byte[] bytes) throws IOException {
			return UnsubscribeMarketIdResponse.parseFrom(bytes);
		}

		@Override
		public byte[] encode(UnsubscribeMarketIdResponse message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<UnsubscribeMarketIdResponse> getMessageClass() {
			return UnsubscribeMarketIdResponse.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.UNSUBSCRIBE_MARKET_ID_RESPONSE_VALUE;
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

	public static final MessageCodec<SymbolLookupRequest> SYMBOL_LOOKUP_REQUEST_CODEC = new MessageCodec<SymbolLookupRequest>() {

		@Override
		public SymbolLookupRequest decode(byte[] bytes) throws IOException {
			return SymbolLookupRequest.parseFrom(bytes);
		}

		@Override
		public byte[] encode(SymbolLookupRequest message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<SymbolLookupRequest> getMessageClass() {
			return SymbolLookupRequest.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.SYMBOL_LOOKUP_REQUEST_VALUE;
		}

	};

	public static final MessageCodec<SymbolLookupResponse> SYMBOL_LOOKUP_RESPONSE_CODEC = new MessageCodec<SymbolLookupResponse>() {

		@Override
		public SymbolLookupResponse decode(byte[] bytes) throws IOException {
			return SymbolLookupResponse.parseFrom(bytes);
		}

		@Override
		public byte[] encode(SymbolLookupResponse message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<SymbolLookupResponse> getMessageClass() {
			return SymbolLookupResponse.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartMessageType.SYMBOL_LOOKUP_RESPONSE_VALUE;
		}

	};

}
