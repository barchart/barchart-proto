package bench.fix;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.openfast.template.TemplateRegistry;
import org.openfast.template.loader.XMLMessageTemplateLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistryFIX {

	private static final Logger log = LoggerFactory
			.getLogger(RegistryFIX.class);

	public static TemplateRegistry makeTemplateRegistry(final String templateText)
			throws Exception {

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
