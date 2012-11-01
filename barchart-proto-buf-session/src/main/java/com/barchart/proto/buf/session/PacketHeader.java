package com.barchart.proto.buf.session;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.WireFormat;

/**
 * fast {@link SessionPacket} header peek (100 nanos)
 * 
 * note: relies on {@link SessionPacket#TYPE_FIELD_NUMBER} as field search stop
 */
public final class PacketHeader {

	private PacketHeader() {
	}

	public static final int EOF = 0;

	//

	private int channel;

	private boolean hasChannel;

	//

	private long sequence;

	private boolean hasSequence;

	//

	private long timeStamp;

	private boolean hasTimeStamp;

	//

	@Override
	public String toString() {
		final StringBuilder text = new StringBuilder(128);
		if (hasChannel) {
			text.append(" channel=");
			text.append(channel);
		}
		if (hasSequence) {
			text.append(" sequence=");
			text.append(sequence);
		}
		if (hasTimeStamp) {
			text.append(" timeStamp=");
			text.append(timeStamp);
		}
		return text.toString();
	}

	//

	public static PacketHeader from(final byte[] array) throws Exception {

		final PacketHeader header = new PacketHeader();

		final CodedInputStream input = CodedInputStream.newInstance(array);

		while (true) {

			final int number = WireFormat.getTagFieldNumber(input.readTag());

			if (number == EOF || number >= SessionPacket.TYPE_FIELD_NUMBER) {
				return header;
			}

			if (number == SessionPacket.CHANNEL_FIELD_NUMBER) {
				header.channel = input.readSInt32();
				header.hasChannel = true;
			}

			if (number == SessionPacket.SEQUENCE_FIELD_NUMBER) {
				header.sequence = input.readSInt64();
				header.hasSequence = true;
			}

			if (number == SessionPacket.TIMESTAMP_FIELD_NUMBER) {
				header.timeStamp = input.readSInt64();
				header.hasTimeStamp = true;
			}

		}

	}

	public int getChannel() {
		return channel;
	}

	public boolean hasChannel() {
		return hasChannel;
	}

	public long getSequence() {
		return sequence;
	}

	public boolean hasSequence() {
		return hasSequence;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public boolean hasTimeStamp() {
		return hasTimeStamp;
	}

}
