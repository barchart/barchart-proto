package bench.time;

import java.util.concurrent.atomic.AtomicBoolean;

public class StopWatch2 {

	private final AtomicBoolean isRunning = new AtomicBoolean(false);

	private long start;
	private long finish;

	private long blockTotal;
	private long blockStart;

	public void blockStart() {
		blockStart = System.nanoTime();
	}

	public void blockFinish() {
		blockTotal += (System.nanoTime() - blockStart);
	}

	/** nanos */
	public long getBlockTotal() {
		return blockTotal;
	}

	public void clear() {
		final long now = System.nanoTime();
		start = now;
		finish = now;
		isRunning.set(false);
	}

	public boolean isRunning() {
		return isRunning.get();
	}

	/**
	 * @param duration
	 *            - nano
	 */
	public boolean hasExceeded(final long duration) {
		return System.nanoTime() - start > duration;
	}

	public void start() {
		if (isRunning.compareAndSet(false, true)) {
			start = System.nanoTime();
		}
	}

	public void startNow() {
		start = System.nanoTime();
	}

	public void stop() {
		if (isRunning.compareAndSet(true, false)) {
			finish = System.nanoTime();
		}
	}

	public void stopNow() {
		finish = System.nanoTime();
	}

	/** nano */
	public long getDiff() {
		return finish - start;
	}

	/** nano */
	public long getStart() {
		return start;
	}

	/** nano */
	public long getStop() {
		return finish;
	}

	/** nano */
	public long getDiffNow() {
		return System.nanoTime() - start;
	}

	/** per nano */
	private final static double ONE_SECOND = 1000 * 1000 * 1000;
	private final static double ONE_MILLI = 1000 * 1000;
	private final static double ONE_MICRO = 1000;

	public String toStringPretty() {

		final double nanoDiff = finish - start;

		final int seconds = (int) (nanoDiff / ONE_SECOND);

		final int millis = (int) ((nanoDiff - seconds * ONE_SECOND) / ONE_MILLI);

		final int micros = (int) ((nanoDiff - seconds * ONE_SECOND - millis
				* ONE_MILLI) / ONE_MICRO);

		final int nanos = (int) (nanoDiff - seconds * ONE_SECOND - millis
				* ONE_MILLI - micros * ONE_MICRO);

		return String.format("%d s %d ms %d us %d ns ", seconds, millis,
				micros, nanos);

	}

	@Override
	public String toString() {
		return "nanoTime: " + Long.toString(getDiff());
	}

}
