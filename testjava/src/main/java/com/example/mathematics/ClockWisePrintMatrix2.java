package com.example.mathematics;


import java.util.ArrayList;

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
public class ClockWisePrintMatrix2 {

    public static int[][] ints = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20}
    };

    public static void main(String[] args) {
        ArrayList arrayList = new Solution().printMatrix(ints);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }

    public static class Solution {
        public ArrayList printMatrix(int[][] matrix) {
            ArrayList res = new ArrayList<>();//新建集合用来存储结果

            int startRow = 0; //左上角元素的行坐标

            int startCol = 0; //左上角元素的列坐标

            int endRow = matrix.length - 1; //右下角元素的行坐标

            int endCol = matrix[0].length - 1; //右下角元素的列坐标

            //这个while循环是左上角元素和右下角元素每次打印完最外圈之后向内推进一步，直到左上角元素和右下角元素走到相同位置。
            while (startRow <= endRow && startCol <= endCol) {
                //这里是调用方法打印最外层的矩阵元素保存到一个ArrayList集合中，每次执行完一次后将ArrayList整个添加到res集合中，用于最后返回结果。
                res.addAll(printMatrixClockwise(matrix, startRow++, startCol++, endRow--, endCol--));

            }
            return res; //将结果保存到List集合中，存入顺序和取出顺序一致。

        }

        //这个方法就是用来打印最外层的元素，保存到集合中
        public ArrayList printMatrixClockwise(int[][] m, int startRow, int startCol, int endRow, int endCol) {
            ArrayList temp = new ArrayList<>();//定义一个ArrayList用来临时存储打印的最外层的元素

            if (startRow == endRow) { //判断是否只有一行

                for (int i = startCol; i <= endCol; i++) { //就是一个一维数组，将所有元素放到temp集合中就行。

                    temp.add(m[startRow][i]);

                }

            } else if (startCol == endCol) { //只有一列的情况，和只有一行的情况相同

                for (int i = startRow; i <= endRow; i++) {
                    temp.add(m[i][startCol]);

                }

            } else { //不止一行一列，打印最外层。

                int curRow = startRow; //curRow 记录当前所在行

                int curCol = startCol; //curCol记录当前所在列

                while (curCol != endCol) { //如果不是最后一列，就从左向右打印，存到集合中。

                    temp.add(m[curRow][curCol++]);

                }

                while (curRow != endRow) { //如果不是最后一行就，从上向下打印，存到集合中。

                    temp.add(m[curRow++][curCol]);

                }

                while (curCol != startCol) { // 到了右下角的元素，要往左走，就是当前位置的列不是开始的列时就递减

                    temp.add(m[curRow][curCol--]);//将每个元素保存到集合中

                }

                while (curRow != startRow) { //到了左下角的元素位置，向上走，所以当前位置的行递减。

                    temp.add(m[curRow--][curCol]);//将每个元素保存到集合中

                }

            }

            return temp; //打印完最外层，保存到集合中，将集合返回。

        }

    }
}
