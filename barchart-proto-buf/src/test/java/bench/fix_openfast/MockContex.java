package bench.fix_openfast;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockContex implements ChannelHandlerContext {

	private static final Logger log = LoggerFactory.getLogger(MockContex.class);

	public Channel channel;

	public MessageEvent eventIn;
	public MessageEvent eventOut;

	@Override
	public Channel getChannel() {
		return channel;
	}

	@Override
	public ChannelPipeline getPipeline() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canHandleUpstream() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canHandleDownstream() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendUpstream(final ChannelEvent e) {

		eventIn = (MessageEvent) e;

		log.debug("upst : {}", eventIn.getMessage());

	}

	@Override
	public void sendDownstream(final ChannelEvent e) {

		eventOut = (MessageEvent) e;

		log.debug("down : {}", eventOut.getMessage());

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
