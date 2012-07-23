/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.decoder.data.DataMaker;
import app.decoder.data.wrap.WrapEntry;
import app.decoder.data.wrap.WrapMessage;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketData.Type;
import com.barchart.proto.buf.MarketDataEntry;

public class MarketDataProcessor {

	private static final Logger log = LoggerFactory
			.getLogger(MarketDataProcessor.class);

	public void apply(final MarketData message, final DataMaker consumer) {

		log.debug("apply message \n{}", message);

		if (!message.hasType()) {
			log.warn("missing type \n{}", message);
			return;
		}

		final Type type = message.getType();

		switch (type) {
		case SNAPSHOT:
			processSnapshot(message, consumer);
			break;
		case UPDATE:
			processUpdate(message, consumer);
			break;
		default:
			log.warn("unknown message type \n{}", message);
			break;
		}

	}

	private void processUpdate(final MarketData message,
			final DataMaker dataMaker) {

		for (final MarketDataEntry entry : message.getEntryList()) {

			if (!entry.hasMarketId()) {
				log.warn("no marketId \n{}", entry);
				continue;
			}

			if (!entry.hasType()) {
				log.warn("no type \n{}", entry);
				continue;
			}

			final long marketId = entry.getMarketId();

			final MarketInstrument instrument = dataMaker.lookup(marketId);

			if (instrument.isNull()) {
				log.warn("missing insrument \n{}", entry);
				continue;
			}

			if (!dataMaker.isRegistered(instrument)) {
				log.warn("insrument not registered \n{}", entry);
				continue;
			}

			final WrapEntry wrapEntry = new WrapEntry(//
					instrument, message, entry);

			dataMaker.make(wrapEntry);

		}

	}

	private void processSnapshot(final MarketData message,
			final DataMaker dataMaker) {

		if (!message.hasMarketId()) {
			log.warn("no marketId \n{}", message);
			return;
		}

		final long marketId = message.getMarketId();

		final MarketInstrument instrument = dataMaker.lookup(marketId);

		if (instrument.isNull()) {
			log.warn("missing insrument \n{}", message);
			return;
		}

		if (!dataMaker.isRegistered(instrument)) {
			log.warn("insrument not registered \n{}", message);
			return;
		}

		final WrapMessage wrapMessage = new WrapMessage(//
				instrument, message);

		dataMaker.make(wrapMessage);

	}

}
