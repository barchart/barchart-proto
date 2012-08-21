package com.barchart.translator.cfn;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger logger = LoggerFactory.getLogger(CFNTranslator.class);
	
	@Override
	public MarketPacket translate(ByteBuffer byteBuffer) {
			
//			byte b = byteBuffer.get();
//			assert(b==0x01);
//			byte c = byteBuffer.get();
//			System.out.println(Character.toString((char)c));
//			printByteBuffer(byteBuffer);
			try {
				Packet source = CFN.Packet.from(byteBuffer, null);
				MarketPacket.Builder packetBuilder = MarketPacket.newBuilder();
				source.into(packetBuilder);
				logger.info(source.toString());
			} catch (Exception e) {
				throw new TranslatorException(e);
			}
//			iceSource.into(packetBuilder);
//			MarketPacket packet = packetBuilder.build();
//			return packet;
			return null;

	}

	private void printByteBuffer(ByteBuffer byteBuffer) {
		String s = new String(byteBuffer.array());
		System.out.println(s);
		
	}

}
