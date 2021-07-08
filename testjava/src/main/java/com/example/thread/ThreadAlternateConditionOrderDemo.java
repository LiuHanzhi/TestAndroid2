package com.example.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
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
 * 使用 ReentrantLock + Condition  实现
 */
public class ThreadAlternateConditionOrderDemo extends Thread {
    /**
     * 多个线程共享这一个sequence数据
     */
    private static int sequence = 0;

    private static final int SEQUENCE_END = 19;

    private Integer id;
    private ReentrantLock lock;
    private Condition[] conditions;


    private ThreadAlternateConditionOrderDemo(Integer id, ReentrantLock lock, Condition[] conditions) {
        this.id = id;
        this.setName("thread" + id);
        this.lock = lock;
        this.conditions = conditions;
    }

//    @Override
//    public void run() {
//        while (sequence >= 0 && sequence < SEQUENCE_END) {
//            lock.lock();
//            try {
//                //对序号取模,如果不等于当前线程的id,则先唤醒其他线程,然后当前线程进入等待状态
//                while (sequence % conditions.length != id) {
//                    conditions[(id + 1) % conditions.length].signal();
//                    conditions[id].await();
//                }
//                System.out.println(Thread.currentThread().getName() + " " + sequence);
//                //序号加1
//                sequence = sequence + 1;
//                //唤醒当前线程的下一个线程
//                conditions[(id + 1) % conditions.length].signal();
//                //当前线程进入等待状态
//                conditions[id].await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                //将释放锁的操作放到finally代码块中,保证锁一定会释放
//                lock.unlock();
//            }
//        }
//        //数字打印完毕,线程结束前唤醒其余的线程,让其他线程也可以结束
//        end();
//    }

    @Override
    public void run() {
        while (sequence >= 0 && sequence < SEQUENCE_END) {
            try {
                System.out.println(Thread.currentThread().getName() + " lock");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " sequence:" + sequence);
                if (sequence % conditions.length == id) {
                    System.out.println(Thread.currentThread().getName() + " " + sequence);
                    //序号加1
                    sequence = sequence + 1;
                    //唤醒当前线程的下一个线程
                    //当前线程进入等待状态
                }
                conditions[(id + 1) % conditions.length].signal();
                conditions[id].await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlock");
            }
        }
        end();
    }

    private void end() {
        lock.lock();
        conditions[(id + 1) % conditions.length].signal();
        conditions[(id + 2) % conditions.length].signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        int threadCount = 3;
        ReentrantLock lock = new ReentrantLock();
        Condition[] conditions = new Condition[threadCount];
        for (int i = 0; i < threadCount; i++) {
            conditions[i] = lock.newCondition();
        }
        ThreadAlternateConditionOrderDemo[] printNumbers = new ThreadAlternateConditionOrderDemo[threadCount];
        for (int i = 0; i < threadCount; i++) {
            ThreadAlternateConditionOrderDemo p = new ThreadAlternateConditionOrderDemo(i, lock, conditions);
            printNumbers[i] = p;
        }
        for (ThreadAlternateConditionOrderDemo printNumber : printNumbers) {
            printNumber.start();
        }
    }

}
