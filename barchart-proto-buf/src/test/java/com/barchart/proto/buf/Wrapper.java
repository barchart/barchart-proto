package com.barchart.proto.buf;

public class Wrapper<T> {

	private T value;

	public void set(final T value) {
		this.value = value;
	}

	public T get() {
		return value;
	}

}
