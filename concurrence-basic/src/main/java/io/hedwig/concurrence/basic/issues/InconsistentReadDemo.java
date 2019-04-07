package io.hedwig.concurrence.basic.issues;

/**
 * @author: patrick on 2019-02-25
 * @Description:
 */
public class InconsistentReadDemo {

  int count = 1;

  private class ConcurrencyCheckTask implements Runnable {

    @Override
    public void run() {
      int c = 0;
      for (int i = 0; ; i++) {
        // 在同一线程连读2次
        int c1 = count;
        int c2 = count;
        if (c1 != c2) {
          c++;
          System.err
              .printf("Got inconsistent read!! check time=%s, happen time=%s(%s%%), 1=%s, 2=%s\n",
                      i + 1, c, (float) c / (i + 1) * 100, c1, c2);
        }
      }
    }
  }

  ConcurrencyCheckTask getConcurrencyCheckTask() {
    return new ConcurrencyCheckTask();
  }

  public static void main(String[] args) {
    InconsistentReadDemo demo = new InconsistentReadDemo();

    Thread thread = new Thread(demo.getConcurrencyCheckTask());
    thread.start();

    while (true) {
      demo.count++;
    }
  }


}
