package com.barchart.translator.ice.experiment.fields;

import java.nio.ByteBuffer;

import com.barchart.translator.ice.experiment.ICEVisitor;
import com.barchart.translator.ice.experiment.common.ShortField;


public class SessionNumber extends ShortField {

	public void visit(ByteBuffer bytes, ICEVisitor visitor) {
		visitor.visitSessionNumber(getValue(bytes));
	}
	
}
