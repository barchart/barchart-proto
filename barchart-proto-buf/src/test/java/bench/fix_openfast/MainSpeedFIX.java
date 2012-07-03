package bench.fix_openfast;

import java.io.File;

import org.openfast.Message;
import org.openfast.template.TemplateRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bench.fix_openfast.Factory.Mode;

/** measure message heap size */
public class MainSpeedFIX {

	private static final Logger log = LoggerFactory
			.getLogger(MainSpeedFIX.class);

	static final int COUNT_TEST = 100 * 1000;

	static final int COUNT_ENTRY = 20;

	static final long A_LONG = Long.MAX_VALUE - 3;
	static final int AN_INT = Integer.MAX_VALUE - 3;

	private static TemplateRegistry registry;

	private static Factory factory;

	static {

		log.debug("setup");

		final File file = new File("src/test/resources/fixfast/templates.xml");

		try {

			registry = RegistryFIX.makeTemplateRegistry(file);

			factory = new Factory(registry);

		} catch (final Exception e) {
			log.error("", e);
		}

	}

	/** TODO finish */
	public static void main(final String... args) throws Exception {

		log.debug("init");

		testProfile(Mode.SNAPSHOT_SIMPLE);

		// testProfile(Mode.SNAPSHOT_COMPLEX);

		// testProfile(Mode.SNAPSHOT_SIMPLE_DESC);

		//

		// testProfile(Mode.UPDATE_SIMPLE);

		// testProfile(Mode.UPDATE_COMPLEX);

		// testProfile(Mode.UPDATE_SIMPLE_DESC);

		log.debug("done");

	}

	static void testProfile(final Factory.Mode mode) throws Exception {

		log.debug("#############################");

		log.debug("MODE  : {}", mode);

		log.debug("COUNT_TEST  : {}", COUNT_TEST);

		// testWireSize(mode);

		testSpeedBuild(mode);

		// testSpeedWrite(mode);

		// testSpeedDecode(mode);

		// testSpeedAll(mode);

		log.debug("#############################");

	}

	static Message buildBase(final Mode mode) {

		return factory.newMessage(mode);

	}

	static void testSpeedBuild(final Mode mode) {

		/** warm up */
		for (int index = 0; index < COUNT_TEST; index++) {
			final Message message = buildBase(mode);
		}

		final long timeStart = System.nanoTime();

		/** measure */
		for (int index = 0; index < COUNT_TEST; index++) {
			final Message message = buildBase(mode);
		}

		final long timeFinish = System.nanoTime();

		final long timeChange = timeFinish - timeStart;

		final long buildSpeed = timeChange / COUNT_TEST;

		log.debug("msg build speed, nano : {}", buildSpeed);

	}

}
