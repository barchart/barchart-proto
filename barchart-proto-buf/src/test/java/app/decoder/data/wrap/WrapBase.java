/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data.wrap;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.feed.base.market.api.MarketMessage;
import com.barchart.proto.buf.MarketData;

public class WrapBase implements MarketMessage {

	protected final MarketInstrument instrument;

	private final MarketData message;

	public WrapBase(final MarketInstrument instrument, final MarketData message) {

		this.instrument = instrument;

		this.message = message;

	}

	@Override
	public MarketInstrument getInstrument() {
		return instrument;
	}

	public MarketData getMessage() {
		return message;
	}

}
