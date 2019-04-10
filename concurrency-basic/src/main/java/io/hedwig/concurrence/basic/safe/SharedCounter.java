package io.hedwig.concurrence.basic.safe;

/**
 * @author: patrick on 2019-01-26
 * @Description:
 */

public class SharedCounter {

  private int counter;

  public void increment() {
    counter = counter + 1;
  }

  public int getCount() {
    return counter;
  }
}
