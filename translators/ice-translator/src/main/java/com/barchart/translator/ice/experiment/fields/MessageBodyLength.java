package com.barchart.translator.ice.experiment.fields;

import java.nio.ByteBuffer;

import com.barchart.translator.ice.experiment.ICEVisitor;
import com.barchart.translator.ice.experiment.common.ShortField;

public class MessageBodyLength extends ShortField {

	@Override
	public void visit(ByteBuffer bytes, ICEVisitor visitor) {
		
	}
	
}
