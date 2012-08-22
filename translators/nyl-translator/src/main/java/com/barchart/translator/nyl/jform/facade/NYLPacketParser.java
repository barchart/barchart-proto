package com.barchart.translator.nyl.jform.facade;

import java.nio.ByteBuffer;

public class NYLPacketParser {

	
	public NYLPacketParser() {
		
	}
	
	public void parse(ByteBuffer buffer, NYLPacketVisitor visitor) {
		Packet packet = new Packet(new ByteFacade(buffer.array()), visitor);
		packet.accept(visitor);
	}

	

	
	

	
	
	
	
}
