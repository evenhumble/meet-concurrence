package io.hedwig.concurrence.demo.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

  private static final long serialVersionUID = 1L;

  private List<Product> products = new ArrayList<Product>();

  private int first;
  private int last;

  private int increment;

  public Task(List<Product> products, int first, int last, int increment) {
    this.products = products;
    this.first = first;
    this.last = last;
    this.increment = increment;
  }


  @Override
  protected void compute() {
    if (last - first < 10) {
      updatePrices();
    } else {
      int middle = (first + last) / 2;
      System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
      Task task1 = new Task(products, first, middle + 1, increment);
      Task task2 = new Task(products, middle + 1, last, increment);
      invokeAll(task1, task2);
    }
  }

  private void updatePrices() {
    for (int i = first; i < last; i++) {
      Product product = products.get(i);
      product.setPrice(product.getPrice() * (1 + increment));
    }
  }
}
