package io.hedwig.concurrence.basic.issues.ticketseller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {

  static final List<String> tickets = new ArrayList<String>();

  static {
    for (int i = 0; i < 10000; i++) {
      tickets.add("票编号:" + i);
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        while (true) {
          synchronized (tickets) {
            tickets.size();

            try {
              TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("销售了--" + tickets.remove(0));
          }
        }
      }).start();
    }
  }
}
	