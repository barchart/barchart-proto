package com.barchart.translator.nyl.data.parse;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.common.data.ByteFacade;
import com.barchart.translator.common.data.OffsetByteReader;
import com.barchart.translator.nyl.data.MarketUpdate;
import com.barchart.translator.nyl.data.impl.MarketUpdateImpl;
import com.barchart.translator.nyl.data.impl.NYLPacketHeaderImpl;

public class NYLPacketParser {

	public NYLPacketParser() {

	}

	public void parse(ByteBuffer buffer, NYLPacketVisitor visitor) {
		Packet packet = new Packet(new ByteFacade(buffer.array()), visitor);
		packet.accept(visitor);
	}

	private static class Packet extends OffsetByteReader {
		private static final Logger logger = LoggerFactory.getLogger(Packet.class);

		private final NYLPacketVisitor visitor;

		private int offset = 0;

		protected Packet(ByteFacade bytes, NYLPacketVisitor visitor) {
			super(0, bytes);
			this.visitor = visitor;
		}

		public void accept(NYLPacketVisitor visitor) {
			NYLPacketHeaderImpl header = new NYLPacketHeaderImpl(offset, bytes);
			header.accept(visitor);

			offset = 16;

			while (offset < bytes.length()) {
				MessageDescriptor messageDescriptor = new MessageDescriptor(offset, bytes);
				switch (messageDescriptor.getMsgType()) {
				case 701:
					handleMarketUpdate();
					break;
				default:
					logger.error("Unknown message type: " + messageDescriptor.getMsgType());
					// throw new IllegalStateException();
				}
				offset += messageDescriptor.getMsgLength() + 2;
			}

		}

		private void handleMarketUpdate() {
			MarketUpdate marketUpdate = new MarketUpdateImpl(offset, bytes);
			visitor.visit(marketUpdate);
			int entryOffset = offset + 32;
			for (int i = 0; i < marketUpdate.getUpdateCount(); i++) {
				MarketUpdate.Entry entry = new MarketUpdateImpl.EntryImpl(entryOffset, bytes);
				visitor.visit(entry);
				entryOffset += 12;
			}
		}

	}

	private static class MessageDescriptor extends OffsetByteReader {

		protected MessageDescriptor(int baseOffset, ByteFacade bytes) {
			super(baseOffset, bytes);
		}

		public int getMsgLength() {
			return bytes.unsignedShort(offset(0));
		}

		public int getMsgType() {
			return bytes.unsignedShort(offset(2));
		}

	}

}
