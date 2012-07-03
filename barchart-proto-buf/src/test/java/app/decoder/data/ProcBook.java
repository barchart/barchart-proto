/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.feed.base.book.api.MarketDoBookEntry;
import com.barchart.feed.base.market.api.MarketDo;
import com.barchart.proto.buf.MarketDataEntry.Descriptor;
import com.barchart.util.values.api.TimeValue;

public class ProcBook {

	private static final Logger log = LoggerFactory.getLogger(ProcBook.class);

	public void apply(final EntryContext context, final MarketDo market) {

		log.debug("apply book");

		final boolean isValid = context.hasPrice() && context.hasSize()
				&& context.getEntry().hasIndex();

		if (!isValid) {
			log.error("wrong bid/ask : {}", context.getEntry());
			return;
		}

		@SuppressWarnings("unused")
		final boolean isBookImplied = context
				.hasDescriptor(Descriptor.BOOK_IMPLIED);

		switch (context.getAction()) {

		case ADD:

			final MarketDoBookEntry bookEntry = null;

			final TimeValue time = context.getTimeStamp();

			market.setBookUpdate(bookEntry, time);

			break;

		case EDIT:

			break;

		case REMOVE:

			break;

		}

	}

}
