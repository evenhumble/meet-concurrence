package io.hedwig.concurrence.basic.lock;

/**
 * @author: patrick on 2019-02-23
 * @Description:
 */
//todo: micro benchmark
//: atomic operation,there is no racing condition
public class SynchronizedLock {

  private int count = 10;
  private final Object o = new Object();

  public void objectAsLock() {
    synchronized(o) { // 任何线程要执行下面的代码， 必须先拿到o的锁
      count --;
      System.out.println(Thread.currentThread().getName() + "," + count);
    }
  }

  public synchronized void instanceAsLock2() {
      count --;
      System.out.println(Thread.currentThread().getName() + "," + count);
  }

  public void instanceAsLock() {
    synchronized(this) {
      count --;
      System.out.println(Thread.currentThread().getName() + "," + count);
    }
  }
  public static void main(String[] args) {
    SynchronizedLock lockTest = new SynchronizedLock();
    for (int i = 0; i < 20; i++) {
      new Thread(lockTest::objectAsLock).start();
      new Thread(lockTest::instanceAsLock).start();
    }
  }
}
