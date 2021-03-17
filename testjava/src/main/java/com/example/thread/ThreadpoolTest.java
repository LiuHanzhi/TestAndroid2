package com.example.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuhanzhi on 2018/3/8.
 */

public class ThreadpoolTest {

    public static void main(String[] args) {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(false));
//        for (int i = 0; i < 10; i++) {
//            executor.execute(new Consumer(i));
//        }

        SynchronousQueue<Product> queue = new SynchronousQueue<>(true);
        //生产者线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    System.out.println("--------");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("start product:id=" + count);

                    //1. SynchronousQueue.offer()
//                    boolean offer = queue.offer(new Product(count));//使用offer方法,只要当消费线程准备好(执行take)时,才会返回true
//                    System.out.println("offer:" + offer);

                    //2. SynchronousQueue.put()
                    try {
                        queue.put(new Product(count));//使用put方法,生产者线程生产出一个product后,会一直阻塞,直到消费线程消费了(执行take)这个product

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("end product:id=" + count);

                    count++;
                }


            }
        }).start();
        //消费者线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("--------");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        System.out.println("start consume");
                        Product product = queue.take();
                        System.out.println("end consume:id=" + product.i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    private static class Product {

        public int i;

        Product(int i) {
            this.i = i;
        }
    }

}
