# 并发和多线程(concurrency and multiplethreading)

* 进程(process)：pid:进程号,独立的内存空间，每一个程序一个进程
* 线程(thread): tid:线程号，线程运行在进程中，一个进程至少一个线程，线程比进程更加轻量级

## 单个CPU

- 一开始一个CPU一个程序
- 一个CPU也可以多任务，一个CPU如何进行多任务:
![img](img/single-cpu.jpg)

CPU可以在不同的时间片段里面切换运行的任务,上图中多任务可以是
多个进程，也可以是多个线程.

## 多个CPU

![img](img/%20%20multiple-cores.jpg)
![img](http://tutorials.jenkov.com/images/java-concurrency/java-concurrency-tutorial-introduction-1.png)

多核CPU和单个CPU类似，每一个核可以认为和一个单CPU

## 并发和多线程优势和难点

- 优势
  * 多任务同一时间执行,提高性能
  * 资源使用率更好
- 劣势:
  * 代码设计更难
  * Context Switching Overhead
  * 资源消耗更多
- 难点: 程序更加不容易写正确
  * 同一个数据有人读，有人写，如何保证数据是准确的
  * 同时读写相同资源,出现资源争抢

## 问题举例

计数器：



