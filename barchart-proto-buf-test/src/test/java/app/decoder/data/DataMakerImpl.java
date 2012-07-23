/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.data;

import java.util.List;

import app.decoder.data.make.MakeEntry;
import app.decoder.data.wrap.WrapBase;
import app.decoder.data.wrap.WrapEntry;
import app.decoder.data.wrap.WrapMessage;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.feed.base.market.api.MarketDo;
import com.barchart.feed.base.market.api.MarketFactory;
import com.barchart.feed.base.market.provider.MakerBase;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;

/** market maker */
public class DataMakerImpl extends MakerBase<WrapBase> implements
		DataMaker {

	private final static MarketFactory factory = new MarketFactory() {
		@Override
		public MarketDo newMarket() {
			return new VarMarketBUF();
		}
	};

	public DataMakerImpl() {
		super(factory);
	}

	protected DataMakerImpl(final MarketFactory factory) {
		super(factory);
	}

	private final MakeEntry procEntry = new MakeEntry();

	@Override
	protected void make(final WrapBase instance, final MarketDo market) {

		if (instance instanceof WrapEntry) {

			final WrapEntry wrapEntry = (WrapEntry) instance;

			procEntry.apply(wrapEntry, market);

			return;

		}

		if (instance instanceof WrapMessage) {

			final WrapMessage wrapMessage = (WrapMessage) instance;

			final MarketInstrument instrument = wrapMessage.getInstrument();

			final MarketData message = wrapMessage.getMessage();

			final List<MarketDataEntry> entryList = message.getEntryList();

			for (final MarketDataEntry entry : entryList) {

				final WrapEntry wrapEntry = new WrapEntry(//
						instrument, message, entry);

				procEntry.apply(wrapEntry, market);

			}

			return;
		}

		log.warn("wrong instance {}", instance);

	}

	@Override
	public MarketInstrument lookup(final long marketId) {
		// TODO Auto-generated method stub
		return null;
	}

}
