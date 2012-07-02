package com.barchart.proto.buf;

import static com.barchart.proto.buf.MessageSpec.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;
import com.google.protobuf.Message;

public class MessageCodec {

	private static final Logger log = LoggerFactory
			.getLogger(MessageCodec.class);

	/** from specific message type into type/extension */
	private static final Map<Class<Message>, MessageMeta> //
	messageMetaMap = new ConcurrentHashMap<Class<Message>, MessageMeta>();

	private static final ExtensionRegistry //
	registry = ExtensionRegistry.newInstance();

	static {

		prepareExtensions(registry);

	}

	/**
	 * note: 1) extension name contract; 2) enum name contract; 3) enum +
	 * extension number contract
	 */
	private static void prepareExtensions(final ExtensionRegistry registry) {

		MessageSpec.registerAllExtensions(registry);

		final String prefix = Base.getDescriptor().getOptions()
				.getExtension(MessageSpec.optionExtensionPrefix);

		for (final Field field : MessageSpec.class.getDeclaredFields()) {

			final String fieldName = field.getName();
			final Class<?> fieldType = field.getType();

			final boolean isNameMatch = fieldName.startsWith(prefix);
			final boolean isTypeMatch = GeneratedExtension.class
					.isAssignableFrom(fieldType);

			final boolean isMessageExtension = isNameMatch && isTypeMatch;

			if (isMessageExtension) {

				try {

					@SuppressWarnings("unchecked")
					final GeneratedExtension<Base, Message> extension = //
					(GeneratedExtension<Base, Message>) field.get(null);

					@SuppressWarnings("unchecked")
					final Class<Message> klaz = (Class<Message>) extension
							.getMessageDefaultInstance().getClass();

					final int number = extension.getDescriptor().getNumber();

					final MessageType type = MessageType.valueOf(number);

					final MessageMeta meta = new MessageMeta(type, extension);

					messageMetaMap.put(klaz, meta);

				} catch (final Exception e) {
					log.error("can not prepare message extenstion", e);
				}

			}

		}

	}

	@SuppressWarnings({ "unchecked", "unused" })
	private static <MESSAGE extends Message> MESSAGE castType(final Base base,
			final Class<MESSAGE> klaz) throws Exception {

		final GeneratedExtension<Base, Message> extension = messageMetaMap
				.get(klaz).extension;

		return (MESSAGE) base.getExtension(extension);

	}

	public static Base decode(final byte[] array) throws Exception {

		final Base.Builder builder = Base.newBuilder();

		builder.mergeFrom(array, registry);

		return builder.build();

	}

	public static Base decodeDelimited(final byte[] array) throws Exception {

		final ByteArrayInputStream input = new ByteArrayInputStream(array);

		final Base.Builder builder = Base.newBuilder();

		builder.mergeDelimitedFrom(input, registry);

		return builder.build();

	}

	/** TODO more message types */
	public static <MARKET> void decode(final MessageVisitor<MARKET> visitor,
			final MARKET market, final Base base) throws Exception {

		if (!base.hasType()) {
			log.debug("missing type : {}", base);
			return;
		}

		final MessageType type = base.getType();

		switch (type) {
		case MarketDataType:
			visitor.apply(base.getExtension(messageMarketData), market);
			break;
		case MarketNewsType:
			visitor.apply(base.getExtension(messageMarketNews), market);
			break;

		default:
			log.error("missing message type : {}", type);
			break;
		}

	}

	public static <MARKET> void decode(final MessageVisitor<MARKET> visitor,
			final MARKET market, final byte[] array) throws Exception {

		final Base base = decode(array);

		decode(visitor, market, base);

	}

	public static <MARKET> void decodeDelimited(
			final MessageVisitor<MARKET> visitor, final MARKET market,
			final byte[] array) throws Exception {

		final Base base = decodeDelimited(array);

		decode(visitor, market, base);

	}

	public static <MESSAGE extends Message> Base encode(final MESSAGE message) {

		final Class<? extends Message> klaz = message.getClass();

		final MessageMeta meta = messageMetaMap.get(klaz);

		final Base.Builder builder = Base.newBuilder();

		builder.setType(meta.type);
		builder.setExtension(meta.extension, message);

		return builder.build();

	}

	public static byte[] encode(final Base base) throws Exception {

		final ByteArrayOutputStream output = new ByteArrayOutputStream(128);

		base.writeTo(output);

		final byte[] array = output.toByteArray();

		return array;

	}

	public static <MESSAGE extends Message> void encodeDelimited(
			final MESSAGE message, final ByteBuffer buffer) throws Exception {

		final Base base = encode(message);

		final ByteArrayOutputStream output = new ByteArrayOutputStream(128);

		base.writeDelimitedTo(output);

		final byte[] array = output.toByteArray();

		buffer.put(array);

	}

}
