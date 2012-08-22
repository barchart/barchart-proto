package com.barchart.translator.nyl.jform.facade;

public interface NYLPacketVisitor {

	
	public void visitPacketHeader(NYLPacketHeader header);
	
	public void visitMarketUpdate(MarketUpdate marketUpdate);
	
	public void visitMarketUpdateEntry(MarketUpdate.Entry entry);
	
}
