package app.decoder.cons;

import app.decoder.data.EntryContext;

import com.barchart.feed.base.instrument.values.MarketInstrument;
import com.barchart.feed.base.market.api.MarketMessage;

/** merge of protobuf and barchart api */
public class ProtoEntry implements MarketMessage {

	private final EntryContext entryContex;

	public ProtoEntry(final EntryContext message) {
		this.entryContex = message;
	}

	@Override
	public MarketInstrument getInstrument() {
		// TODO Auto-generated method stub
		return null;
	}

	public EntryContext getEntryContext() {
		return entryContex;
	}

}
