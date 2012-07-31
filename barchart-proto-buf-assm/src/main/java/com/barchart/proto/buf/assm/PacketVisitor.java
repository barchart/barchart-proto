package com.barchart.proto.buf.assm;

import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.buf.inst.Instrument;

public interface PacketVisitor<T> {

	void apply(MarketMessage message, T target);

	void apply(Instrument message, T target);

}
