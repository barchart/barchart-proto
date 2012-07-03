/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.proc;

import app.decoder.cons.DataConsumer;
import app.decoder.cons.MessageTarget;
import app.decoder.cons.NewsConsumer;

import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketNews;
import com.barchart.proto.buf.MessageVisitor;

/** visitor forwarder */
public class MarketProcessor extends MessageVisitor.Adaptor<MessageTarget> {

	private final MarketDataProcessor procData = new MarketDataProcessor();

	private final MarketNewsProcessor procNews = new MarketNewsProcessor();

	@Override
	public void apply(final MarketData message, final MessageTarget target) {

		final DataConsumer consumer = target.getDataConsumer();

		procData.apply(message, consumer);

	}

	@Override
	public void apply(final MarketNews message, final MessageTarget target) {

		final NewsConsumer consumer = target.getNewsConsumer();

		procNews.apply(message, consumer);

	}

}
