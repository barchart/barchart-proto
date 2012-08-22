package com.barchart.translator.nyl.data;

import com.barchart.translator.nyl.data.enums.NYLPacketType;

public interface NYLPacketHeader {

	/**
	 * Length of the packet including the 16-byte packet header.
	 */
	public int getPacketLength();

	/**
	 * Identifier for the type of data contained in the packet.
	 * 
	 * ‘799’ – Generic Derivatives Message
	 */
	public NYLPacketType getPacketType();

	/**
	 * This field contains the packet sequence number. It is unique for each
	 * broadcast channel (multicast group) and is used for gap detection. It
	 * increases serially and monotonically and is reset to 1 at the beginning
	 * of each trading day.
	 */
	public long getPacketSeqNum();

	/**
	 * Time in milliseconds indicating the packet broadcast time. The number
	 * represents the number of milliseconds since the previous Sunday 00:00
	 * UTC.
	 */
	public long getSendTime();

	/**
	 * Numeric value identifying the service.
	 */
	public int getServiceID();

	/**
	 * Indicates delivery method.
	 */
	public int getDeliveryFlag();

	/**
	 * The number of messages that are contained within the packet.
	 */
	public int getNumberMsgEntries();

}
