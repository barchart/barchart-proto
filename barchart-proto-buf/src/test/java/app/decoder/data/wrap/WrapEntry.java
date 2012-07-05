/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data.wrap;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;
import com.barchart.proto.buf.MarketDataEntry.Descriptor;
import com.barchart.proto.buf.MessageRules;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;
import com.barchart.util.values.provider.ValueBuilder;
import com.barchart.util.values.provider.ValueConst;

public class WrapEntry extends WrapBase {

	protected final MarketDataEntry entry;

	public WrapEntry(final MarketInstrument instrument,
			final MarketData message, final MarketDataEntry entry) {

		super(instrument, message);

		this.entry = entry;

	}

	//

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

	public PriceValue getPrice() {
		if (hasPrice()) {
			final long mantissa = entry.getPriceMantissa();
			final int exponent = MessageRules.getPriceExponent(getMessage(), entry);
			return ValueBuilder.newPrice(mantissa, exponent);
		} else {
			return ValueConst.NULL_PRICE;
		}
	}

	/** XXX size exponent */
	public SizeValue getSize() {
		if (hasSize()) {
			final long mantissa = entry.getSizeMantissa();
			final int exponent = MessageRules.getSizeExponent(getMessage(), entry);
			return ValueBuilder.newSize(mantissa);
		} else {
			return ValueConst.NULL_SIZE;
		}
	}

	public TimeValue getTimeStamp() {
		if (MessageRules.hasTimeStamp(getMessage(), entry)) {
			return ValueBuilder.newTime(MessageRules.getTimeStamp(getMessage(),
					entry));
		} else {
			return ValueConst.NULL_TIME;
		}
	}

	public TimeValue getTradeDate() {
		if (MessageRules.hasTradeDate(getMessage(), entry)) {
			final int days = MessageRules.getTradeDate(getMessage(), entry);
			final MutableDateTime date = new MutableDateTime(0,
					DateTimeZone.UTC);
			date.addDays(days);
			return ValueBuilder.newTime(date.getMillis());
		} else {
			return ValueConst.NULL_TIME;
		}
	}

	public long getSequence() {
		return MessageRules.getSequence(getMessage(), entry);
	}

	public boolean hasTradeDate() {
		return MessageRules.hasTradeDate(getMessage(), entry);
	}

	public boolean hasTimeStamp() {
		return MessageRules.hasTimeStamp(getMessage(), entry);
	}

	public boolean hasSequence() {
		return MessageRules.hasSequence(getMessage(), entry);
	}

	public boolean hasAction() {
		return entry.hasAction();
	}

	public boolean hasType() {
		return entry.hasType();
	}

}
