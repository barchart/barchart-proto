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

import com.barchart.translator.nyl.jform.facade.MarketUpdate;
import com.barchart.translator.nyl.jform.facade.MarketUpdate.Entry;
import com.barchart.translator.nyl.jform.facade.NYLPacketHeader;
import com.barchart.translator.nyl.jform.facade.NYLPacketParser;
import com.barchart.translator.nyl.jform.facade.NYLPacketVisitor;
import com.barchart.translator.nyl.jform.visit.NYLField;
import com.barchart.translator.nyl.jform.visit.NYLField.DeliveryFlag;
import com.barchart.translator.nyl.jform.visit.NYLField.Filler;
import com.barchart.translator.nyl.jform.visit.NYLField.MsgSize;
import com.barchart.translator.nyl.jform.visit.NYLField.MsgType;
import com.barchart.translator.nyl.jform.visit.NYLField.NumberMsgEntries;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketLength;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketSeqNum;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketType;
import com.barchart.translator.nyl.jform.visit.NYLField.SecurityID;
import com.barchart.translator.nyl.jform.visit.NYLField.SecurityIDSource;
import com.barchart.translator.nyl.jform.visit.NYLField.SendTime;
import com.barchart.translator.nyl.jform.visit.NYLField.SeriesSequenceNumber;
import com.barchart.translator.nyl.jform.visit.NYLField.ServiceID;
import com.barchart.translator.nyl.jform.visit.NYLField.SnapshotFlag;
import com.barchart.translator.nyl.jform.visit.NYLField.SourceTime;
import com.barchart.translator.nyl.jform.visit.NYLField.UpdateCount;
import com.barchart.translator.nyl.jform.visit.NYLFieldVisitor;
import com.barchart.translator.nyl.jform.visit.NYLPacket;

public class MessageLogger {

	private static final Logger logger = LoggerFactory.getLogger(MessageLogger.class);

	@Test
	public void logMessages() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("/home/joel/buf/nyl-metals-l2-a.packets");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		Replayer replayer = new Replayer(bis);
		byte[] array;
		NYLPacket nylPacket = new NYLPacket();
		
		NYLPacketParser parser = new NYLPacketParser();
		while ((array = replayer.nextPayload()) != null) {
			
			
			
			parser.parse(ByteBuffer.wrap(array), new PacketVisitor());
			
			
//			NYLPrint nylPrint = new NYLPrint(ByteBuffer.wrap(array));
//			nylPacket.readFields(nylPrint);
		}
	}

	
	private static class PacketVisitor implements NYLPacketVisitor {

		@Override
		public void visitPacketHeader(NYLPacketHeader header) {
			logger.info(header.toString());
		}

		@Override
		public void visitMarketUpdate(MarketUpdate marketUpdate) {
			logger.info(marketUpdate.toString());
		}

		@Override
		public void visitMarketUpdateEntry(Entry entry) {
			logger.info(entry.toString());			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static class NYLPrint implements NYLFieldVisitor {

		private final ByteBuffer buffer;

		public NYLPrint(ByteBuffer buffer) {
			this.buffer = buffer;
		}

		@Override
		public NYLFieldVisitor visit(PacketLength field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(PacketType field) {
//			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(PacketSeqNum field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(SendTime field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(ServiceID field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(DeliveryFlag field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(NumberMsgEntries field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(MsgSize field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(MsgType field) {
			
			
			return this;
		}

		@Override
		public NYLFieldVisitor visit(SourceTime field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(SeriesSequenceNumber field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(SecurityIDSource field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(SecurityID field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(SnapshotFlag field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(Filler field) {
			print(field, field.getValue(buffer));
			return this;
		}

		@Override
		public NYLFieldVisitor visit(UpdateCount field) {
			print(field, field.getValue(buffer));
			return this;
		}

		private void print(NYLField field, byte value) {
			System.out.println(field.toString() + " = " + value);
		}

		private void print(NYLField field, int value) {
			System.out.println(field.toString() + " = " + value);
		}

		private void print(NYLField field, short value) {
			System.out.println(field.toString() + " = " + value);
		}

		private void print(NYLField field, long value) {
			System.out.println(field.toString() + " = " + value);
		}

		private void print(SecurityID field, String value) {
			System.out.println(field.toString() + " = " + value);
		}

	}

}
