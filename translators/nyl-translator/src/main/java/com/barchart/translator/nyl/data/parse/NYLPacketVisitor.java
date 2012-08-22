package com.barchart.translator.nyl.data.parse;

import com.barchart.translator.nyl.data.MarketUpdate;
import com.barchart.translator.nyl.data.PacketHeader;

public interface NYLPacketVisitor {

	
	public void visit(PacketHeader header);
	
	public void visit(MarketUpdate marketUpdate);
	
	public void visit(MarketUpdate.Entry entry);
	
}
