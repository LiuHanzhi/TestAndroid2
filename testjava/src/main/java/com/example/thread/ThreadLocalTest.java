package com.example.thread;

/**
 * Created by liuhanzhi on 2018/3/9.
 */

public class ThreadLocalTest {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocal<String> threadLocal = new ThreadLocal<String>();
                threadLocal.set("aaaaa");
                ttt();
            }

            private void ttt() {
                ThreadLocal<String> threadLocal = new ThreadLocal<String>();
                System.out.println(threadLocal.get());
            }

        }).start();
    }
}
