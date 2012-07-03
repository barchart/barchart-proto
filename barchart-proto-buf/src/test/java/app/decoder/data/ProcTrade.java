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

import com.barchart.feed.base.bar.enums.MarketBarType;
import com.barchart.feed.base.market.api.MarketDo;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;

public class ProcTrade {

	private static final Logger log = LoggerFactory.getLogger(ProcTrade.class);

	public void apply(final EntryContext context, final MarketDo market) {

		log.debug("apply trade");

		if (!context.hasPrice() || !context.hasSize()) {
			log.error("wrong trade : {}", context.getEntry());
			return;
		}

		final MarketBarType type = null;

		final PriceValue price = context.getPrice();
		final SizeValue size = context.getSize();
		final TimeValue time = context.getTimeStamp();

		switch (context.getAction()) {

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
