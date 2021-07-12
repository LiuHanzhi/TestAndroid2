package com.example.mathematics.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        List<Data> list = new ArrayList<>();

        list.add(new Data(3));
        list.add(new Data(5));
        list.add(new Data(4));

        for (Data data : list) {
            System.out.println("i:" + data.i);
        }
        System.out.println("=====");


        PriorityQueue<Data> queue = new PriorityQueue<>();
        queue.offer(new Data(3));
        queue.offer(new Data(5));
        queue.offer(new Data(4));
//        queue.addAll(list);

        for (Data data : queue) {
            System.out.println("i:" + data.i);
        }
        System.out.println("=====");
        while (!queue.isEmpty()) {
            System.out.println("i:" + queue.poll());
        }

    }

    public static class Data implements Comparable<Data> {

        private int i;

        public Data(int i) {
            this.i = i;
        }

        @Override
        public int compareTo(Data o) {
            return this.i - o.i;
        }

    }
}
