package com.barchart.proto.buf.gateway;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
	public void test() throws IOException {

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
		
		
		
		MessageSender<OrderRequest> requestSender  = writer.registerCodec(GatewayCodecs.ORDER_REQUEST_CODEC);
		
		MessageSender<OrderResponse> responseSender = writer.registerCodec(GatewayCodecs.ORDER_RESPONSE_CODEC);
		
		requestSender.send(request1);
		responseSender.send(response1);

		requestSender.send(request2);
		responseSender.send(response2);
		
		requestSender.send(request3);
		responseSender.send(response3);
		
		requestSender.send(request4);
		responseSender.send(response4);
		
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

}
