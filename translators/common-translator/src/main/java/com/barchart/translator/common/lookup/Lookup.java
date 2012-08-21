package com.barchart.translator.common.lookup;

public interface Lookup<K, V> {

	public V lookup(K key);
}
