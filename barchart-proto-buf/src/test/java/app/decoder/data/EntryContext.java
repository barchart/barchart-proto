package app.decoder.data;

import java.util.Arrays;
import java.util.List;

import com.barchart.proto.buf.MarketData2;
import com.barchart.proto.buf.MarketDataEntry2;
import com.barchart.proto.buf.MarketDataEntry2.Action;
import com.barchart.proto.buf.MarketDataEntry2.Descriptor;
import com.barchart.proto.buf.MarketDataEntry2.Type;
import com.barchart.util.values.api.PriceValue;
import com.barchart.util.values.api.SizeValue;
import com.barchart.util.values.api.TimeValue;

/**
 * value object / type convertor
 */
public class EntryContext {

	private final MarketData2 message;
	private final MarketDataEntry2 entry;

	public EntryContext(final MarketData2 message, final MarketDataEntry2 entry) {

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

	public Type getType() {
		return entry.getType();
	}

	public boolean hasPrice() {
		return entry.hasPriceMantissa();
	}

	public boolean hasSize() {
		return entry.hasSizeMantissa();
	}

	public Action getAction() {
		return entry.getAction();
	}

	public MarketDataEntry2 getEntry() {
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
