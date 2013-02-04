/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.buf.wrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

public class PacketWrapperFile implements Iterable<PacketWrapper> {

	private final File file;

	public PacketWrapperFile(File file) {
		this.file = file;
	}

	@Override
	public Iterator<PacketWrapper> iterator() {
		try {
			return new IteratorImpl(getInputStreamForPacketFile());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private InputStream getInputStreamForPacketFile() throws IOException {
		InputStream fileInputStream = new FileInputStream(file);
		if (file.getName().endsWith(".gz")) {
			return new GZIPInputStream(fileInputStream);
		} else {
			return fileInputStream;
		}
	}

	private static class IteratorImpl implements Iterator<PacketWrapper> {

		private final InputStream inputStream;
		private PacketWrapper next;

		public IteratorImpl(InputStream inputStream) throws IOException {
			this.inputStream = inputStream;
			this.next = readNext();

		}

		private PacketWrapper readNext() {
			try {
				PacketWrapper nextWrapper = PacketWrapper.parseDelimitedFrom(inputStream);
				if (nextWrapper == null) {
					inputStream.close();
				}
				return nextWrapper;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public PacketWrapper next() {
			PacketWrapper wrapper = next;
			next = readNext();
			return wrapper;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public String toString() {
		return "PacketWrapperFile [file=" + file + "]";
	}

	public String getName() {
		return file.getName();
	}

}
