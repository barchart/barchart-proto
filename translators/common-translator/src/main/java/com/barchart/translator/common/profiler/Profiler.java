package com.barchart.translator.common.profiler;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;


public class Profiler {

	private final Multimap<Class<?>, Stopwatch> stopwatchMap;
	
	
	
	public Profiler() {
		this.stopwatchMap = HashMultimap.create();
	}
	
	
	public String gatherStatistics() {
		
		for (Class<?> key : stopwatchMap.keys()) {
			Collection<Stopwatch> stopwatches = stopwatchMap.get(key);
			aggregateStopwatches(stopwatches);
		}
		
		return null;
	}
	
	
	private void aggregateStopwatches(Collection<Stopwatch> stopwatches) {
		long elapsedNanos = 0;
		int count = 0;
		for (Stopwatch stopwatch : stopwatches) {
			
		}
	}


	public synchronized Stopwatch newStopwatch(Class<?> clazz) {
		Stopwatch stopwatch = new Stopwatch();
		stopwatchMap.put(clazz,  stopwatch);
		return stopwatch;
	}
	
	
	public static class Stopwatch {
		
		private boolean isRunning;

		private long startNanos;

		private final StopwatchStatistic statistic;
		
		private Stopwatch() {
			this.isRunning = false;
			this.statistic = new StopwatchStatistic();
		}
		
		public void start() {
			if (isRunning) {
				throw new IllegalStateException();
			} else {
				startNanos = System.nanoTime();
				isRunning = true;
			}
		}
		
		public void stop() {
			if (!isRunning) {
				throw new IllegalStateException();
			} else {
				long stopNanos = System.nanoTime();
				long elapsed = stopNanos - startNanos;
				statistic.addStatistic(elapsed);
				isRunning = false;
			}
		}
		

		
	}
	
	private static class StopwatchStatistic {
		
		private ReentrantLock lock;
		private long totalElapsed;
		private int count;

		private StopwatchStatistic() {
			this.lock = new ReentrantLock();
		}
		
		private void addStatistic(long elapsed) {
			lock.lock();
			try {
				this.totalElapsed += elapsed;
				count++;
			} finally {
				lock.unlock();
			}
		}		
		
	}
	
}
