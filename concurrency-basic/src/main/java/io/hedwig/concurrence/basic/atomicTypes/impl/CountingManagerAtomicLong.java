package io.hedwig.concurrence.basic.atomicTypes.impl;

import io.hedwig.concurrence.basic.atomicTypes.CountingManager;

import java.util.concurrent.atomic.AtomicLong;

public class CountingManagerAtomicLong implements CountingManager {

	private AtomicLong counter;

	public CountingManagerAtomicLong() {
		counter = new AtomicLong(0);
	}

	public void increment() {
		counter.getAndIncrement();
	}

	public long getCounter() {
		return counter.get();
	}
}
