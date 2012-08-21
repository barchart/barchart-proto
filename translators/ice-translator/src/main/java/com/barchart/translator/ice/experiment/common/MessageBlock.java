package com.barchart.translator.ice.experiment.common;

import java.nio.ByteBuffer;

import com.barchart.translator.common.lookup.ValueLookup;
import com.barchart.translator.ice.experiment.ICEVisitor;
import com.barchart.translator.ice.experiment.fields.TradeMessage;
import com.barchart.translator.ice.experiment.messages.MarketSnapshotMessage;
import com.barchart.translator.ice.experiment.messages.MarketSnapshotOrderMessage;
import com.google.common.collect.ImmutableMap.Builder;

public class MessageBlock extends CharField {

	private static final ValueLookup<Character, FieldBlock> lookup = new ValueLookup<Character, FieldBlock>() {
		@Override
		public void init(Builder<Character, FieldBlock> builder) {
			builder.put('C', new MarketSnapshotMessage());
			builder.put('G', new TradeMessage());
			builder.put('D', new MarketSnapshotOrderMessage());
			
		}
	};
	
	@Override
	public void visit(ByteBuffer bytes, ICEVisitor visitor) {
		char value = getValue(bytes);
		FieldBlock fieldBlock = lookup.lookup(value);
		fieldBlock.visit(bytes, visitor);
	}

	
	
}
