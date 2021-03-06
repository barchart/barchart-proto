/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.fix_openfast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.openfast.template.TemplateRegistry;
import org.openfast.template.loader.XMLMessageTemplateLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistryFIX {

	static final Logger log = LoggerFactory.getLogger(RegistryFIX.class);

	public static TemplateRegistry makeTemplateRegistry(
			final String templateText) throws Exception {

		final InputStream templateSource = new ByteArrayInputStream(
				templateText.getBytes("UTF-8"));

		final XMLMessageTemplateLoader templateLoader = new XMLMessageTemplateLoader();

		templateLoader.setLoadTemplateIdFromAuxId(true);

		templateLoader.load(templateSource);

		final TemplateRegistry registry = templateLoader.getTemplateRegistry();

		return registry;

	}

	public static TemplateRegistry makeTemplateRegistry(final File file)
			throws Exception {

		String text;

		text = FileUtils.readFileToString(file);

		return makeTemplateRegistry(text);

	}

}
