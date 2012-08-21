package com.barchart.translator.cme.fest;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.translator.cme.messaging.CMEMessage;
import com.barchart.translator.cme.messaging.CMEMessageDecoder;
import com.barchart.translator.common.fix.FIXMessage;
import com.cme.fest.framework.messaging.Message;
import com.cme.fest.framework.messaging.MessageBlock;
import com.cme.fest.framework.messaging.MessageGroup;
import com.cme.fest.framework.messaging.avl.AVLMessage;
import com.cme.fest.protocol.fast.DecoderStrategy;

public class FESTMessageDecoder implements CMEMessageDecoder {

	private static final Logger logger = LoggerFactory.getLogger(FESTMessageDecoder.class);

	/*
	 * The Preamble will provide the sequence number and sub-channel identifier
	 * for the related FAST message. The Preamble consists of 5 non-FAST encoded
	 * bytes that will be found before all FIX/FAST messages on all feeds
	 * (Incremental, Market Recovery, Instrument Definition, and TCP Replay).
	 * The Preamble will be found before the FAST encoded message, and will
	 * contain the sequence number and sub-channel identifier. Processing of the
	 * Preamble is optional and FAST messages will not be impacted by it.
	 */
	private static final int PREFIX_LENGTH = 5;

	private final DecoderStrategy decoderStrategy;

	public FESTMessageDecoder(DecoderStrategy decoderStrategy) {
		this.decoderStrategy = decoderStrategy;
	}

	@Override
	public CMEMessage decode(ByteBuffer byteBuffer) {
		Message message = decode(byteBuffer.array());
		FIXMessage headerAndBody = new FESTMessageFIXWrapper(message);
		List<FIXMessage> repeatingGroups = getRepeatingGroups(message);
		CMEMessage cmeMessage = new CMEMessage(headerAndBody, headerAndBody, repeatingGroups);
		return cmeMessage;
	}

	private List<FIXMessage> getRepeatingGroups(Message festMessage) {
		List<FIXMessage> list = new ArrayList<FIXMessage>();
		for (MessageGroup group : festMessage.getMessageGroups()) {
			for (MessageBlock block : group.getMessageBlocks()) {
				if (block != null) {
					FESTMessageFIXWrapper blockFixWrapper = new FESTMessageFIXWrapper(block);
					list.add(blockFixWrapper);
				}
			}
		}
		return list;
	}

	private Message decode(byte[] array) {
		Message message = new AVLMessage();
		decoderStrategy.decode(array, PREFIX_LENGTH, message);
		return message;
	}

}
