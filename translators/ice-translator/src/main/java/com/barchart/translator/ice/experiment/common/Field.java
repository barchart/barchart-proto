package com.barchart.translator.ice.experiment.common;

import java.nio.ByteBuffer;

public abstract class Field {

	public void visit(ByteBuffer bytes, FieldVisitor visitor) {
		visitor.visit(this);
	}

	public void visit(ByteBuffer bytes, Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

}

