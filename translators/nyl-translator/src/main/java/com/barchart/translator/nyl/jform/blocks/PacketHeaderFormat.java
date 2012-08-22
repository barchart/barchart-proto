//package com.barchart.translator.nyl.jform.blocks;
//
//import com.barchart.translator.common.jform.Field;
//import com.barchart.translator.common.jform.FieldBlock;
//import com.barchart.translator.common.jform.FieldVisitor;
//import com.barchart.translator.nyl.jform.fields.DeliveryFlag;
//import com.barchart.translator.nyl.jform.fields.NumberMsgEntries;
//import com.barchart.translator.nyl.jform.fields.PacketLength;
//import com.barchart.translator.nyl.jform.fields.PacketSeqNum;
//import com.barchart.translator.nyl.jform.fields.PacketType;
//import com.barchart.translator.nyl.jform.fields.SendTime;
//import com.barchart.translator.nyl.jform.fields.ServiceID;
//import com.google.common.collect.ImmutableCollection.Builder;
//import com.google.common.collect.ImmutableList;
//
//public class PacketHeaderFormat extends FieldBlock {
//
//	@Override
//	public void populateFields(Builder<Field> fields) {
//		fields.add(new PacketLength());
////		fields.add(new PacketType());
////		fields.add(new PacketSeqNum());
////		fields.add(new SendTime());
////		fields.add(new ServiceID());
////		fields.add(new DeliveryFlag());
////		fields.add(new NumberMsgEntries());
//	}
//
//	
//	public interface PacketFieldVisitor extends FieldVisitor {
//		public void visit(PacketLength field);
//		public void visit(PacketType field);
//		public void visit(PacketSeqNum field);
//		public void visit(SendTime field);
//		public void visit(ServiceID field);
//		public void visit(DeliveryFlag field);
//		public void visit(NumberMsgEntries field);
//	}
//	
//	
//	public PrototypeShadow receiveBytes(byte[] array) {
//		return new PrototypeShadow(array, fields);
//	}
//	
//	
//	public static class PrototypeShadow {
//		
//		private final byte[] array;
//		private ImmutableList<Field> fields;
//
//		public PrototypeShadow(byte[] array, ImmutableList<Field> fields) {
//			this.array = array;
//			this.fields = fields;
//		}
//		
//		public void run() {
//			
//		}
//		
//	}
//
//
//	public void accept(PacketFieldVisitor nylPrint) {
//		for (Field field : fields) {
////			nylPrint.visit(field);
//		}
//	}
//
//}
