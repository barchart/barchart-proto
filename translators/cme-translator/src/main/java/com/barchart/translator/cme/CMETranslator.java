package com.barchart.translator.cme;

import java.nio.ByteBuffer;

import com.barchart.proto.buf.data.MarketEntry;
import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.translator.cme.messaging.CMEMessage;
import com.barchart.translator.cme.messaging.CMEMessageDecoder;
import com.barchart.translator.cme.symbols.CMEGUIDLookup;
import com.barchart.translator.cme.translation.MarketEntryTranslation;
import com.barchart.translator.cme.translation.MarketMessageTranslation;
import com.barchart.translator.cme.translation.PacketTranslation;
import com.barchart.translator.common.Translator;
import com.barchart.translator.common.fix.FIXMessage;

public class CMETranslator implements Translator {
	
	private final int channelID;
	private final CMEGUIDLookup guidLookup;
	private final CMEMessageDecoder decoder;
	
	public CMETranslator(CMEGUIDLookup guidLookup, CMEMessageDecoder decoder, int channelID) {
		this.decoder = decoder;
		this.guidLookup = guidLookup;
		this.channelID = channelID;
	}

	@Override
	public MarketPacket  translate(ByteBuffer byteBuffer) {
		CMEMessage cmeMessage = decoder.decode(byteBuffer);
		MarketPacket  packet = translate(cmeMessage);
		return packet;
	}
	
	private MarketPacket translate(CMEMessage cmeMessage) {
		MarketPacket.Builder packetBuilder = makePacketBuilder(cmeMessage.getHeader());
		MarketMessage.Builder messageBuilder = makeMessageBuilder(cmeMessage.getBody());
		for (FIXMessage group : cmeMessage.getRepeatingGroups()) {
			MarketEntry.Builder entryBuilder = makeEntryBuilder(group);
			messageBuilder.addEntry(entryBuilder);
		}
		packetBuilder.addMessage(messageBuilder);
		return packetBuilder.build();
	}
	

	private MarketPacket.Builder makePacketBuilder(FIXMessage header) {
		PacketTranslation packetTranslation = translatePacket(header);
		MarketPacket.Builder builder = packetTranslation.getBuilder();
		builder.setChannel(channelID);
		return builder; 
	}

	private MarketMessage.Builder makeMessageBuilder(FIXMessage body) {
		MarketMessageTranslation marketMessageTranslation = translateMarketMessage(body);
		MarketMessage.Builder builder = marketMessageTranslation.getBuilder();
		Long marketID = makeMarketID(body);
		if (marketID != null) {
			builder.setMarketId(marketID);
		}
		return builder;
	}
	
	private MarketEntry.Builder makeEntryBuilder(FIXMessage group) {
		MarketEntryTranslation marketEntryTranslation = translateMarketEntry(group);
		MarketEntry.Builder builder = marketEntryTranslation.getBuilder();
		Long marketID = makeMarketID(group);
		if (marketID != null) {
			builder.setMarketId(marketID);
		}
		return builder;
	}
	
	private PacketTranslation translatePacket(FIXMessage header) {
		PacketTranslation translation = new PacketTranslation();
		translation.translateSequence(header.getMsgSeqNum());
		translation.translateSendingTime(header.getSendingTime());
		return translation;
	}
	
	private MarketMessageTranslation translateMarketMessage(FIXMessage body) {
		MarketMessageTranslation translation = new MarketMessageTranslation();
		translation.translateMessageType(body.getMsgType());
		translation.translateLastMsgSeqNumProcessed(body.getLastMsgSeqNumProcessed());
		translation.translateTradeDate(body.getTradeDate());
		return translation;
	}
	
	private MarketEntryTranslation translateMarketEntry(FIXMessage group) {
		MarketEntryTranslation translation = new MarketEntryTranslation();
		translation.translateEntrySize(group.getMDEntrySize());
		translation.translateEntryType(group.getMDEntryType());
		translation.translatePrice(group.getMDEntryPx());
		translation.translatePriceLevel(group.getMDPriceLevel());
		translation.translateUpdateAction(group.getMDUpdateAction());
		return translation;
	}
	
	private Long makeMarketID(FIXMessage fixMessage) {
		String securityID = fixMessage.getSecurityID();
		if (securityID == null) {
			return null;
		} else {
			return guidLookup.marketLookup(securityID);
		}
	}


}
