/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data.make;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import app.decoder.data.wrap.WrapEntry;

import com.barchart.feed.base.bar.enums.MarketBarType;
import com.barchart.feed.base.market.api.MarketDo;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;

public class MakeTrade {

	private static final Logger log = LoggerFactory.getLogger(MakeTrade.class);

	public void apply(final WrapEntry entry, final MarketDo market) {

		log.debug("apply trade");

		if (!entry.hasPrice() || !entry.hasSize()) {
			log.error("wrong trade : {}", entry);
			return;
		}

		final MarketBarType type = null;

		final PriceValue price = entry.getPrice();
		final SizeValue size = entry.getSize();
		final TimeValue time = entry.getTimeStamp();

		switch (entry.getAction()) {

		case ADD:

			market.setTrade(type, price, size, time);

			break;

		case EDIT:

			break;

		case REMOVE:

			break;

		}

	}

}
