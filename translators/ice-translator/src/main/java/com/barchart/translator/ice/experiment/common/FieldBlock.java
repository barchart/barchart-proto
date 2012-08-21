package com.barchart.translator.ice.experiment.common;

import java.nio.ByteBuffer;

import com.barchart.translator.ice.experiment.ICEVisitor;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public abstract class FieldBlock {

	private final ImmutableList<Field> fields;

	public FieldBlock() {
		Builder<Field> builder = ImmutableList.builder();
		populateFields(builder);
		this.fields = builder.build();
	}
	
	
	public abstract void populateFields(ImmutableCollection.Builder<Field> fields);
	
	public void visit(ByteBuffer bytes, ICEVisitor o) {
		for (Field field : fields) {
			field.visit(bytes, o);
		}
	}

	
}
