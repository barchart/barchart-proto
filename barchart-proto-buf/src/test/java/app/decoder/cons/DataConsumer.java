package app.decoder.cons;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.feed.base.market.api.MarketMakerProvider;

public interface DataConsumer extends MarketMakerProvider<ProtoEntry> {

	MarketInstrument lookup(long marketId);

}
