package io.hedwig.concurrence.demo.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockSample {

    /**
     * Read Write Lock 在不同的线程中相互没有干扰
     */
    public static void readWriteLockSampleRun() {
        final Queue3 queue3 = new Queue3();
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    System.out.println("init read thread " + currentThread().getName());
                    while (true) {
                        queue3.get();
                    }
                }
            }.start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        System.out.println("init write thread " + currentThread().getName());
                        queue3.put(new Random().nextInt(1000));
                    }
                }
            }.start();
        }
    }

    /**
     * Simple Read Write 修改
     */
    public static class Queue3 {
        private Object data = null;
        private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        public void get() {
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " be ready to read data!");
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " have read data:" + data);
            rwLock.readLock().unlock();
        }

        public void put(Object data) {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " be ready to write data!");
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " have write data:" + data);
            rwLock.writeLock().unlock();
        }
    }

    public static class CacheDemo {
        /**
         * Cache Demo for cache object in a map
         */
        private Map<String, Object> map = new HashMap<String, Object>();//缓存器
        private ReadWriteLock rwl = new ReentrantReadWriteLock();

        public static void main(String[] args) {

        }

        public Object get(String id) {
            Object value = null;
            rwl.readLock().lock();//首先开启读锁，从缓存中去取
            try {
                value = map.get(id);
                if (value == null) {  //如果缓存中没有释放读锁，上写锁
                    rwl.readLock().unlock();
                    rwl.writeLock().lock();
                    try {
                        if (value == null) {
                            value = "aaa";  //此时可以去数据库中查找，这里简单的模拟一下
                        }
                    } finally {
                        rwl.writeLock().unlock(); //释放写锁
                    }
                    rwl.readLock().lock(); //然后再上读锁
                }
            } finally {
                rwl.readLock().unlock(); //最后释放读锁
            }
            return value;
        }

    }

    public static void main(String[] args) {
        readWriteLockSampleRun();
    }
}
