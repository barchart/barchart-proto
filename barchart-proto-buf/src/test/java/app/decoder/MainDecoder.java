package app.decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.decoder.cons.MessageTarget;
import app.decoder.proc.MarketProcessor;

import com.barchart.proto.buf.Base;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketDataEntry;
import com.barchart.proto.buf.MessageCodec;
import com.barchart.proto.buf.MessageVisitor;

public class MainDecoder {

	private static final Logger log = LoggerFactory
			.getLogger(MainDecoder.class);

	static final MessageVisitor<MessageTarget> visitor = new MarketProcessor();

	/**
	 * emulate decoder life cycle
	 */
	public static void main(final String... args) throws Exception {

		log.debug("init");

		// ######## encode

		final MarketDataEntry.Builder entryBuilder = MarketDataEntry
				.newBuilder();

		entryBuilder.setType(MarketDataEntry.Type.TRADE);
		entryBuilder.setMarketId(123);

		final MarketDataEntry dataEntry = entryBuilder.build();

		//

		final MarketData.Builder messageBuilder = MarketData.newBuilder();

		messageBuilder.addEntry(dataEntry);

		final MarketData message = messageBuilder.build();

		//

		final Base base = MessageCodec.encode(message);

		final byte[] array = MessageCodec.encode(base);

		/** send/receive array over the wire here */

		// ######## decode & apply to market

		final MessageTarget target = null; // XXX

		MessageCodec.decode(visitor, target, array);

		//

		log.debug("done");

	}

}
