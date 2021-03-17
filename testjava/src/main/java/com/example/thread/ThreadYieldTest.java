package com.example.thread;

/**
 * 理论上，yield意味着放手，放弃，投降。
 * 一个调用yield()方法的线程告诉虚拟机它乐意让其他线程占用自己的位置。
 * 这表明该线程没有在做一些紧急的事情。
 * 注意，这仅是一个暗示，并不能保证不会产生任何影响。
 * ps:
 */

public class ThreadYieldTest {

    public static void main(String[] args) {
        Thread thread1 = new MyThread("thread1");
        Thread thread2 = new MyThread("thread2");
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);
//        Thread thread3 = new MyThread("thread3");
        thread1.start();
        thread2.start();
//        thread3.start();
    }

    private static class MyThread extends Thread {

        private int count;

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (count++ < 20){
                System.out.println(getName() + " is running");
                Thread.yield();
            }
        }
    }
}
