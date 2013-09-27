package com.barchart.proto.buf.gateway;

import java.io.IOException;

import org.openfeed.messaging.MessageCodec;

public final class GatewayCodecs {

	private GatewayCodecs() {
	}

	public static MessageCodec<OrderRequest> ORDER_REQUEST_CODEC = new MessageCodec<OrderRequest>(GatewayMessageType.ORDER_REQUEST_VALUE) {

		@Override
		public OrderRequest decode(byte[] bytes) throws IOException {
			return OrderRequest.parseFrom(bytes);
		}

		@Override
		public byte[] encode(OrderRequest message) throws IOException {
			return message.toByteArray();
		}

	};

	public static MessageCodec<OrderResponse> ORDER_RESPONSE_CODEC = new MessageCodec<OrderResponse>(GatewayMessageType.ORDER_RESPONSE_VALUE) {

		@Override
		public OrderResponse decode(byte[] bytes) throws IOException {
			return OrderResponse.parseFrom(bytes);
		}

		@Override
		public byte[] encode(OrderResponse message) throws IOException {
			return message.toByteArray();
		}

	};

	public static MessageCodec<PortfolioRequest> PORTFOLIO_REQUEST_CODEC = new MessageCodec<PortfolioRequest>(GatewayMessageType.PORTFOLIO_REQUEST_VALUE) {

		@Override
		public PortfolioRequest decode(byte[] bytes) throws IOException {
			return PortfolioRequest.parseFrom(bytes);
		}

		@Override
		public byte[] encode(PortfolioRequest message) throws IOException {
			return message.toByteArray();
		}

	};

	public static MessageCodec<PortfolioResponse> PORTFOLIO_RESPONSE_CODEC = new MessageCodec<PortfolioResponse>(GatewayMessageType.PORTFOLIO_RESPONSE_VALUE) {
		@Override
		public PortfolioResponse decode(byte[] bytes) throws IOException {
			return PortfolioResponse.parseFrom(bytes);
		}

		@Override
		public byte[] encode(PortfolioResponse message) throws IOException {
			return message.toByteArray();
		}
	};

}
