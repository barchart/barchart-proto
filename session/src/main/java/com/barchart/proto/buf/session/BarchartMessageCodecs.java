package com.barchart.proto.buf.session;

import java.io.IOException;

import org.openfeed.messaging.MessageCodec;

import com.barchart.proto.streamingfeed.BarchartMessageType;
import com.barchart.proto.streamingfeed.MarketIdAvailabilityMessage;
import com.barchart.proto.streamingfeed.SubscribeRequest;
import com.barchart.proto.streamingfeed.SubscribeResponse;
import com.barchart.proto.streamingfeed.SubscribeSymbolRequest;
import com.barchart.proto.streamingfeed.SubscribeSymbolResponse;
import com.barchart.proto.streamingfeed.SupportedExchangesRequest;
import com.barchart.proto.streamingfeed.SupportedExchangesResponse;
import com.barchart.proto.streamingfeed.SymbolLookupRequest;
import com.barchart.proto.streamingfeed.SymbolLookupResponse;
import com.barchart.proto.streamingfeed.UnsubscribeRequest;
import com.barchart.proto.streamingfeed.UnsubscribeResponse;

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

	public static final MessageCodec<SubscribeRequest> SUBSCRIBE_REQUEST_CODEC =
			new MessageCodec<SubscribeRequest>() {

		@Override
				public SubscribeRequest decode(byte[] bytes) throws IOException {
					return SubscribeRequest.parseFrom(bytes);
		}

		@Override
				public byte[] encode(SubscribeRequest message) throws IOException {
			return message.toByteArray();
		}

		@Override
				public Class<SubscribeRequest> getMessageClass() {
					return SubscribeRequest.class;
		}

		@Override
		public int getTypeCode() {
					return BarchartMessageType.SUBSCRIBE_REQUEST_VALUE;
		}

	};

	public static final MessageCodec<SubscribeResponse> SUBSCRIBE_RESPONSE_CODEC =
			new MessageCodec<SubscribeResponse>() {

		@Override
				public SubscribeResponse decode(byte[] bytes) throws IOException {
					return SubscribeResponse.parseFrom(bytes);
		}

		@Override
				public byte[] encode(SubscribeResponse message) throws IOException {
			return message.toByteArray();
		}

		@Override
				public Class<SubscribeResponse> getMessageClass() {
					return SubscribeResponse.class;
		}

		@Override
		public int getTypeCode() {
					return BarchartMessageType.SUBSCRIBE_RESPONSE_VALUE;
		}

	};


	public static final MessageCodec<UnsubscribeRequest> UNSUBSCRIBE_REQUEST_CODEC =
			new MessageCodec<UnsubscribeRequest>() {

		@Override
				public UnsubscribeRequest decode(byte[] bytes) throws IOException {
					return UnsubscribeRequest.parseFrom(bytes);
		}

		@Override
				public byte[] encode(UnsubscribeRequest message) throws IOException {
			return message.toByteArray();
		}

		@Override
				public Class<UnsubscribeRequest> getMessageClass() {
					return UnsubscribeRequest.class;
		}

		@Override
		public int getTypeCode() {
					return BarchartMessageType.UNSUBSCRIBE_REQUEST_VALUE;
		}

	};

	public static final MessageCodec<UnsubscribeResponse> UNSUBSCRIBE_RESPONSE_CODEC =
			new MessageCodec<UnsubscribeResponse>() {

		@Override
				public UnsubscribeResponse decode(byte[] bytes) throws IOException {
					return UnsubscribeResponse.parseFrom(bytes);
		}

		@Override
				public byte[] encode(UnsubscribeResponse message) throws IOException {
			return message.toByteArray();
		}

		@Override
				public Class<UnsubscribeResponse> getMessageClass() {
					return UnsubscribeResponse.class;
		}

		@Override
		public int getTypeCode() {
					return BarchartMessageType.UNSUBSCRIBE_RESPONSE_VALUE;
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

	public static final MessageCodec<SupportedExchangesRequest> SUPPORTED_EXCHANGES_REQUEST_CODEC =
			new MessageCodec<SupportedExchangesRequest>() {

				@Override
				public SupportedExchangesRequest decode(byte[] bytes) throws IOException {
					return SupportedExchangesRequest.parseFrom(bytes);
				}

				@Override
				public byte[] encode(SupportedExchangesRequest message) throws IOException {
					return message.toByteArray();
				}

				@Override
				public Class<SupportedExchangesRequest> getMessageClass() {
					return SupportedExchangesRequest.class;
				}

				@Override
				public int getTypeCode() {
					return BarchartMessageType.SUPPORTED_EXCHANGEAS_REQUEST_VALUE;
				}

			};

	public static final MessageCodec<SupportedExchangesResponse> SUPPORTED_EXCHANGES_RESPONSE_CODEC =
			new MessageCodec<SupportedExchangesResponse>() {

				@Override
				public SupportedExchangesResponse decode(byte[] bytes) throws IOException {
					return SupportedExchangesResponse.parseFrom(bytes);
				}

				@Override
				public byte[] encode(SupportedExchangesResponse message) throws IOException {
					return message.toByteArray();
				}

				@Override
				public Class<SupportedExchangesResponse> getMessageClass() {
					return SupportedExchangesResponse.class;
				}

				@Override
				public int getTypeCode() {
					return BarchartMessageType.SUPPORTED_EXCHANGEAS_RESPONSE_VALUE;
				}

			};
}
