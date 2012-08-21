package com.barchart.translator.common.lookup;

import com.google.common.collect.ImmutableMap;

public abstract class ValueLookup<K, V> {

	private final ImmutableMap<K, V> map; 
	
	public ValueLookup() {
		ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
		init(builder);
		map = builder.build();
	}
	
	public final V lookup(K key) {
		if (key == null) {
			return null;
		} else {
			V value = map.get(key);
			if (value == null) {
				throw new IllegalArgumentException("Unknown key: " + key + ".  Known keys: " + map.keySet().toString());
			}
			return value;
		}
	}
	
	public abstract void init(ImmutableMap.Builder<K, V> builder);
	

	
}
