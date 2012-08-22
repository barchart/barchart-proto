package com.barchart.translator.ice.experiment.messages;

import com.barchart.translator.common.jform.Field;
import com.barchart.translator.common.jform.FieldBlock;
import com.barchart.translator.common.jform.MessageBlock;
import com.barchart.translator.ice.experiment.fields.NumberOfMsgs;
import com.barchart.translator.ice.experiment.fields.SentDateTime;
import com.barchart.translator.ice.experiment.fields.SequenceNumber;
import com.barchart.translator.ice.experiment.fields.SessionNumber;
import com.google.common.collect.ImmutableCollection.Builder;

public class ICEPacket extends FieldBlock {

	@Override
	public void populateFields(Builder<Field> fields) {
		fields.add(new SessionNumber());
		fields.add(new SequenceNumber());
		fields.add(new NumberOfMsgs());
		fields.add(new SentDateTime());
		fields.add(new MessageBlock());
	}
	
}
