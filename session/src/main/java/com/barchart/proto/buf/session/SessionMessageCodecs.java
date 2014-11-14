package com.barchart.proto.buf.session;

import java.io.IOException;

import org.openfeed.messaging.MessageCodec;

// agh, boilerplate.  How cool would this be if it was generated via method handles or bytecode injection?  Probably a waste of time and impossible to debug, though
public class SessionMessageCodecs {

	public static final MessageCodec<CapabilitiesMessage> CAPABILITIES_CODEC = new MessageCodec<CapabilitiesMessage>() {

		@Override
		public CapabilitiesMessage decode(byte[] bytes) throws IOException {
			return CapabilitiesMessage.parseFrom(bytes);
		}

		@Override
		public byte[] encode(CapabilitiesMessage message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<CapabilitiesMessage> getMessageClass() {
			return CapabilitiesMessage.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartSessionMessageType.CAPABILITIES_VALUE;
		}

	};

	public static final MessageCodec<StartTLSMessage> START_TLS_CODEC = new MessageCodec<StartTLSMessage>() {

		@Override
		public StartTLSMessage decode(byte[] bytes) throws IOException {
			return StartTLSMessage.parseFrom(bytes);
		}

		@Override
		public byte[] encode(StartTLSMessage message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<StartTLSMessage> getMessageClass() {
			return StartTLSMessage.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartSessionMessageType.START_TLS_VALUE;
		}

	};

	public static final MessageCodec<StopTLSMessage> STOP_TLS_CODEC = new MessageCodec<StopTLSMessage>() {

		@Override
		public StopTLSMessage decode(byte[] bytes) throws IOException {
			return StopTLSMessage.parseFrom(bytes);
		}

		@Override
		public byte[] encode(StopTLSMessage message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<StopTLSMessage> getMessageClass() {
			return StopTLSMessage.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartSessionMessageType.STOP_TLS_VALUE;
		}

	};

	public static final MessageCodec<PingMessage> PING_CODEC = new MessageCodec<PingMessage>() {

		@Override
		public PingMessage decode(byte[] bytes) throws IOException {
			return PingMessage.parseFrom(bytes);
		}

		@Override
		public byte[] encode(PingMessage message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<PingMessage> getMessageClass() {
			return PingMessage.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartSessionMessageType.PING_VALUE;
		}

	};

	public static final MessageCodec<PongMessage> PONG_CODEC = new MessageCodec<PongMessage>() {

		@Override
		public PongMessage decode(byte[] bytes) throws IOException {
			return PongMessage.parseFrom(bytes);
		}

		@Override
		public byte[] encode(PongMessage message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<PongMessage> getMessageClass() {
			return PongMessage.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartSessionMessageType.PONG_VALUE;
		}

	};

	public static final MessageCodec<AuthRequestMessage> AUTH_REQUEST_CODEC = new MessageCodec<AuthRequestMessage>() {

		@Override
		public AuthRequestMessage decode(byte[] bytes) throws IOException {
			return AuthRequestMessage.parseFrom(bytes);
		}

		@Override
		public byte[] encode(AuthRequestMessage message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<AuthRequestMessage> getMessageClass() {
			return AuthRequestMessage.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartSessionMessageType.AUTH_REQUEST_VALUE;
		}

	};

	public static final MessageCodec<AuthResponseMessage> AUTH_RESPONSE_CODEC = new MessageCodec<AuthResponseMessage>() {

		@Override
		public AuthResponseMessage decode(byte[] bytes) throws IOException {
			return AuthResponseMessage.parseFrom(bytes);
		}

		@Override
		public byte[] encode(AuthResponseMessage message) throws IOException {
			return message.toByteArray();
		}

		@Override
		public Class<AuthResponseMessage> getMessageClass() {
			return AuthResponseMessage.class;
		}

		@Override
		public int getTypeCode() {
			return BarchartSessionMessageType.AUTH_RESPONSE_VALUE;
		}

	};


	public static final MessageCodec<SessionResponseMessage> SESSION_RESPONSE_CODEC =
			new MessageCodec<SessionResponseMessage>() {

				@Override
				public SessionResponseMessage decode(byte[] bytes) throws IOException {
					return SessionResponseMessage.parseFrom(bytes);
				}

				@Override
				public byte[] encode(SessionResponseMessage message) throws IOException {
					return message.toByteArray();
				}

				@Override
				public Class<SessionResponseMessage> getMessageClass() {
					return SessionResponseMessage.class;
				}

				@Override
				public int getTypeCode() {
					return BarchartSessionMessageType.SESSION_RESPONSE_VALUE;
				}

			};

	// TODO:  Add the rest of them. Are they being used?
}
