package com.example.thread;

/**
 * join()方法

 线程实例的方法join()方法可以使得一个线程在另一个线程结束后再执行。
 如果join()方法在一个线程实例上调用，当前运行着的线程将阻塞直到这个线程实例完成了执行。
 */
public class JoinExample2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Second task started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Second task completed");
            }
        });
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("First task started");
                System.out.println("Sleeping for 2 seconds");
                try {
                    t1.start();
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("First task completed");
            }
        });

        t.start();

    }
}