/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf.assm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openfeed.proto.inst.InstrumentDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.ByteString;
import com.google.protobuf.Message;

/**
 * encode/decode proto.buf messages
 */
public final class PacketCodec {

	private static final Logger log = LoggerFactory
			.getLogger(PacketCodec.class);

	private static final Map<Class<? extends Message>, PacketType> messageKlazToType = //
	new ConcurrentHashMap<Class<? extends Message>, PacketType>();

	private static final EnumMap<PacketType, Class<? extends Message>> messageTypeToKlaz = //
	new EnumMap<PacketType, Class<? extends Message>>(PacketType.class);

	static {

		// messageTypeToKlaz.put(PacketType.MarketData, MarketMessage.class);
		// messageTypeToKlaz.put(PacketType.Instrument,
		// InstrumentDefinition.class);

		for (final Map.Entry<PacketType, Class<? extends Message>> entry : messageTypeToKlaz
				.entrySet()) {

			messageKlazToType.put(entry.getValue(), entry.getKey());

		}

	}

	/**
	 * 
	 * decode sub type messages with help of message visitor and message
	 * consumer target
	 * 
	 */
	public static <TARGET> void decode(final Packet packet,
			final PacketVisitor<TARGET> visitor, final TARGET target)
			throws Exception {

		if (!packet.hasType()) {
			log.warn("missing base type",
					new IllegalArgumentException(packet.toString()));
			return;
		}

		/** packet type determines interpretation of the packet body */
		final PacketType type = packet.getType();

		/** packet body is just a byte array at this point */
		final List<ByteString> bodyList = packet.getBodyList();

		switch (type) {

		case MarketData: {
			// MarketMessageCodec parseFrom =
			// MarketMessageCodec.parseFrom(body);
			// MarketMessageCodec build =
			// MarketMessageCodec.newBuilder().mergeFrom(body).build();
			// List<MarketMessage> messasgeList = build.getMessageList();
			// MarketMessageCodec codec = MarketMessageCodec.parseFrom(body);
			// int messageCount = codec.getMessageCount();
			// UnknownFieldSet unknownFields = codec.getUnknownFields();
			// List<MarketMessage> messasgeList = codec.getMessageList();

			// visitor.apply(messasgeList, target);

			break;
		}

		case Instrument: {

			for (final ByteString body : bodyList) {
				final InstrumentDefinition definition = InstrumentDefinition
						.parseFrom(body);
			}

			// final InstrumentMessageCodec parseFrom = InstrumentMessageCodec
			// .parseFrom(ByteString.copyFrom(bodyList));

			// System.out.println(parseFrom);

			// final InstrumentDefinition message =
			// InstrumentCodec.decode(body);

			// visitor.apply(message, target);

			break;
		}

		//

		default:
			log.warn("unsupported message type",
					new UnsupportedOperationException(type.name()));
			break;
		}

	}

	/** decode form raw array */
	public static Packet decode(final byte[] array) throws Exception {

		final Packet.Builder builder = Packet.newBuilder();

		builder.mergeFrom(array);

		return builder.build();

	}

	public static <TARGET> void decode(final byte[] array,
			final PacketVisitor<TARGET> visitor, final TARGET target)
			throws Exception {

		final Packet packet = decode(array);

		decode(packet, visitor, target);

	}

	/** decode from length-prefixed raw array */
	public static Packet decodeDelimited(final byte[] array) throws Exception {

		final ByteArrayInputStream input = new ByteArrayInputStream(array);

		final Packet.Builder builder = Packet.newBuilder();

		builder.mergeDelimitedFrom(input);

		return builder.build();

	}

	public static <TARGET> void decodeDelimited(final byte[] array,
			final PacketVisitor<TARGET> visitor, final TARGET target)
			throws Exception {

		final Packet packet = decodeDelimited(array);

		decode(packet, visitor, target);

	}

	public static <TARGET> void decodeStream(final InputStream stream,
			final PacketVisitor<TARGET> visitor, final TARGET target)
			throws Exception {
		Packet packet;
		while ((packet = Packet.parseDelimitedFrom(stream)) != null) {
			decode(packet, visitor, target);
		}
	}

	public static byte[] encode(final Packet base) throws Exception {
		return base.toByteArray();
	}

	/** embed sub type message into a base */
	public static <MESSAGE extends Message> Packet.Builder encode(
			final MESSAGE message) {

		final Packet.Builder builder = Packet.newBuilder();

		if (message == null) {
			log.warn("missing message", new NullPointerException());
			return builder;
		}

		final Class<? extends Message> klaz = message.getClass();

		final PacketType type = messageKlazToType.get(klaz);

		if (type == null) {
			log.warn("unsupported message type",
					new UnsupportedOperationException(klaz.getName()));
			return builder;
		}

		builder.setType(type);
		// builder.setMessage(message.toByteString());

		return builder;

	}

	/** encode with length-prefix before raw array */
	public static void encodeDelimited(final Packet base,
			final ByteBuffer buffer) throws Exception {

		final ByteArrayOutputStream output = new ByteArrayOutputStream(128);

		base.writeDelimitedTo(output);

		final byte[] array = output.toByteArray();

		buffer.put(array);

	}

	private PacketCodec() {
	}

}
