package com.barchart.proto.buf.gateway;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.openfeed.messaging.MessageReceiver;
import org.openfeed.messaging.MessageSender;
import org.openfeed.messaging.MessageStreamReader;
import org.openfeed.messaging.MessageStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayMessageTest {

	private static final Logger logger = LoggerFactory.getLogger(GatewayMessageTest.class);

	@Test
	public void writeAndReadDifferentMessageTypes() throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MessageStreamWriter writer = new MessageStreamWriter(baos);

		OrderRequest request1 = OrderRequest.newBuilder().setAccount(Account.newBuilder().setName("Request 1")).build();
		OrderRequest request2 = OrderRequest.newBuilder().setAccount(Account.newBuilder().setName("Request 2")).build();
		OrderRequest request3 = OrderRequest.newBuilder().setAccount(Account.newBuilder().setName("Request 3")).build();
		OrderRequest request4 = OrderRequest.newBuilder().setAccount(Account.newBuilder().setName("Request 4")).build();

		OrderResponse response1 = OrderResponse.newBuilder().setAccount(Account.newBuilder().setName("Response 1")).build();
		OrderResponse response2 = OrderResponse.newBuilder().setAccount(Account.newBuilder().setName("Response 2")).build();
		OrderResponse response3 = OrderResponse.newBuilder().setAccount(Account.newBuilder().setName("Response 3")).build();
		OrderResponse response4 = OrderResponse.newBuilder().setAccount(Account.newBuilder().setName("Response 4")).build();

		writer.write(GatewayCodecs.ORDER_REQUEST_CODEC, request1);
		writer.write(GatewayCodecs.ORDER_RESPONSE_CODEC, response1);

		writer.write(GatewayCodecs.ORDER_REQUEST_CODEC, request2);
		writer.write(GatewayCodecs.ORDER_RESPONSE_CODEC, response2);

		writer.write(GatewayCodecs.ORDER_REQUEST_CODEC, request3);
		writer.write(GatewayCodecs.ORDER_RESPONSE_CODEC, response3);

		writer.write(GatewayCodecs.ORDER_REQUEST_CODEC, request4);
		writer.write(GatewayCodecs.ORDER_RESPONSE_CODEC, response4);

		byte[] byteArray = baos.toByteArray();

		MessageStreamReader reader = new MessageStreamReader(new ByteArrayInputStream(byteArray));
		reader.register(GatewayCodecs.ORDER_REQUEST_CODEC, new MessageReceiver<OrderRequest>() {
			@Override
			public void receive(OrderRequest orderRequest) throws Exception {
				logger.info("Received: " + orderRequest);
			}
		});

		reader.register(GatewayCodecs.ORDER_RESPONSE_CODEC, new MessageReceiver<OrderResponse>() {
			@Override
			public void receive(OrderResponse orderResponse) throws Exception {
				logger.info("Received: " + orderResponse);
			}
		});

		while (reader.processNextMessage())
			;

	}

	@Test
	public void testUsingNormalProtobufToReadHomogeneousMessageStream() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MessageStreamWriter writer = new MessageStreamWriter(baos);
		OrderRequest request1 = OrderRequest.newBuilder().setAccount(Account.newBuilder().setName("Request 1")).build();
		OrderRequest request2 = OrderRequest.newBuilder().setAccount(Account.newBuilder().setName("Request 2")).build();
		OrderRequest request3 = OrderRequest.newBuilder().setAccount(Account.newBuilder().setName("Request 3")).build();
		OrderRequest request4 = OrderRequest.newBuilder().setAccount(Account.newBuilder().setName("Request 4")).build();
		writer.write(GatewayCodecs.ORDER_REQUEST_CODEC, request1);
		writer.write(GatewayCodecs.ORDER_REQUEST_CODEC, request2);
		writer.write(GatewayCodecs.ORDER_REQUEST_CODEC, request3);
		writer.write(GatewayCodecs.ORDER_REQUEST_CODEC, request4);

		byte[] byteArray = baos.toByteArray();

		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		OrderRequest readRequest;
		while (readType(bais) != -1 && (readRequest = OrderRequest.parseDelimitedFrom(bais)) != null) {
			logger.info("Easily read: " + readRequest);
		}

	}

	private int readType(InputStream input) throws IOException {
		int firstByte = input.read();
		if (firstByte == -1) {
			return -1;
		} else {
			return readRawVarint32(input, firstByte);
		}
	}

	private int readRawVarint32(InputStream input, int firstByte) throws IOException {
		if ((firstByte & 0x80) == 0) {
			return firstByte;
		}

		int result = firstByte & 0x7f;
		int offset = 7;
		for (; offset < 32; offset += 7) {
			final int b = input.read();
			if (b == -1) {
				throw new RuntimeException("Truncated message");
			}
			result |= (b & 0x7f) << offset;
			if ((b & 0x80) == 0) {
				return result;
			}
		}
		// Keep reading up to 64 bits.
		for (; offset < 64; offset += 7) {
			final int b = input.read();
			if (b == -1) {
				throw new RuntimeException("Truncated message");
			}
			if ((b & 0x80) == 0) {
				return result;
			}
		}
		throw new RuntimeException("Malformed VarInt");
	}

}
