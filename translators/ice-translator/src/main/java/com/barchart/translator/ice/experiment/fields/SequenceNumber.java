package com.barchart.translator.ice.experiment.fields;

import java.nio.ByteBuffer;

import com.barchart.translator.common.jform.IntegerField;
import com.barchart.translator.ice.experiment.ICEVisitor;

public class SequenceNumber extends IntegerField {

	public void visit(ByteBuffer bytes, ICEVisitor visitor) {
		visitor.visitSequenceNumber(getValue(bytes));
	}
	
}
