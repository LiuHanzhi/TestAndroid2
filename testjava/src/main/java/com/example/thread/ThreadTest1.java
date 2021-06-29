package com.example.thread;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuhanzhi on 2018/3/10.
 *
 * 1、条件
 * 1:有一个数组，存储了0~19这20个数字；
 * 2:有3个线程的名字分别是Thread0，Thread1，Thread2
 * 3:启动这3个线程顺序的从数组中取出一个数字打印出来
 *
 * 要求
 * 1:Thread0必须第一个获取数据打印；
 * 2:同一时刻，只有一个线程在运行；
 * 3:线程是有序执行的，Thread0->Thread1->Thread2->Thread0->Thread1->…
 *
 */

public class ThreadTest1 {

    public static ArrayList<Integer> list = new ArrayList<>();

    public static final ReentrantLock lock = new ReentrantLock(true); //使用公平锁

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        MyThread mythread0 = new MyThread("thread0");
        MyThread mythread1 = new MyThread("thread1");
        MyThread mythread2 = new MyThread("thread2");
        mythread0.start();
        mythread1.start();
        mythread2.start();
    }

    private static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
//            System.out.println(Thread.currentThread().getName() + "----11111");
            while (true) {
//                System.out.println(Thread.currentThread().getName() + "----22222");

//                if(!lock.isHeldByCurrentThread()) {
                    lock.lock();
                    if(list.isEmpty()) {
                        break;
                    }
                    int i = list.remove(0);
                    System.out.println(Thread.currentThread().getName() + ", i:" + i);
                    lock.unlock();
//                }

            }
        }
    }
}


