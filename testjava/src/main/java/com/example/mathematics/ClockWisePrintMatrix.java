package com.example.mathematics;


/**
 * 1、顺时针打印矩阵：
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，如：
 * 1 2 3 4
 * <p>
 * 5 6 7 8
 * <p>
 * 9 10 11 12
 * <p>
 * 13 14 15 16
 * 输出如下：1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
 */
public class ClockWisePrintMatrix {

    public static int[][] ints = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20}
    };

    public static void main(String[] args) {
        int iMax = ints.length;
        int jMax = ints[0].length;
        int iMin = 0;
        int jMin = -1;

        int clockwise = 0; //四个方向 0。从左向右 1。从上打下 2.从右向左 3.从下向上
        int count = 0;
        int size = jMax * iMax;
        for (int i = 0; ; ) {
            for (int j = 0; ; ) {
                if(size == count) {
                    return;
                }
                if (clockwise == 0) {
                    if (j == jMax) {
                        i++;
                        j--;
                        jMax--;
                        clockwise = 1;
                        continue;
                    }
                    System.out.println(ints[i][j]);
                    count++;
                    j++;
                } else if (clockwise == 1) {
                    if (i == iMax) {
                        i--;
                        j--;
                        iMax--;
                        clockwise = 2;
                        continue;
                    }
                    System.out.println(ints[i][j]);
                    count++;
                    i++;
                } else if (clockwise == 2) {
                    if (j == jMin) {
                        i--;
                        j++;
                        jMin++;
                        clockwise = 3;
                        continue;
                    }
                    System.out.println(ints[i][j]);
                    count++;
                    j--;
                } else if (clockwise == 3) {
                    if (i == iMin) {
                        i++;
                        j++;
                        iMin++;
                        clockwise = 0;
                        continue;
                    }
                    System.out.println(ints[i][j]);
                    count++;
                    i--;
                }

            }
        }
    }
}
