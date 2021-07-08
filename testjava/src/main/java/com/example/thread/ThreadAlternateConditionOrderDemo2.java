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
 * 使用 ReentrantLock + 公平锁  实现
 */

public class ThreadAlternateConditionOrderDemo2 {

    public static ArrayList<Integer> list = new ArrayList<>();

    public static final ReentrantLock lock = new ReentrantLock(true); //使用公平锁

    private static final int THREAD_SIZE = 3;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < THREAD_SIZE; i++) {
            list.add(new MyThread("thread" + i));
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
            Thread.sleep(1); //设置一个延迟start时间，保证thread的启动先后顺序。
        }
    }

    private static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "----11111");
            while (true) {
                System.out.println(Thread.currentThread().getName() + "----lock");

//                if(!lock.isHeldByCurrentThread()) {
                lock.lock();
                if (list.isEmpty()) {
                    lock.unlock();
                    break;
                }
                int i = list.remove(0);
                System.out.println(Thread.currentThread().getName() + ", i:" + i);
                try {
                    Thread.sleep(10); // 设置一个sleep时间，保证方法执行时，其他线程已经启动
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "----unlock");
//                }

            }
        }
    }
}


