/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data;

import java.util.Arrays;
import java.util.List;

import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;
import com.barchart.proto.buf.MessageRules;
import com.barchart.proto.buf.MarketDataEntry.Descriptor;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;


/**
 * value object / type convertor
 */
public class EntryContext {

	private final MarketData message;
	private final MarketDataEntry entry;

	public EntryContext(final MarketData message, final MarketDataEntry entry) {

		this.message = message;
		this.entry = entry;

	}

	// ///////////////////////////////
	// helper functions
	// ///////////////////////////////

	public boolean hasDescriptor(final Descriptor descriptor) {

		final List<Descriptor> list = entry.getDescriptorList();

		if (list.isEmpty()) {
			return false;
		}

		return list.contains(descriptor);

	}

	public boolean hasDescriptorAll(final Descriptor... descriptor) {

		final List<Descriptor> list = entry.getDescriptorList();

		if (list.isEmpty()) {
			return false;
		}

		final List<Descriptor> searchList = Arrays.asList(descriptor);

		return list.containsAll(searchList);

	}

	public MarketDataEntry.Type getType() {
		return entry.getType();
	}

	public boolean hasPrice() {
		return entry.hasPriceMantissa();
	}

	public boolean hasSize() {
		return entry.hasSizeMantissa();
	}

	public MarketDataEntry.Action getAction() {
		return entry.getAction();
	}

	public MarketDataEntry getEntry() {
		return entry;
	}

	// ///////////////////////////////
	// nesting agreement functions
	// ///////////////////////////////

	public long getMarketId() {
		return MessageRules.getMarketId(message, entry);
	}

	public PriceValue getPrice() {
		return null; // MessageRules.getPrice(message, entry);
	}

	public SizeValue getSize() {
		return null;// MessageRules.getSize(message, entry);
	}

	public TimeValue getTimeStamp() {
		return null; // MessageRules.getTimeStamp(message, entry);
	}

	public TimeValue getTradeDate() {
		return null; // MessageRules.getTradeDate(message, entry);
	}

	public long getSequence() {
		return MessageRules.getSequence(message, entry);
	}

}
