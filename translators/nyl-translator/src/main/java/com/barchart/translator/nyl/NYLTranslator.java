package com.barchart.translator.nyl;

import java.nio.ByteBuffer;

import com.barchart.proto.buf.data.MarketEntry;
import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.buf.data.PacketType;
import com.barchart.translator.common.Translator;
import com.barchart.translator.common.exception.TranslatorException;
import com.barchart.translator.nyl.data.MarketUpdate;
import com.barchart.translator.nyl.data.PacketHeader;
import com.barchart.translator.nyl.data.parse.NYLPacketParser;
import com.barchart.translator.nyl.data.parse.NYLPacketVisitor;

public class NYLTranslator implements Translator {

	private final NYLPacketParser parser;

	public NYLTranslator() {
		parser = new NYLPacketParser();
	}
	
	@Override
	public MarketPacket translate(ByteBuffer byteBuffer) {
		BUFBuilder bufBuilder = new BUFBuilder();
		parser.parse(byteBuffer, bufBuilder);
		return bufBuilder.getBUFPacket();
	}
	
	private static class BUFBuilder implements NYLPacketVisitor {

		private MarketPacket.Builder packetBuilder;
		private MarketMessage.Builder messageBuilder;

		BUFBuilder() {
			this.packetBuilder = null;
		}
		
		
		public MarketPacket getBUFPacket() {
			if (packetBuilder == null) {
				throw new TranslatorException("No packet to decode");
			} else {
				return packetBuilder.build();
			}
		}

		@Override
		public void visit(PacketHeader header) {
			if (packetBuilder != null) {
				throw new TranslatorException("Multiple packet headers are ilelgal");
			}
			packetBuilder = MarketPacket.newBuilder();
			packetBuilder.setTimeStamp(header.getSendTime());
			packetBuilder.setSequence(header.getPacketSeqNum());
			packetBuilder.setType(PacketType.DATA);
		}

		@Override
		public void visit(MarketUpdate marketUpdate) {
			messageBuilder = packetBuilder.addMessageBuilder();
			
			switch (marketUpdate.getSnapshotFlag()) {
			case 0:
				messageBuilder.setType(MarketMessage.Type.UPDATE);
				break;
			case 1:
				messageBuilder.setType(MarketMessage.Type.SNAPSHOT);
			}
		}

		@Override
		public void visit(MarketUpdate.Entry entry) {
			if (messageBuilder == null) {
				throw new TranslatorException("No message for MarketUpdate Entry");
			}
			MarketEntry.Builder entryBuilder = messageBuilder.addEntryBuilder();
			switch (entry.getUpdateType()) {
			case BID:
				entryBuilder.setType(MarketEntry.Type.BID);
				break;
			case OFFER:
				entryBuilder.setType(MarketEntry.Type.ASK);
			}
		}
		
	}

}
