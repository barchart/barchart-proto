package com.barchart.translator.ice.jform.prototypes;

import java.util.List;

import com.barchart.translator.common.jform.Field;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public abstract class Prototype {

	
	private final ImmutableList<Field> fields;

	public Prototype() {
		Builder<Field> builder = ImmutableList.builder();
		populateFields(builder);
		this.fields = builder.build();
	}
	
	
	public abstract void populateFields(ImmutableCollection.Builder<Field> fields);
	
	protected List<Field> getFields() {
		return fields;
	}
	
}
