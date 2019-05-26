package io.hedwig.concurrence.basic.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 18009
 *
 */
public class T03_SynchronizedList {
	
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		List<String> strsSync = Collections.synchronizedList(strings);
	}
}