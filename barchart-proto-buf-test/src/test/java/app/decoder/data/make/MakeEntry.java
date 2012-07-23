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

import com.barchart.feed.base.market.api.MarketDo;

public class MakeEntry {

	private static final Logger log = LoggerFactory.getLogger(MakeEntry.class);

	private final MakeBook makeBook = new MakeBook();

	private final MakeTrade makeTrade = new MakeTrade();

	public void apply(final WrapEntry entry, final MarketDo market) {

		log.debug("apply entry");

		switch (entry.getType()) {

		case TRADE:
			makeTrade.apply(entry, market);
			break;

		case BID:
		case ASK:
			makeBook.apply(entry, market);
			break;

		default:

		}

	}

}
