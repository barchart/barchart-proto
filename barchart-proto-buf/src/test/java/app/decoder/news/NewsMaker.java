package app.decoder.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.decoder.cons.MessageTarget;

import com.barchart.proto.buf.MarketNews;

public class NewsMaker {

	private static final Logger log = LoggerFactory
			.getLogger(NewsMaker.class);

	public void apply(final MarketNews message, final MessageTarget target) {

		log.debug("apply : {}", message);

	}

}
