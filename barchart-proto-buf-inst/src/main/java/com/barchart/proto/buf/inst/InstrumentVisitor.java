/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf.inst;

public interface InstrumentVisitor<T> {

	void apply(Forex inst, T target);

	void apply(Equity inst, T target);

	void apply(Index inst, T target);

	void apply(Future inst, T target);

	void apply(Option inst, T target);

	void apply(Spread inst, T target);

}
