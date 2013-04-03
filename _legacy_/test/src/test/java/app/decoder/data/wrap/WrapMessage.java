/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data.wrap;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.proto.buf.MarketData;

public class WrapMessage extends WrapBase {

	public WrapMessage(final MarketInstrument instrument,
			final MarketData message) {

		super(instrument, message);

	}

}
