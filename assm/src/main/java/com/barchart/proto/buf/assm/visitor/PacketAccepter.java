package com.barchart.proto.buf.assm.visitor;

import java.io.IOException;
import java.io.InputStream;

import com.barchart.proto.buf.assm.Packet;
import com.barchart.proto.buf.data.MarketMessage;
import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.buf.inst.InstrumentDefinition;
import com.barchart.proto.buf.inst.InstrumentPacket;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class PacketAccepter {

	public PacketAccepter() {
	}


	
	public void accept(Packet packet, PacketVisitor packetVisitor) throws InvalidProtocolBufferException {
		com.barchart.proto.buf.assm.PacketType type = packet.getType();
		switch (type) {
		case Instrument:
			InstrumentPacket.Builder instrumentPacketBuilder = InstrumentPacket.newBuilder();
			if (packet.hasChannel()) {
				instrumentPacketBuilder.setChannel(packet.getChannel());
			}
			if (packet.hasSequence()) {
				instrumentPacketBuilder.setSequence(packet.getSequence());
			}
			if (packet.hasTimeStamp()) {
				instrumentPacketBuilder.setTimeStamp(packet.getTimeStamp());
			}
			instrumentPacketBuilder.setType(com.barchart.proto.buf.inst.PacketType.INSTRUMENT);

			for (ByteString body : packet.getBodyList()) {
				InstrumentDefinition.Builder definitionBuilder = instrumentPacketBuilder.addDefinitionBuilder();
				definitionBuilder.mergeFrom(body);
			}
			InstrumentPacket instrumentPacket = instrumentPacketBuilder.build();
			packetVisitor.visit(instrumentPacket);
			break;
		case MarketData:
			MarketPacket.Builder marketPacketBuilder = MarketPacket.newBuilder();
			if (packet.hasChannel()) {
				marketPacketBuilder.setChannel(packet.getChannel());
			}
			if (packet.hasSequence()) {
				marketPacketBuilder.setSequence(packet.getSequence());
			}
			if (packet.hasTimeStamp()) {
				marketPacketBuilder.setTimeStamp(packet.getTimeStamp());
			}
			marketPacketBuilder.setType(com.barchart.proto.buf.data.PacketType.DATA);

			for (ByteString body : packet.getBodyList()) {
				MarketMessage.Builder messageBuilder = marketPacketBuilder.addMessageBuilder();
				messageBuilder.mergeFrom(body);
			}
			MarketPacket marketPacket = marketPacketBuilder.build();
			packetVisitor.visit(marketPacket);
			break;
		}

	}

	public void accept(InputStream input, PacketVisitor packetVisitor) throws IOException {
		Packet packet;
		while ((packet = Packet.parseDelimitedFrom(input)) != null) {
			accept(packet, packetVisitor);
		}
	}

	public interface InstrumentPacketHandler {
		public void handle(InstrumentPacket packet);
	}

}
