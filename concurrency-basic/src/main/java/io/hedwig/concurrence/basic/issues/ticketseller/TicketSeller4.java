package io.hedwig.concurrence.basic.issues.ticketseller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {

  static Queue<String> tickets = new ConcurrentLinkedQueue<String>();  // Queue里面不应该有空值

  static {
    for (int i = 0; i < 1000; i++) {
      tickets.add("票编号:" + i);
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        while (true) {
          String s = tickets.poll();
          if (s == null) {
            break;
          }
          System.out.println("销售了--" + s);
        }
      }).start();
    }
  }
}