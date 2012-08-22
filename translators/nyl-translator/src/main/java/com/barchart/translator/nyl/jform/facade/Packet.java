package com.barchart.translator.nyl.jform.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.nyl.MessageLogger;

public class Packet extends OffsetByteReader {
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
//				throw new IllegalStateException();
			}
			offset += messageDescriptor.getMsgLength() + 2;
		}
		
	}
	
	private void handleMarketUpdate() {
		MarketUpdateImpl marketUpdate = new MarketUpdateImpl(offset, bytes);
		marketUpdate.accept(visitor);
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