package com.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuhanzhi on 2018/3/10.
 * 多线程交替打印
 * <p>
 * 1、条件
 * 1:有一个数组，存储了0~19这20个数字；
 * 2:有3个线程的名字分别是Thread0，Thread1，Thread2
 * 3:启动这3个线程顺序的从数组中取出一个数字打印出来
 * <p>
 * 要求
 * 1:Thread0必须第一个获取数据打印；
 * 2:同一时刻，只有一个线程在运行；
 * 3:线程是有序执行的，Thread0->Thread1->Thread2->Thread0->Thread1->…
 *
 *
 * 通过 synchronized 实现
 */

public class ThreadAlternateConditionOrderDemo3 {

    public static ArrayList<Integer> list = new ArrayList<>();

    public static final Object lock = new Object();

    private static int sequence = 0;

    private static final int THREAD_SIZE = 3;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_SIZE; i++) {
            threads.add(new MyThread(i));
        }

        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }

    }

    private static class MyThread extends Thread {

        private final int id;

        public MyThread(int id) {
            this.id = id;
            setName("thread" + id);
        }

        @Override
        public void run() {
            while (true) {
//                System.out.println(Thread.currentThread().getName() + "----lock");
                synchronized (lock) {
                    if(sequence == list.size()) {
                        break;
                    }
                    if(sequence % THREAD_SIZE == id) {
                        System.out.println(Thread.currentThread().getName() + ", i:" + list.get(sequence));
                        sequence++;
                    }
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                System.out.println(Thread.currentThread().getName() + "----unlock");

            }

            //notifyAll唤醒所有的线程，避免线程死等对象锁。
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }
}


