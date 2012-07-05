/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data;

import com.barchart.feed.base.bar.api.MarketDoBar;
import com.barchart.feed.base.bar.enums.MarketBarType;
import com.barchart.feed.base.book.api.MarketDoBookEntry;
import com.barchart.feed.base.cuvol.api.MarketDoCuvolEntry;
import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.feed.base.market.provider.VarMarket;
import com.barchart.feed.base.state.enums.MarketStateEntry;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;

public class VarMarketBUF extends VarMarket {

	@Override
	public void setInstrument(final MarketInstrument symbol) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBookUpdate(final MarketDoBookEntry entry,
			final TimeValue time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBookSnapshot(final MarketDoBookEntry[] entries,
			final TimeValue time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCuvolUpdate(final MarketDoCuvolEntry entry,
			final TimeValue time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCuvolSnapshot(final MarketDoCuvolEntry[] entries,
			final TimeValue time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBar(final MarketBarType type, final MarketDoBar bar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTrade(final MarketBarType type, final PriceValue price,
			final SizeValue size, final TimeValue time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setState(final MarketStateEntry entry, final boolean isOn) {
		// TODO Auto-generated method stub

	}

}
