/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
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
