/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.fix_openfast;

import java.net.SocketAddress;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DownstreamMessageEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.UpstreamMessageEvent;
import org.openfast.Message;
import org.openfast.MessageInputStream;
import org.openfast.MessageOutputStream;
import org.openfast.template.TemplateRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MsgCodecFIX extends SimpleChannelHandler {

	private static final Logger log = LoggerFactory
			.getLogger(MsgCodecFIX.class);

	private final ChannelBufferInputStream bufferInput;
	private final MessageInputStream messageInput;

	private final ChannelBufferOutputStream bufferOutput;
	private final MessageOutputStream messageOutput;

	public MsgCodecFIX(final TemplateRegistry templateRegistry) {

		this.bufferInput = new ChannelBufferInputStream();
		this.messageInput = new MessageInputStream(bufferInput);
		this.messageInput.setTemplateRegistry(templateRegistry);

		//

		this.bufferOutput = new ChannelBufferOutputStream();
		this.messageOutput = new MessageOutputStream(bufferOutput);
		this.messageOutput.setTemplateRegistry(templateRegistry);

	}

	@Override
	public synchronized void messageReceived(final ChannelHandlerContext ctx,
			final MessageEvent eventIn) throws Exception {

		final Object instance = eventIn.getMessage();

		if (!(instance instanceof ChannelBuffer)) {
			ctx.sendUpstream(eventIn);
			return;
		}

		final ChannelBuffer buffer = (ChannelBuffer) instance;

		bufferInput.setBuffer(buffer);

		while (true) {

			if (!buffer.readable()) {
				// end of packet
				break;
			}

			messageInput.reset();

			Message fixMessage = null;
			try {
				fixMessage = messageInput.readMessage();
			} catch (final Throwable e) {
				log.error("### bada-boom", e);
				break;
			}

			if (fixMessage == null) {
				// end of packet
				break;
			}

			// deliver message

			final MessageEvent eventNew = new UpstreamMessageEvent(
					eventIn.getChannel(), fixMessage, null);

			ctx.sendUpstream(eventNew);

		}

	}

	@Override
	public synchronized void writeRequested(final ChannelHandlerContext ctx,
			final MessageEvent eventOut) throws Exception {

		final Object instance = eventOut.getMessage();

		if (!(instance instanceof Message)) {
			ctx.sendDownstream(eventOut);
			return;
		}

		final Message fixMessage = (Message) instance;

		final ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

		bufferOutput.setBuffer(buffer);

		messageOutput.reset();

		messageOutput.writeMessage(fixMessage);

		// deliver buffer

		final Channel channel = ctx.getChannel();
		final ChannelFuture future = Channels.future(channel);
		final SocketAddress address = channel.getRemoteAddress();

		final MessageEvent eventNew = new DownstreamMessageEvent(channel,
				future, buffer, address);

		ctx.sendDownstream(eventNew);

	}

}
