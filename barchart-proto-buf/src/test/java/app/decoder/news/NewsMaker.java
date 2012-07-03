/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
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
