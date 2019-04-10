package io.hedwig.concurrence.basic.thread;

/**
 * @author: patrick on 2019-01-27
 * @Description:
 */
public class StartAThread {

  public static void main(String[] args) {
     new Thread(new Runnable() {
       public void run() {
         System.out.println("start a new thread");
       }
     }).start();
  }
}
