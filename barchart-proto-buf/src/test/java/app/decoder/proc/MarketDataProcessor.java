package app.decoder.proc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.decoder.cons.DataConsumer;
import app.decoder.cons.ProtoEntry;
import app.decoder.data.EntryContext;
import app.decoder.data.Util;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;

public class MarketDataProcessor {

	private static final Logger log = LoggerFactory
			.getLogger(MarketDataProcessor.class);

	public void apply(final MarketData message, final DataConsumer consumer) {

		log.debug("apply message \n{}", message);

		for (final MarketDataEntry entry : message.getEntryList()) {

			final boolean hasMarketId = Util.hasMarketId(message, entry);

			if (!hasMarketId) {
				log.error("no marketId \n{}", entry);
				continue;
			}

			if (!entry.hasType()) {
				log.error("no type \n{}", entry);
				continue;
			}

			final long marketId = Util.getMarketId(message, entry);

			final MarketInstrument instrument = consumer.lookup(marketId);

			if (instrument.isNull()) {
				log.error("missing insrument \n{}", entry);
				continue;
			}

			if (!consumer.isRegistered(instrument)) {
				log.error("insrument not registered \n{}", entry);
				continue;
			}

			final EntryContext context = new EntryContext(message, entry);

			final ProtoEntry protoEntry = new ProtoEntry(context);

			consumer.make(protoEntry);

		}

	}

}
