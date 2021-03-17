package com.example.thread;

/**
 * Created by liuhanzhi on 2018/3/10.
 */

public class ThreadTest1 {

    public static int count;

    public static final Object lock = new Object();

    public static void main(String[] args) {
        MyThread mythread0 = new MyThread("thread0", 0);
        MyThread mythread1 = new MyThread("thread1", 1);
        MyThread mythread2 = new MyThread("thread2", 2);
        mythread0.start();
        mythread1.start();
        mythread2.start();
    }

    private static class MyThread extends Thread {

        int id;

        public MyThread(String name, int id) {
            super(name);
            this.id = id;
        }

        @Override
        public void run() {
            while (count < 100) {
                synchronized (lock) {
                    if (count >= 100) {
                        break;
                    }
                    if (count % 3 != id) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(getName() + " count:" + count);
                    count++;
                    lock.notifyAll();
                }
            }
        }
    }
}


