package com.barchart.translator.common.jform;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public abstract class FieldBlock<T> {

	protected final ImmutableList<T> fields;

	public FieldBlock() {
		Builder<T> builder = ImmutableList.builder();
		populateFields(builder);
		this.fields = builder.build();
	}
	
	
	public abstract void populateFields(ImmutableCollection.Builder<T> fields);

	
}
