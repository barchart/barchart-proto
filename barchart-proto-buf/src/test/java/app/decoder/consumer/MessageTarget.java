/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package app.decoder.consumer;

import app.decoder.data.DataMaker;
import app.decoder.news.NewsMaker;

public interface MessageTarget {

	DataMaker getDataMaker();

	NewsMaker getNewsMaker();

}
