package com.barchart.proto.buf.assm;

import java.util.List;

import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.buf.inst.Instrument;

public interface PacketVisitor<T> {

	void apply(List<MarketMessage> messageList, T target);

	void apply(Instrument message, T target);

}
