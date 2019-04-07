package io.hedwig.concurrence.basic.thread;

/**
 * @author: patrick on 2019-01-27
 * @Description:
 */
public class ThreadStates {

  public static void main(String[] args) {
    new Thread(new TimeWaitingThread(),"time-waiting").start();
    new Thread(new WaitingThread(),"waiting").start();
    new Thread(new BlockedThread(),"block-1").start();
    new Thread(new BlockedThread(),"block-2").start();
  }

  static class TimeWaitingThread implements Runnable{

    @Override
    public void run() {
      System.out.println("time waiting thread "+ Thread.currentThread().getId());
      sleepInSeconds(20);
    }
  }

  static class WaitingThread implements Runnable{

    @Override
    public void run() {
      while(true){
        synchronized (WaitingThread.class){
          try {
            WaitingThread.class.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }


  static class BlockedThread implements Runnable{

    @Override
    public void run() {
      while(true){
        synchronized (BlockedThread.class){
          sleepInSeconds(100);
        }
      }
    }
  }

  static void sleepInSeconds(int s){
    try {
      Thread.sleep(s);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
