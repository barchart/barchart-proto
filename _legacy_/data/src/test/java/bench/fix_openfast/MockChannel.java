/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.fix_openfast;

import java.net.SocketAddress;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelConfig;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;

public class MockChannel implements Channel {

	@Override
	public int compareTo(final Channel o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Channel getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelConfig getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelPipeline getPipeline() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBound() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SocketAddress getLocalAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocketAddress getRemoteAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture write(final Object message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture write(final Object message,
			final SocketAddress remoteAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture bind(final SocketAddress localAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture connect(final SocketAddress remoteAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture disconnect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture unbind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture close() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture getCloseFuture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInterestOps() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isReadable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWritable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChannelFuture setInterestOps(final int interestOps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelFuture setReadable(final boolean readable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttachment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttachment(final Object attachment) {
		// TODO Auto-generated method stub

	}

}
