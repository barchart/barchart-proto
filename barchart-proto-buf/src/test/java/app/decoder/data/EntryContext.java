package app.decoder.data;

import java.util.Arrays;
import java.util.List;

import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;
import com.barchart.proto.buf.MarketDataEntry.Descriptor;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;

/**
 * value object / type convertor
 */
public class EntryContext {

	private final MarketData message;
	private final MarketDataEntry entry;

	public EntryContext(final MarketData message, final MarketDataEntry entry) {

		this.message = message;
		this.entry = entry;

	}

	// ///////////////////////////////
	// helper functions
	// ///////////////////////////////

	public boolean hasDescriptor(final Descriptor descriptor) {

		final List<Descriptor> list = entry.getDescriptorList();

		if (list.isEmpty()) {
			return false;
		}

		return list.contains(descriptor);

	}

	public boolean hasDescriptorAll(final Descriptor... descriptor) {

		final List<Descriptor> list = entry.getDescriptorList();

		if (list.isEmpty()) {
			return false;
		}

		final List<Descriptor> searchList = Arrays.asList(descriptor);

		return list.containsAll(searchList);

	}

	public MarketDataEntry.Type getType() {
		return entry.getType();
	}

	public boolean hasPrice() {
		return entry.hasPriceMantissa();
	}

	public boolean hasSize() {
		return entry.hasSizeMantissa();
	}

	public MarketDataEntry.Action getAction() {
		return entry.getAction();
	}

	public MarketDataEntry getEntry() {
		return entry;
	}

	// ///////////////////////////////
	// nesting agreement functions
	// ///////////////////////////////

	public long getMarketId() {
		return Util.getMarketId(message, entry);
	}

	public PriceValue getPrice() {
		return Util.getPrice(message, entry);
	}

	public SizeValue getSize() {
		return Util.getSize(message, entry);
	}

	public TimeValue getTimeStamp() {
		return Util.getTimeStamp(message, entry);
	}

	public TimeValue getTradeDate() {
		return Util.getTradeDate(message, entry);
	}

	public long getSequence() {
		return Util.getSequence(message, entry);
	}

}