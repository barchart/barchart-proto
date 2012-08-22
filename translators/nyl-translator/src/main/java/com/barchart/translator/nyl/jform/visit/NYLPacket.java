package com.barchart.translator.nyl.jform.visit;

import java.nio.ByteBuffer;

import com.barchart.translator.common.jform.FieldBlock;
import com.barchart.translator.nyl.jform.visit.NYLField.DeliveryFlag;
import com.barchart.translator.nyl.jform.visit.NYLField.NumberMsgEntries;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketLength;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketSeqNum;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketType;
import com.barchart.translator.nyl.jform.visit.NYLField.SendTime;
import com.barchart.translator.nyl.jform.visit.NYLField.ServiceID;
import com.barchart.translator.nyl.jform.visit.enums.PacketTypeEnum;
import com.google.common.collect.ImmutableCollection.Builder;

public class NYLPacket extends FieldBlock<NYLField> {

	private static final PacketLength PACKET_LENGTH = new PacketLength();
	public static final PacketType PACKET_TYPE = new PacketType();
	public static final PacketSeqNum PACKET_SEQ_NUM = new PacketSeqNum();
	public static final SendTime SEND_TIME = new SendTime();
	public static final ServiceID SERVICE_ID = new ServiceID();
	public static final DeliveryFlag DELIVERY_FLAG = new DeliveryFlag();
	public static final NumberMsgEntries NUMBER_MSG_ENTRIES = new NumberMsgEntries();

	@Override
	public void populateFields(Builder<NYLField> fields) {
		fields.add(new PacketLength());
		fields.add(new PacketType());
		fields.add(new PacketSeqNum());
		fields.add(new SendTime());
		fields.add(new ServiceID());
		fields.add(new DeliveryFlag());
		fields.add(new NumberMsgEntries());
	}

	public void visitFields(ByteBuffer buffer, NYLFieldVisitor visitor) {
		visitor.visit(PACKET_LENGTH);
		visitor.visit(PACKET_TYPE);
		visitor.visit(PACKET_SEQ_NUM);
		visitor.visit(SEND_TIME);
		visitor.visit(SERVICE_ID);
		visitor.visit(DELIVERY_FLAG);
		visitor.visit(NUMBER_MSG_ENTRIES);
	}

	public interface Handler {
		public void packetLength(int value);
		public void packetType(PacketTypeEnum value);
		public void packetSeqNum(long value);
		public void sendTime(long value);
		public void serviceID(int value);
		public void deliveryFlag(int value);
		public void numberMsgEntries(int value);
	}
	
	
	public void readFields(ByteBuffer buffer, Handler handler) {
		handler.packetLength(PACKET_LENGTH.getValue(buffer));
		handler.packetType(PACKET_TYPE.getValue(buffer));
		handler.packetSeqNum(PACKET_SEQ_NUM.getValue(buffer));
		handler.sendTime(SEND_TIME.getValue(buffer));
		handler.serviceID(SERVICE_ID.getValue(buffer));
		handler.deliveryFlag(DELIVERY_FLAG.getValue(buffer));
		handler.numberMsgEntries(NUMBER_MSG_ENTRIES.getValue(buffer));
	}

	
	public static class MessageTypeUnion implements NYLField {

		private MsgSize msgSize;
		private MsgType msgType;

		public MessageTypeUnion() {
			msgSize = new MsgSize();
			msgType = new MsgType();
		}

		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			visitor = msgSize.accept(visitor);
			visitor = msgType.accept(visitor);
			return visitor;
		}

	}

}
