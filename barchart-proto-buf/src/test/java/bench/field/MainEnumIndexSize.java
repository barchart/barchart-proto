package bench.field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.buf.test.BenchEnumIndexSize;
import com.barchart.proto.buf.test.BenchEnumIndexSize.Type;

/** choose optimal trade date encoding */
public class MainEnumIndexSize {

	private static final Logger log = LoggerFactory
			.getLogger(MainEnumIndexSize.class);

	public static void main(final String... args) {

		log.debug("init");

		{

			final BenchEnumIndexSize message = BenchEnumIndexSize.newBuilder()
					.setType(Type.SIZE_120).build();

			log.debug("SIZE_120 size : {}", message.getSerializedSize());

		}

		{

			final BenchEnumIndexSize message = BenchEnumIndexSize.newBuilder()
					.setType(Type.SIZE_127).build();

			log.debug("SIZE_127 size : {}", message.getSerializedSize());

		}

		{

			final BenchEnumIndexSize message = BenchEnumIndexSize.newBuilder()
					.setType(Type.SIZE_128).build();

			log.debug("SIZE_128 size : {}", message.getSerializedSize());

		}

		{

			final BenchEnumIndexSize message = BenchEnumIndexSize.newBuilder()
					.setType(Type.SIZE_130).build();

			log.debug("SIZE_130 size : {}", message.getSerializedSize());

		}

		log.debug("done");

	}

}
