/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data.make;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import app.decoder.data.wrap.WrapEntry;

import com.barchart.feed.base.book.api.MarketDoBookEntry;
import com.barchart.feed.base.market.api.MarketDo;
import com.barchart.proto.buf.MarketDataEntry.Descriptor;
import com.barchart.util.values.api.TimeValue;

public class MakeBook {

	private static final Logger log = LoggerFactory.getLogger(MakeBook.class);

	public void apply(final WrapEntry entry, final MarketDo market) {

		log.debug("apply book");

		final boolean isValid = entry.hasPrice() && entry.hasSize();

		if (!isValid) {
			log.error("wrong bid/ask : {}", entry);
			return;
		}

		@SuppressWarnings("unused")
		final boolean isBookImplied = entry
				.hasDescriptor(Descriptor.BOOK_IMPLIED);

		switch (entry.getAction()) {

		case ADD:

			final MarketDoBookEntry bookEntry = null;

			final TimeValue time = entry.getTimeStamp();

			market.setBookUpdate(bookEntry, time);

			break;

		case EDIT:

			break;

		case REMOVE:

			break;

		}

	}

}
