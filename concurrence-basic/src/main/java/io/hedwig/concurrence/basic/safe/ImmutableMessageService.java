package io.hedwig.concurrence.basic.safe;

import net.jcip.annotations.Immutable;

@Immutable
//inner state can be changed after construct
public class ImmutableMessageService {

  private final String message;

  public ImmutableMessageService(String message) {
    this.message = message;
  }


  public String getMessage() {
    return message;
  }
}