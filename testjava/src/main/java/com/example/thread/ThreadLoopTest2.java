package com.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuhanzhi on 2018/3/27.
 * 两个线程交替执行:可以使用synchronized和objec的wait和notify使用
 * 三个以上线程交替执行,需使用ReetranLock和Condition来使用(在启动a线程执行)
 */

public class ThreadLoopTest2 {
    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);//先休眠一段时间,等其他线程执行了await操作后,再开始执行本线程。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = 0;
                while (i < 100) {
                    lock.lock();
                    try {
                        print("a", i);
                        condition1.signal();
//                        System.out.println("a:waiting...");
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                    i++;
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    lock.lock();
                    try {
//                        System.out.println("b:waiting...");
                        condition1.await();
                        print("b", i);
                        condition1.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                    i++;
                }
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    lock.lock();
                    try {
//                        System.out.println("c:waiting...");
                        condition1.await();
                        print("c", i);
                        condition1.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                    i++;
                }
            }
        });
        a.start();
        b.start();
        c.start();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.newCondition();
    }

    private static void print(String name, int i) {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("name:" + name + ",i:" + i);
    }


}
