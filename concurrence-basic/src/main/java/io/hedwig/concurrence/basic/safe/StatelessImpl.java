package io.hedwig.concurrence.basic.safe;

import java.math.BigInteger;

/**
 * @author: patrick on 2019-01-26
 * @Description:
 */
public class StatelessImpl {

  public static BigInteger factorial(int number) {
    BigInteger f = new BigInteger("1");
    for (int i = 2; i <= number; i++) {
      f = f.multiply(BigInteger.valueOf(i));
    }
    return f;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(()->{
        System.out.println(StatelessImpl.factorial(12));
      }).start();
    }
  }

}
