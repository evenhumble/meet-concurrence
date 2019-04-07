package io.hedwig.concurrence.basic.issues;

/**
 * @author: patrick on 2019-02-24
 * @Description: volatile: a single variable is
 * supposed to be accessed by multiple threads
 * concurrently
 *
 * In order to achieve significant performance
 * improvements, the Java Memory Model allows threads to
 * cache variables in their thread local memory without the need of constantly synchronize variable values with the main memory.
 *
 * If a variable is declared as volatile it
 * means that the variable should be always
 * written and read directly through main memory.
 *
 * happen before: volatile variable and another thread later
 * access that same variable happen before
 * shared object or state should be defined as volatile
 */
public class VolatileDemo  {

   static class VolatileThread extends Thread {
//      volatile
      boolean keepRunning=true;

      @Override
      public void run() {
        long count = 0;
        while(keepRunning){
          count++;
        }
        System.out.println("Thread terminated." + count);
      }

    }

  public static void main(String[] args) throws InterruptedException {
    VolatileThread thread = new VolatileThread();
    thread.start();
    Thread.sleep(1000);
    System.out.println("after sleeping in main");
    thread.keepRunning = false;
    //actually hang forever if keepRunning not set to volatile
    thread.join();
    System.out.println("keepRunning set to " + thread.keepRunning);

  }

}
