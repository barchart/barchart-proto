package com.barchart.translator.common.fields;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public final class EnumField<T extends Enum<?>> {

	private final Function<byte[], T> byteArrayToEnumFunction;
	
	public EnumField(Function<byte[], T> f) {
		this.byteArrayToEnumFunction = f;
	}

	public T get(byte[] array) {
		return byteArrayToEnumFunction.apply(array);
	}
//
//	public static <T extends Enum<?> & Keyable<String>> EnumField<T> from(Class<T> clazz, final StringField field) {
//		T[] enumConstants = clazz.getEnumConstants();
//		final ImmutableMap<String,T> lookupMap = buildMapFromValues(enumConstants);
//		Function<byte[], T> f = new Function<byte[], T>() {
//			@Override
//			public T apply(byte[] array) {
//				String string = field.get(array);
//				return lookupMap.get(string);
//			}
//		};
//		return new EnumField<T>(f);
//	}
	
	

	public static <T extends Enum<?> & Keyable<Object>> EnumField<T> from(Class<T> clazz, final Field field) {
		T[] enumConstants = clazz.getEnumConstants();
		
		final ImmutableMap<Object,T> lookupMap = buildMapFromValues(enumConstants);
		
		Function<byte[], T> f = new Function<byte[], T>() {
			@Override
			public T apply(byte[] array) {
				Object value = field.getAsObject(array);
				return lookupMap.get(value);
			}
		};
		return new EnumField<T>(f);
	}
	
	
//	public static <T extends Enum<?> & Keyable<Object>> EnumField<T> from(Class<T> clazz, final IntField field) {
//		T[] enumConstants = clazz.getEnumConstants();
//		final ImmutableMap<Object, T> lookupMap = buildMapFromValues(enumConstants);
//		Function<byte[], T> f = new Function<byte[], T>() {
//			@Override
//			public T apply(byte[] array) {
//				int value = field.get(array);
//				return lookupMap.get(value);
//			}
//		};
//		return new EnumField<T>(f);		
//	}
	
	private static <K, V extends Enum<?> & Keyable<K>>  ImmutableMap<K, V> buildMapFromValues(V[] values) {
		Builder<K, V> builder = ImmutableMap.builder();
		for (V value : values) {
			builder.put(value.getKey(), value);
		}
		return builder.build();
	}

}
