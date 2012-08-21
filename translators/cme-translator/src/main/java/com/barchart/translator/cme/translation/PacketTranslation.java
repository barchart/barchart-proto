package com.barchart.translator.cme.translation;

import com.barchart.proto.buf.data.MarketPacket;

public class PacketTranslation {

	private MarketPacket.Builder packetBuilder;

	public PacketTranslation() {
		this.packetBuilder = MarketPacket.newBuilder();
	}

	public void translateSequence(Long msgSequenceNumber) {
		if (msgSequenceNumber != null) {
			packetBuilder.setSequence(msgSequenceNumber);
		}
	}

	public MarketPacket build() {
		return packetBuilder.build();
	}

	public MarketPacket.Builder getBuilder() {
		return packetBuilder;
	}

	public void translateSendingTime(Long sendingTime) {
		if (sendingTime != null) {
			packetBuilder.setTimeStamp(sendingTime);
		}
	}

}
