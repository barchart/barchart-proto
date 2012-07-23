package com.barchart.proto.buf.inst;

public interface InstrumentVisitor<T> {

	void apply(Forex inst, T target);

}
