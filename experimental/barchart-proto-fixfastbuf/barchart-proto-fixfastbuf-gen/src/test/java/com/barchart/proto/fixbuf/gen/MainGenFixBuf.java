/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package com.barchart.proto.fixbuf.gen;

public class MainGenFixBuf {

	public static void main(final String[] none) throws Exception {

		final String[] args = new String[] {
				//
				"./src/main/resources/fix", // fix spec
				"./src/main/resources/buf", // buf transform
				"./target/protocol" // output
		};

		GenFixBuf.main(args);

	}

}
