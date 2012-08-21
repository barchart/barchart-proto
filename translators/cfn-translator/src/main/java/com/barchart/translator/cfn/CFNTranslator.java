package com.barchart.translator.cfn;

import java.nio.ByteBuffer;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.xform.cfn.CFN;
import com.barchart.proto.xform.cfn.CFN.BodyFutureBookTop;
import com.barchart.proto.xform.cfn.CFN.BodyFutureEndOfDay;
import com.barchart.proto.xform.cfn.CFN.BodyFutureInterest;
import com.barchart.proto.xform.cfn.CFN.BodyFutureSettlement;
import com.barchart.proto.xform.cfn.CFN.BodyFutureTrade;
import com.barchart.proto.xform.cfn.CFN.BodyOptionBookTop;
import com.barchart.proto.xform.cfn.CFN.BodyOptionTrade;
import com.barchart.proto.xform.cfn.CFN.BodyUnderlyingBookTop;
import com.barchart.proto.xform.cfn.CFN.BodyUnderlyingTrade;
import com.barchart.proto.xform.cfn.CFN.Packet;
import com.barchart.proto.xform.cfn.CodecCFN;
import com.barchart.proto.xform.cfn.ConverterCFN;
import com.barchart.proto.xform.cfn.ExampleCFN;
import com.barchart.translator.common.Translator;
import com.barchart.translator.common.exception.TranslatorException;

public class CFNTranslator implements Translator {

	static {
		CFN.bind(new CodecCFN());
		CFN.bind(new ConverterCFN());
	}
	
	@Override
	public MarketPacket translate(ByteBuffer byteBuffer) {
		try {
			Packet iceSource = CFN.Packet.from(byteBuffer, null);
//			MarketPacket.Builder packetBuilder = MarketPacket.newBuilder();
//			iceSource.into(packetBuilder);
//			MarketPacket packet = packetBuilder.build();
//			return packet;
			
			ExampleCFN.decode(byteBuffer, new CFN.Body.Visitor<String>() {

				@Override
				public void apply(BodyFutureTrade arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void apply(BodyOptionTrade arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void apply(BodyFutureBookTop arg0, String arg1) {
					
				}

				@Override
				public void apply(BodyOptionBookTop arg0, String arg1) {
					
				}

				@Override
				public void apply(BodyFutureInterest arg0, String arg1) {
					
				}

				@Override
				public void apply(BodyUnderlyingTrade arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void apply(BodyUnderlyingBookTop arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void apply(BodyFutureEndOfDay arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void apply(BodyFutureSettlement arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			return null;
		} catch (Exception e) {
			throw new TranslatorException(e);
		}

	}

}
