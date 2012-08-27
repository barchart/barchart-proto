package com.barchart.translator.nyl;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.xform.nyl.CodecNYL;
import com.barchart.proto.xform.nyl.ConverterNYL;
import com.barchart.proto.xform.nyl.NYL;
import com.barchart.proto.xform.nyl.NYL.Packet;
import com.barchart.translator.common.Translator;
import com.barchart.translator.common.exception.TranslatorException;

public class NYLTranslatorXForm implements Translator {

	private static final Logger logger = LoggerFactory.getLogger(NYLTranslatorXForm.class);

	private final int channelID;

	public NYLTranslatorXForm(int channelID) {
		this.channelID = channelID;
//		NYL.bind(new CodecNYL());
//		NYL.bind(new ConverterNYL());
	}

	@Override
	public MarketPacket translate(ByteBuffer byteBuffer) {
		try {
			Packet nylSource = NYL.Packet.from(byteBuffer, null);
			MarketPacket.Builder packetBuilder = MarketPacket.newBuilder();
			nylSource.into(packetBuilder);
			packetBuilder.setChannel(channelID);
			MarketPacket packet = packetBuilder.build();
			return packet;
		} catch (Exception e) {
			throw new TranslatorException("While parsing NYL Packet", e);
		}
	}

}
