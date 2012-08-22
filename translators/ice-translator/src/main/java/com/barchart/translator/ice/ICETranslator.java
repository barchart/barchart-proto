package com.barchart.translator.ice;

import java.nio.ByteBuffer;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.xform.ice.ICE;
import com.barchart.proto.xform.ice.ICE.ICEPacket;
import com.barchart.translator.common.Translator;
import com.barchart.translator.common.exception.TranslatorException;

public class ICETranslator implements Translator {

	@Override
	public MarketPacket translate(ByteBuffer byteBuffer) {
		try {
			ICEPacket iceSource = ICE.ICEPacket.from(byteBuffer, null);
			MarketPacket.Builder packetBuilder = MarketPacket.newBuilder();
			iceSource.into(packetBuilder);
			MarketPacket packet = packetBuilder.build();
			return packet;
		} catch (Exception e) {
			throw new TranslatorException(e);
		}

	}

}