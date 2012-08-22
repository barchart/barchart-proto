package com.barchart.translator.ice.jform.prototypes;

import java.nio.ByteBuffer;

import com.barchart.translator.common.jform.Field;
import com.barchart.translator.common.jform.JFormField;
import com.barchart.translator.common.jform.MessageBlock;
import com.barchart.translator.ice.experiment.fields.NumberOfMsgs;
import com.barchart.translator.ice.experiment.fields.SentDateTime;
import com.barchart.translator.ice.experiment.fields.SequenceNumber;
import com.barchart.translator.ice.experiment.fields.SessionNumber;
import com.google.common.collect.ImmutableCollection.Builder;

public class PacketPrototype extends Prototype {

	public void populateFields(Builder<Field> fields) {
		fields.add(new SessionNumber());
		fields.add(new SequenceNumber());
		fields.add(new NumberOfMsgs());
		fields.add(new SentDateTime());
		fields.add(new MessageBlock());
	}
	
	public void accept(ByteBuffer bytes, Visitor visitor) {
		for (Field field : getFields()) {
//			field.visit(bytes, visitor);
		}
	}
	
	

	public interface Visitor {
		
		@JFormField
		public void readSessionNumber(SessionNumber sessionNumber);
	}
	
}
