package com.barchart.translator.common.jform;

import java.nio.ByteBuffer;

import com.barchart.translator.common.util.Keyable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public abstract class EnumField<T extends Enum<?> & Keyable> extends Field {

	
	private final ImmutableMap<Object, T> enumMap;

	public EnumField(Class<T> clazz) {
		T[] enumConstants = clazz.getEnumConstants();
		Builder<Object, T> builder = ImmutableMap.builder();
		for (T t : enumConstants) {
			builder.put(t.getKey(), t);
		}
		this.enumMap = builder.build();
	}
	
	public T getValue(ByteBuffer buffer) {
		Object key = readKey(buffer);
		return enumMap.get(key);
	}
	
	
	protected abstract Object readKey(ByteBuffer buffer);
	
	
}
