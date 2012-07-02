package app.decoder.proc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.decoder.cons.NewsConsumer;

import com.barchart.proto.buf.MarketNews;

public class MarketNewsProcessor {

	private static final Logger log = LoggerFactory
			.getLogger(MarketNewsProcessor.class);

	public void apply(final MarketNews message, final NewsConsumer consumer) {

		log.debug("apply : {}", message);

	}

}
