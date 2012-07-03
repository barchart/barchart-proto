/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.fix_openfast;

import java.io.IOException;
import java.io.InputStream;

import org.jboss.netty.buffer.ChannelBuffer;

public class ChannelBufferInputStream extends InputStream {

	private ChannelBuffer buffer;

	public ChannelBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(final ChannelBuffer buffer) {
		this.buffer = buffer;
	}

	/* read unsigned byte; see ByteArrayInputStream.read() */
	@Override
	public int read() throws IOException {
		if (buffer.readable()) {
			return buffer.readByte() & 0xFF;
		} else {
			return -1;
		}
	}

	@Override
	public int read(final byte array[]) throws IOException {
		return read(array, 0, array.length);
	}

	@Override
	public int read(final byte array[], final int off, final int len)
			throws IOException {
		if (buffer.readable()) {
			final int size = Math.min(buffer.readableBytes(), len);
			buffer.readBytes(array, off, size);
			return size;
		} else {
			return -1;
		}

	}

}
