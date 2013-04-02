/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.process;

import app.decoder.consumer.MessageTarget;
import app.decoder.data.DataMaker;
import app.decoder.news.NewsMaker;

import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketNews;
import com.barchart.proto.buf.MessageVisitor;

/** visitor forwarder */
public class MarketProcessor extends MessageVisitor.Adaptor<MessageTarget> {

	private final MarketDataProcessor procData = new MarketDataProcessor();

	private final MarketNewsProcessor procNews = new MarketNewsProcessor();

	@Override
	public void apply(final MarketData message, final MessageTarget target) {

		final DataMaker consumer = target.getDataMaker();

		procData.apply(message, consumer);

	}

	@Override
	public void apply(final MarketNews message, final MessageTarget target) {

		final NewsMaker consumer = target.getNewsMaker();

		procNews.apply(message, consumer);

	}

}
