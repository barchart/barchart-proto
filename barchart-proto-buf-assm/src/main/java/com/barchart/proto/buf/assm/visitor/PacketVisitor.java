package com.barchart.proto.buf.assm.visitor;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.buf.inst.InstrumentPacket;

public interface PacketVisitor {
	
	public void visit(InstrumentPacket instrumentPacket);
	
	public void visit(MarketPacket marketPacket);

}
