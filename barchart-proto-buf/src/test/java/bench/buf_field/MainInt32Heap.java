package bench.buf_field;

import java.lang.management.ManagementFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.test.BenchInt32;
import com.barchart.util.bench.size.JavaSize;
import com.sun.management.OperatingSystemMXBean;

/** measure message heap size */
@SuppressWarnings("restriction")
public class MainInt32Heap {

	private static final Logger log = LoggerFactory.getLogger(MainInt32Heap.class);

	private static final Runtime runtime = Runtime.getRuntime();

	static void heapGC() throws Exception {
		runtime.gc();
		Thread.sleep(1000);
		runtime.gc();
		Thread.sleep(1000);
		runtime.gc();
		Thread.sleep(1000);
		runtime.gc();
		Thread.sleep(1000);
		runtime.gc();
		Thread.sleep(1000);
	}

	final static OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory
			.getOperatingSystemMXBean();

	static long heapSize() {

		// long size = bean.getTotalPhysicalMemorySize();

		final long size = runtime.totalMemory();

		return size;
	}

	static final int COUNT = 1 * 1000 * 1000;

	static final BenchInt32[] array = new BenchInt32[COUNT];

	public static void main(final String... args) throws Exception {

		log.debug("init");

		{

			final BenchInt32 message = BenchInt32.newBuilder().build();

			log.debug("int32 wire size : {}", message.getSerializedSize());

		}

		{

			final BenchInt32 message = BenchInt32.newBuilder().build();

			log.debug("int32 heap estimated size : {}", JavaSize.of(message));

		}

		{

			/** warm up */
			for (int index = 0; index < COUNT; index++) {
				BenchInt32.newBuilder().setValue(index).build();
			}

			heapGC();

			final long heapStart = heapSize();

			for (int index = 0; index < COUNT; index++) {
				array[index] = BenchInt32.newBuilder().setValue(index).build();
			}

			heapGC();

			final long heapFinish = heapSize();

			final long heapChange = heapFinish - heapStart;

			final long messageSize = heapChange / COUNT;

			log.debug("int32 heap measured size : {}", messageSize);

			Thread.sleep(1000 * 1000);

		}

		log.debug("done");

	}

}
