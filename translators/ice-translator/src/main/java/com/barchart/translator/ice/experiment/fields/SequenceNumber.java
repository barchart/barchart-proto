package com.barchart.translator.ice.experiment.fields;

import java.nio.ByteBuffer;

import com.barchart.translator.ice.experiment.ICEVisitor;
import com.barchart.translator.ice.experiment.common.IntegerField;

public class SequenceNumber extends IntegerField {

	@Override
	public void visit(ByteBuffer bytes, ICEVisitor visitor) {
		visitor.visitSequenceNumber(getValue(bytes));
	}
	
}
