package io.hedwig.concurrence.basic.issues;

/**
 * @author: patrick on 2019-02-24
 * @Description: ThreadSafe Singleton
 */
public class ThreadSafeSingleton {
  private ThreadSafeSingleton(){}

  private static class Inner {
    private static ThreadSafeSingleton instance =
        new ThreadSafeSingleton();
  }

  public static ThreadSafeSingleton getInstance(){
    return Inner.instance;
  }
}
