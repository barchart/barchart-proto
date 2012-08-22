package com.barchart.translator.nyl.data.parse;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.common.data.ByteFacade;
import com.barchart.translator.common.data.OffsetByteReader;
import com.barchart.translator.nyl.data.NYLMarketUpdate;
import com.barchart.translator.nyl.data.NYLPacketHeader;
import com.barchart.translator.nyl.data.NYLValueAddedParameters;
import com.barchart.translator.nyl.data.enums.NYLMessageType;
import com.barchart.translator.nyl.data.impl.NYLMarketUpdateImpl;
import com.barchart.translator.nyl.data.impl.NYLPacketHeaderImpl;
import com.barchart.translator.nyl.data.impl.NYLValueAddedParametersImpl;

public class NYLPacketParser {

	private static final Logger logger = LoggerFactory.getLogger(NYLPacketParser.class);

	public NYLPacketParser() {

	}

	public void parse(ByteBuffer buffer, NYLPacketVisitor visitor) {
		Packet packet = new Packet(new ByteFacade(buffer.array()), visitor);
		packet.accept(visitor);
	}

	private static class Packet extends OffsetByteReader {

		private final NYLPacketVisitor visitor;

		private int offset = 0;

		protected Packet(ByteFacade bytes, NYLPacketVisitor visitor) {
			super(0, bytes);
			this.visitor = visitor;
		}

		public void accept(NYLPacketVisitor visitor) {
			NYLPacketHeader header = new NYLPacketHeaderImpl(offset, bytes);
			visitor.visit(header);
			offset = NYLPacketHeaderImpl.HEADER_SIZE;
			while (offset < bytes.length()) {
				int bytesConsumed = handleMessage(getNextSubMessageType());
				offset += bytesConsumed;
			}
		}

		private NYLMessageType getNextSubMessageType() {
			int code = bytes.unsignedShort(offset + 2);
			return NYLMessageType.fromCode(code);
		}

		private int handleMessage(NYLMessageType messageType) {
			switch (messageType) {
			case MARKET_UPDATE_V1:
				return handleMarketUpdate();
			case VALUE_ADDED_PARAMETERS:
				return handleValueAddedParameters();
			default:
				throw new IllegalStateException("Unsupported message type: " + messageType);
			}

		}

		private int handleValueAddedParameters() {
			NYLValueAddedParameters message = new NYLValueAddedParametersImpl(offset, bytes);
			int entryOffset = offset + 28;
			NYLValueAddedParameters.Entry[] entries = new NYLValueAddedParameters.Entry[message.getUpdateCount()];
			for (int i = 0; i < message.getUpdateCount(); i++) {
				NYLValueAddedParameters.Entry entry = new NYLValueAddedParametersImpl.EntryImpl(entryOffset, bytes);
				entries[i] = entry;
				entryOffset += 12;
			}
			visitor.visit(message, entries);
			return message.getMsgSize() + 2;
		}

		private int handleMarketUpdate() {
			NYLMarketUpdate message = new NYLMarketUpdateImpl(offset, bytes);
			int entryOffset = offset + 32;
			NYLMarketUpdate.Entry[] entries = new NYLMarketUpdate.Entry[message.getUpdateCount()];
			for (int i = 0; i < message.getUpdateCount(); i++) {
				NYLMarketUpdate.Entry entry = new NYLMarketUpdateImpl.EntryImpl(entryOffset, bytes);
				entries[i] = entry;
				entryOffset += 12;
			}
			visitor.visit(message, entries);
			return message.getMsgSize() + 2;
		}

	}

}
