/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.zip_jdk;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZipUtil {

	static final ThreadLocal<Deflater> zipPack = new ThreadLocal<Deflater>() {
		@Override
		protected Deflater initialValue() {

			final Deflater zipper = new Deflater();

			zipper.setLevel(Deflater.BEST_COMPRESSION);
			// zipper.setLevel(Deflater.BEST_SPEED);

			// zipper.setStrategy(Deflater.FILTERED);

			return zipper;
		}
	};

	public static byte[] compress(final byte[] arrayIn) throws Exception {

		final Deflater zipper = zipPack.get();

		zipper.reset();

		zipper.setInput(arrayIn);

		zipper.finish();

		final ByteArrayOutputStream output = //
		new ByteArrayOutputStream(arrayIn.length);

		final byte[] array = new byte[128];

		while (!zipper.finished()) {

			final int count = zipper.deflate(array);

			output.write(array, 0, count);

		}

		// zipper.end();

		final byte[] arrayOut = output.toByteArray();

		return arrayOut;

	}

	static final ThreadLocal<Inflater> zipUnpack = new ThreadLocal<Inflater>() {
		@Override
		protected Inflater initialValue() {
			return new Inflater();
		}
	};

	public static int decompress(final byte[] arrayIn, final byte[] arrayOut)
			throws Exception {

		final Inflater zipper = zipUnpack.get();

		zipper.reset();

		zipper.setInput(arrayIn, 0, arrayIn.length);

		final int sizeOut = zipper.inflate(arrayOut);

		zipper.end();

		return sizeOut;

	}

}
