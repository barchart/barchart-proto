package com.barchart.translator.nyl.jform.facade;

public class MessageDescriptorImpl extends OffsetByteReader {

	protected MessageDescriptorImpl(int baseOffset, ByteFacade bytes) {
		super(baseOffset, bytes);
	}
	
	public int getMsgSize() {
		return bytes.unsignedShort(offset(0));
	}
	

	
	
	
}
