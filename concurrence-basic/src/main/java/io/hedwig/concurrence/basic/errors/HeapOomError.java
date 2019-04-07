package io.hedwig.concurrence.basic.errors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: patrick on 2019-04-06
 * @Description:
 */
public class HeapOomError {

  static class OomObject{}

  public static void main(String[] args) {
    List<OomObject> list = new ArrayList<OomObject>();
    while (true) {
      list.add(new OomObject());
    }
  }
}
