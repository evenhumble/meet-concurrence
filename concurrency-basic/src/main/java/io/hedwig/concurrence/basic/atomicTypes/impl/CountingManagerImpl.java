package io.hedwig.concurrence.basic.atomicTypes.impl;

import io.hedwig.concurrence.basic.atomicTypes.CountingManager;

public class CountingManagerImpl implements CountingManager {

	private long counter;

	public void increment() {
		counter++;
	}

	public long getCounter() {
		return counter;

	}
}
	