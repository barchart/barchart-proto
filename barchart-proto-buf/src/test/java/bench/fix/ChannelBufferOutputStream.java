package bench.fix;

import java.io.IOException;
import java.io.OutputStream;

import org.jboss.netty.buffer.ChannelBuffer;

public class ChannelBufferOutputStream extends OutputStream {

	private ChannelBuffer buffer;

	public ChannelBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(final ChannelBuffer buffer) {
		this.buffer = buffer;
	}

	/* read unsigned byte; see ByteArrayInputStream.read() */
	// @Override
	public int read() throws IOException {
		if (buffer.readable()) {
			return buffer.readByte() & 0xFF;
		} else {
			return -1;
		}
	}

	// @Override
	public int read(final byte array[]) throws IOException {
		return read(array, 0, array.length);
	}

	// @Override
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

	@Override
	public void write(final int b) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(final byte b[]) throws IOException {
		write(b, 0, b.length);
	}

	@Override
	public void write(final byte b[], final int off, final int len)
			throws IOException {

	}

}
