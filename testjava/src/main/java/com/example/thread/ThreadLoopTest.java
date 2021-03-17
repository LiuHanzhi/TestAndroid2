package com.example.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuhanzhi on 2018/3/27.
 * 两个线程交替执行:可以使用synchronized和objec的wait和notify使用
 * 三个以上线程交替执行,需使用ReetranLock和Condition来使用
 */

public class ThreadLoopTest {
    public static void main(String[] args) {
        final Object lock = new Object();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    synchronized (lock) {
                        try {
                            lock.wait();
                            print("a",i);
                            lock.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
                    synchronized (lock) {
                        try {
                            print("b",i);
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
                    synchronized (lock) {
                        try {
                            print("c",i);
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
        System.out.println("name:" + name + ",i:"+i);
    }


}
