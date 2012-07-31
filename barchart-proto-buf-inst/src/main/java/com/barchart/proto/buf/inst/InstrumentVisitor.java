package com.barchart.proto.buf.inst;

public interface InstrumentVisitor<T> {

	void apply(Forex inst, T target);

	void apply(Equity inst, T target);

	void apply(Index inst, T target);

	void apply(Future inst, T target);

	void apply(Option inst, T target);

	void apply(Spread inst, T target);

}
