package bench.fix;

import java.io.File;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DownstreamMessageEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.UpstreamMessageEvent;
import org.openfast.Message;
import org.openfast.SequenceValue;
import org.openfast.template.Field;
import org.openfast.template.MessageTemplate;
import org.openfast.template.Sequence;
import org.openfast.template.TemplateRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** measure message heap size */
public class MainCodecFIX {

	private static final Logger log = LoggerFactory
			.getLogger(MainCodecFIX.class);

	static final int COUNT_TEST = 100 * 1000;

	static final int COUNT_ENTRY = 20;

	static final long A_LONG = Long.MAX_VALUE - 3;
	static final int AN_INT = Integer.MAX_VALUE - 3;

	public static void main(final String... args) throws Exception {

		log.debug("init");

		//

		log.debug("setup");

		final File file = new File("src/test/resources/fixfast/templates.xml");

		final TemplateRegistry registry = RegistryFIX
				.makeTemplateRegistry(file);

		final MsgCodecFIX codec = new MsgCodecFIX(registry);

		final MockContex ctx = new MockContex();

		final MockChannel channel = new MockChannel();
		final ChannelFuture future = Channels.future(channel);

		ctx.channel = channel;

		//

		log.debug("encode");

		final MessageTemplate template = registry.get(1);

		final Message messageOut = new Message(template);
		messageOut.setInteger("MsgSeqNum", 1);
		messageOut.setLong("SendingTime", 1);

		final Field[] fields = new Field[] {};
		final Sequence seq = new Sequence("MDEntries", fields, false);
		final SequenceValue seqValue = new SequenceValue(seq);
		messageOut.setFieldValue("MDEntries", seqValue);

		final MessageEvent eventOut = new DownstreamMessageEvent(channel,
				future, messageOut, null);

		codec.writeRequested(ctx, eventOut);

		//

		log.debug("decode");

		final ChannelBuffer bufferIn = (ChannelBuffer) ctx.eventIn.getMessage();

		final MessageEvent eventIn = new UpstreamMessageEvent(channel,
				bufferIn, null);

		codec.messageReceived(ctx, eventIn);

		//

		log.debug("done");

	}

}
