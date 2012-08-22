package com.barchart.translator.ice.experiment.visitors;

import java.nio.ByteBuffer;

import com.barchart.translator.common.jform.Field;
import com.barchart.translator.ice.experiment.ICEVisitor;

public class ConcreteICEVisitor implements ICEVisitor {


	public ConcreteICEVisitor(ByteBuffer bytes) {
	}
	
	
	@Override
	public void visit(Field field) {
		
	}

	@Override
	public void visitSessionNumber(short value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSequenceNumber(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitMessageType(char value) {
		// TODO Auto-generated method stub
		
	}

}
