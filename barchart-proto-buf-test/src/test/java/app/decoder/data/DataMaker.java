/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data;

import app.decoder.data.wrap.WrapBase;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.feed.base.market.api.MarketMakerProvider;

public interface DataMaker extends MarketMakerProvider<WrapBase> {

	MarketInstrument lookup(long marketId);

}
