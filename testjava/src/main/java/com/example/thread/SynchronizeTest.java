package com.example.thread;

/**
 * Created by liuhanzhi on 2018/3/10.
 */

public class SynchronizeTest {

    public static void main(String[] arg) {
        SynchronizeTest st = new SynchronizeTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                st.t4();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizeTest.t5();
            }
        });
        t1.start();
        t2.start();
    }

    public synchronized void t1() {
        System.out.println("11111---start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("11111---end");
    }

    public synchronized void t4() {
        System.out.println("444---start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("444---end");
    }

    public static synchronized void t5() {
        System.out.println("555---start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("555---end");
    }


    public void t2() {
        synchronized (SynchronizeTest.this) {
            System.out.println("2222---start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2222---end");
        }
    }

    public void t3() {
        synchronized (SynchronizeTest.this) {
            System.out.println("333---start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("333---end");
        }
    }
}
