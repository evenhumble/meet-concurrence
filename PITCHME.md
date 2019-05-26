# Meet Concurrency 

Learning JAVA Concurrency By Examples:

- Issues
- Solutions

----

## Thread Safe

- What is ThreadSafe? 
- Not Thread Safe Example:
```java
public class NotThreadSafeIssue {

  private static int count = 0;

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(() -> {
        count++;
        System.out.println(Thread.currentThread().getName() + ":" + count);
      }).start();
    }
  }
}

```

The program expected that every thread
read a number which increased by itself,but the actual result is not in that
way, the thread 10 and 11 the value is increased by other thread.It is not thread safe.

```java
Thread-10:12
Thread-11:12
Thread-12:13
Thread-13:14
```