/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.DataSubscribeRequest;
import com.barchart.proto.buf.DataSubscribeResponse;
import com.barchart.proto.buf.HeartBeat;
import com.barchart.proto.buf.InstrumentRequest;
import com.barchart.proto.buf.InstrumentResponse;
import com.barchart.proto.buf.LoginRequest;
import com.barchart.proto.buf.LoginResponse;
import com.barchart.proto.buf.MarketData;
import com.barchart.proto.buf.MarketNews;
import com.barchart.proto.buf.MessageVisitor;
import com.barchart.proto.buf.NewsSubscribeRequest;
import com.barchart.proto.buf.NewsSubscribeResponse;
import com.google.protobuf.Message;

public class LoggingVisitor implements MessageVisitor<Void> {

	protected static final Logger log = LoggerFactory
			.getLogger(LoggingVisitor.class);

	protected void log(final Message message) {
		log.debug("message \n{}", message);
	}

	@Override
	public void apply(final MarketData message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final MarketNews message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final DataSubscribeRequest message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final DataSubscribeResponse message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final NewsSubscribeRequest message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final NewsSubscribeResponse message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final InstrumentRequest message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final InstrumentResponse message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final LoginRequest message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final LoginResponse message, final Void target) {
		log(message);
	}

	@Override
	public void apply(final HeartBeat message, final Void target) {
		log(message);
	}

}
