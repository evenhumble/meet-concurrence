package io.hedwig.concurrence.demo.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class BiSearchWithForkJoin extends RecursiveAction {

  private final int threshold;
  private final BinarySearchProblem binarySearchProblem;
  private int result;
  private final int numberToSearch;

  public BiSearchWithForkJoin(int threshold, BinarySearchProblem binarySearchProblem,
                              int numberToSearch) {
    this.threshold = threshold;
    this.binarySearchProblem = binarySearchProblem;
    this.numberToSearch = numberToSearch;
  }


  @Override
  protected void compute() {
    if (binarySearchProblem.getSize() < threshold) {
      result = binarySearchProblem.searchSequentially(numberToSearch);
    } else {
      int midPoint = binarySearchProblem.getSize() / 2;
      BiSearchWithForkJoin
          leftProblem =
          new BiSearchWithForkJoin(threshold, binarySearchProblem.subProblem(0, midPoint),
                                   numberToSearch);
      BiSearchWithForkJoin
          rightProblem =
          new BiSearchWithForkJoin(threshold, binarySearchProblem
              .subProblem(midPoint + 1, binarySearchProblem.getSize()), numberToSearch);
      invokeAll(leftProblem, rightProblem);
      result = Math.max(leftProblem.result, rightProblem.result);
    }
  }

  public static void main(String[] args) {
    int[] data = new int[10000];
    for (int i = 0; i < data.length; i++) {
      data[i] = i;
    }

    BinarySearchProblem binarySearchProblem1 = new BinarySearchProblem(data, 0, data.length);
    int threshold = 100;
    int nThreads = 10;

    BiSearchWithForkJoin
        biSearchWithForkJoin =
        new BiSearchWithForkJoin(threshold, binarySearchProblem1, 555);
    ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
    forkJoinPool.invoke(biSearchWithForkJoin);
    System.out.printf("Result is: %d%n", biSearchWithForkJoin.result);

  }
}
