package com.barchart.translator.ice.experiment.messages;

import com.barchart.translator.ice.experiment.common.Field;
import com.barchart.translator.ice.experiment.common.FieldBlock;
import com.barchart.translator.ice.experiment.fields.MarketID;
import com.barchart.translator.ice.experiment.fields.MessageBodyLength;
import com.barchart.translator.ice.experiment.fields.OrderID;
import com.barchart.translator.ice.experiment.fields.OrderSequenceID;
import com.google.common.collect.ImmutableCollection.Builder;

public class MarketSnapshotOrderMessage extends FieldBlock {

	@Override
	public void populateFields(Builder<Field> fields) {
		fields.add(new MessageBodyLength());
		fields.add(new MarketID());
		fields.add(new OrderID());
		fields.add(new OrderSequenceID());

	}

}
