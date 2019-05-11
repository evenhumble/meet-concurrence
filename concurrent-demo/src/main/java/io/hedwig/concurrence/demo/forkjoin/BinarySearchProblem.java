package io.hedwig.concurrence.demo.forkjoin;

import java.util.Arrays;

public class BinarySearchProblem {

  private final int[] numbers;
  private final int start;
  private final int end;
  private final int size;

  public BinarySearchProblem(int[] numbers, int start, int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
    this.size = end - start;
  }

  public int searchSequentially(int numberToSearch) {
    return Arrays.binarySearch(numbers, start, end, numberToSearch);
  }

  public BinarySearchProblem subProblem(int subStart, int subEnd) {
    return new BinarySearchProblem(numbers, start + subStart, start + subEnd);
  }

  public int getSize() {
    return size;
  }
}
