package bench.fix_openfast;

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

	@Override
	public void write(final int value) throws IOException {
		buffer.writeByte(value);
	}

	@Override
	public void write(final byte array[]) throws IOException {
		buffer.writeBytes(array, 0, array.length);
	}

	@Override
	public void write(final byte array[], final int offset, final int length)
			throws IOException {
		buffer.writeBytes(array, offset, length);

	}

}
