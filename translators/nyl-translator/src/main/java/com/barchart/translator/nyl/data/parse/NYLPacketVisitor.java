package com.barchart.translator.nyl.data.parse;

import com.barchart.translator.nyl.data.NYLMarketUpdate;
import com.barchart.translator.nyl.data.NYLPacketHeader;
import com.barchart.translator.nyl.data.NYLValueAddedParameters;

public interface NYLPacketVisitor {

	public void visit(NYLPacketHeader header);

	public void visit(NYLMarketUpdate marketUpdate, NYLMarketUpdate.Entry... entries);

	public void visit(NYLValueAddedParameters message, NYLValueAddedParameters.Entry... entries);

}
