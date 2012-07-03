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

import com.barchart.feed.base.market.api.MarketDo;

public class ProcEntry {

	private static final Logger log = LoggerFactory.getLogger(ProcEntry.class);

	private final ProcBook procBook = new ProcBook();

	private final ProcTrade procTrade = new ProcTrade();

	public void apply(final EntryContext context, final MarketDo market) {

		log.debug("apply entry");

		switch (context.getType()) {

		case TRADE:
			procTrade.apply(context, market);
			break;

		case BID:
		case ASK:
			procBook.apply(context, market);
			break;

		default:

		}

	}

}
