package com.barchart.translator.nyl.jform.visit;

import java.nio.ByteBuffer;

import com.barchart.translator.common.jform.ByteField;
import com.barchart.translator.common.jform.EnumField;
import com.barchart.translator.common.jform.Field;
import com.barchart.translator.common.jform.FieldBlock;
import com.barchart.translator.common.jform.IntegerField;
import com.barchart.translator.common.jform.ShortField;
import com.barchart.translator.common.jform.StringField;
import com.barchart.translator.nyl.jform.visit.enums.PacketTypeEnum;

public interface NYLField {

	public NYLFieldVisitor accept(NYLFieldVisitor visitor);

	public static class PacketLength extends ShortField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class PacketType extends EnumField<PacketTypeEnum> implements NYLField {
		
		public PacketType() {
			super(PacketTypeEnum.class);
		}

		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}

		@Override
		protected Object readKey(ByteBuffer buffer) {
			short shortKey = buffer.getShort();
			return Short.valueOf(shortKey);
		}
	}

	public static class PacketSeqNum extends IntegerField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class SendTime extends IntegerField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class ServiceID extends ShortField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class DeliveryFlag extends ByteField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class NumberMsgEntries extends ByteField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class MsgSize extends ShortField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class MsgType extends EnumField<MsgType.Enum> implements NYLField {
		
		private static final MarketUpdate MARKET_UPDATE_FIELD_BLOCK = new MarketUpdate();
		

		public enum Enum {
			MARKET_UPDATE_V_1 {
				@Override
				public NYLFieldBlock getFieldBlock() {
					return MARKET_UPDATE_FIELD_BLOCK;
				}
			};
			
			
			public abstract NYLFieldBlock getFieldBlock();
			
		}

		public MsgType() {
			super(Enum.class);
		}
		
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class SourceTime extends IntegerField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class SeriesSequenceNumber extends IntegerField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class SecurityIDSource extends ByteField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class SecurityID extends StringField implements NYLField {
		public SecurityID() {
			super(15);
		}

		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class SnapshotFlag extends ByteField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class Filler extends Field implements NYLField {

		private final int bytesToIgnore;

		public Filler(int bytesToIgnore) {
			this.bytesToIgnore = bytesToIgnore;
		}

		public int getValue(ByteBuffer buffer) {
			for (int i = 0; i < bytesToIgnore; i++) {
				buffer.get();
			}
			return bytesToIgnore;
		}

		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}

	public static class UpdateCount extends ShortField implements NYLField {
		@Override
		public NYLFieldVisitor accept(NYLFieldVisitor visitor) {
			return visitor.visit(this);
		}
	}
	
	

}
