package com.barchart.translator.nyl;

import java.nio.ByteBuffer;

import com.barchart.proto.buf.data.MarketEntry;
import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.buf.data.PacketType;
import com.barchart.translator.common.Translator;
import com.barchart.translator.common.exception.TranslatorException;
import com.barchart.translator.nyl.data.NYLMarketUpdate;
import com.barchart.translator.nyl.data.NYLMarketUpdate.Entry;
import com.barchart.translator.nyl.data.NYLPacketHeader;
import com.barchart.translator.nyl.data.NYLValueAddedParameters;
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
		public void visit(NYLPacketHeader header) {
			if (packetBuilder != null) {
				throw new TranslatorException("Multiple packet headers are ilelgal");
			}
			packetBuilder = MarketPacket.newBuilder();
			packetBuilder.setTimeStamp(header.getSendTime());
			packetBuilder.setSequence(header.getPacketSeqNum());
			packetBuilder.setType(PacketType.DATA);
		}

		@Override
		public void visit(NYLMarketUpdate marketUpdate, NYLMarketUpdate.Entry... entries) {

			MarketMessage.Builder messageBuilder = packetBuilder.addMessageBuilder();

			switch (marketUpdate.getSnapshotFlag()) {
			case 0:
				messageBuilder.setType(MarketMessage.Type.UPDATE);
				break;
			case 1:
				messageBuilder.setType(MarketMessage.Type.SNAPSHOT);
			}

			for (NYLMarketUpdate.Entry entry : entries) {
				MarketEntry.Builder entryBuilder = messageBuilder.addEntryBuilder();
				switch (entry.getUpdateType()) {
				case BID:
					entryBuilder.setType(MarketEntry.Type.BID);
					break;
				case OFFER:
					entryBuilder.setType(MarketEntry.Type.ASK);
				}

				entryBuilder.setPriceMantissa(entry.getPrice());
				entryBuilder.setPriceExponent(0);

				entryBuilder.setSizeMantissa(entry.getVolume());
				entryBuilder.setSizeExponent(0);
			}
		}

		@Override
		public void visit(NYLValueAddedParameters message, NYLValueAddedParameters.Entry... entries) {
			// TODO
		}

	}

}
