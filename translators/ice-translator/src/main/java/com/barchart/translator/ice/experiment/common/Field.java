package com.barchart.translator.ice.experiment.common;

import java.nio.ByteBuffer;

public abstract class Field {

	public void visit(ByteBuffer bytes, FieldVisitor visitor) {
		visitor.visit(this);
	}

}
