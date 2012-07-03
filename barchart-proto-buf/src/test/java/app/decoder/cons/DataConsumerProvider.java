/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.cons;

import app.decoder.data.EntryContext;
import app.decoder.data.ProcEntry;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.feed.base.market.api.MarketDo;
import com.barchart.feed.base.market.api.MarketFactory;
import com.barchart.feed.base.market.provider.MakerBase;

/** market maker */
public class DataConsumerProvider extends MakerBase<ProtoEntry> implements
		DataConsumer {

	private final static MarketFactory factory = new MarketFactory() {
		@Override
		public MarketDo newMarket() {
			return new VarMarketBUF();
		}
	};

	public DataConsumerProvider() {
		super(factory);
	}

	protected DataConsumerProvider(final MarketFactory factory) {
		super(factory);
	}

	private final ProcEntry procEntry = new ProcEntry();

	@Override
	protected void make(final ProtoEntry wrapper, final MarketDo market) {

		final EntryContext context = wrapper.getEntryContext();

		procEntry.apply(context, market);

	}

	@Override
	public MarketInstrument lookup(final long marketId) {
		// TODO Auto-generated method stub
		return null;
	}

}
