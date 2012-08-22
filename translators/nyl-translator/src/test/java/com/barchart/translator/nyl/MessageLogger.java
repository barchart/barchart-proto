package com.barchart.translator.nyl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.xform.nyl.NYL.Body.Visitor;
import com.barchart.translator.nyl.data.NYLMarketUpdate;
import com.barchart.translator.nyl.data.NYLPacketHeader;
import com.barchart.translator.nyl.data.NYLMarketUpdate.Entry;
import com.barchart.translator.nyl.data.NYLValueAddedParameters;
import com.barchart.translator.nyl.data.parse.NYLPacketParser;
import com.barchart.translator.nyl.data.parse.NYLPacketVisitor;

public class MessageLogger {

	private static final Logger logger = LoggerFactory.getLogger(MessageLogger.class);

	@Test
	public void logMessages() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("/home/joel/buf/nyl-metals-l2-a.packets");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		Replayer replayer = new Replayer(bis);
		NYLPacketParser parser = new NYLPacketParser();
		byte[] array;
		NYLTranslator translator = new NYLTranslator();

		while ((array = replayer.nextPayload()) != null) {
			PacketVisitor visitor = new PacketVisitor();
			parser.parse(ByteBuffer.wrap(array), visitor);

			MarketPacket translate = translator.translate(ByteBuffer.wrap(array));
			 System.out.println(translate);
			System.out.println(visitor.toString());
		}
	}

	private static class PacketVisitor implements NYLPacketVisitor {

		private StringBuilder builder;

		public PacketVisitor() {
			this.builder = new StringBuilder();
		}

		@Override
		public void visit(NYLPacketHeader header) {
			append("PacketLength", header.getPacketLength());
			append("PacketType", header.getPacketType());
			append("PacketSeqNum", header.getPacketSeqNum());
			append("SendTime", header.getSendTime());
			append("ServiceID", header.getServiceID());
			append("DeliveryFlag", header.getDeliveryFlag());
			append("NumMsgEntries", header.getNumberMsgEntries());
		}

		@Override
		public String toString() {
			return builder.toString();
		}

		private void append(String fieldName, int value) {
			append(fieldName, Integer.valueOf(value));
		}

		private void append(String fieldName, long value) {
			append(fieldName, Long.valueOf(value));
		}

		private void append(String fieldName, Object value) {
			builder.append(fieldName + "\t\t" + value + "\n");
		}

		@Override
		public void visit(NYLMarketUpdate marketUpdate, NYLMarketUpdate.Entry... entries) {
			append("-- MsgSize", marketUpdate.getMsgSize());
			append("-- MsgType", marketUpdate.getMsgType());
			append("-- SourceTime", marketUpdate.getSourceTime());
			append("-- SeriesSeqNum", marketUpdate.getSereiesSequenceNumber());
			append("-- SecurityIDSource", marketUpdate.getSecurityIDSource());
			append("-- SnapshotFlag", marketUpdate.getSnapshotFlag());
			append("-- UpdateCount", marketUpdate.getUpdateCount());

			for (NYLMarketUpdate.Entry entry : entries) {
				append("----* UpdateType", entry.getUpdateType());
				append("----* Price", entry.getPrice());
				append("----* Volume", entry.getVolume());
				builder.append("\n");
			}

		}

		@Override
		public void visit(NYLValueAddedParameters message, com.barchart.translator.nyl.data.NYLValueAddedParameters.Entry... entries) {
			// TODO Auto-generated method stub

		}

	}

}
