package io.hedwig.concurrence.basic.atomicTypes.impl;

import io.hedwig.concurrence.basic.atomicTypes.CountingManager;

import java.util.concurrent.atomic.AtomicInteger;

public class CountingManagerAtomicInteger implements CountingManager {

	private AtomicInteger counter;
	
	public CountingManagerAtomicInteger() {
		counter = new AtomicInteger(0);
	}
	
	public void increment() {
		counter.getAndIncrement();
	}
	
	public long getCounter() {
		return counter.get();
	}
	
}