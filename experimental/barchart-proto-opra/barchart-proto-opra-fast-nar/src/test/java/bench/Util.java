/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;


public class Util {

	public static byte[] compress(final byte[] arrayIn) throws Exception {

		final Deflater zipper = new Deflater();

		zipper.setLevel(Deflater.BEST_COMPRESSION);

		zipper.setInput(arrayIn);

		zipper.finish();

		final ByteArrayOutputStream output = //
		new ByteArrayOutputStream(arrayIn.length);

		final byte[] array = new byte[1024];

		while (!zipper.finished()) {

			final int count = zipper.deflate(array);

			output.write(array, 0, count);

		}

		output.close();

		final byte[] arrayOut = output.toByteArray();

		return arrayOut;

	}

}
