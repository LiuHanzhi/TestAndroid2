package com.example.mathematics.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 4. 寻找两个正序数组的中位数
 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。



 示例 1：

 输入：nums1 = [1,3], nums2 = [2]
 输出：2.00000
 解释：合并数组 = [1,2,3] ，中位数 2
 示例 2：

 输入：nums1 = [1,2], nums2 = [3,4]
 输出：2.50000
 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 示例 3：

 输入：nums1 = [0,0], nums2 = [0,0]
 输出：0.00000
 示例 4：

 输入：nums1 = [], nums2 = [1]
 输出：1.00000
 示例 5：

 输入：nums1 = [2], nums2 = []
 输出：2.00000


 提示：

 nums1.length == m
 nums2.length == n
 0 <= m <= 1000
 0 <= n <= 1000
 1 <= m + n <= 2000
 -106 <= nums1[i], nums2[i] <= 106


 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？

 通过次数444,403提交次数1,099,376
 */
class Solution4 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int index1 = 0, index2 = 0;
        while (index1 < nums1.length || index2 < nums2.length) {
            if (index1 >= nums1.length) {
                list.add(nums2[index2]);
                index2++;
            } else if (index2 >= nums2.length) {
                list.add(nums1[index1]);
                index1++;
            } else {
                int num1 = nums1[index1];
                int num2 = nums2[index2];
                System.out.println("num1:" + num1 + ",num2:" + num2);
                if (num1 > num2) {
                    list.add(num2);
                    index2++;
                } else {
                    list.add(num1);
                    index1++;
                }
            }

        }
        if (list.size() == 0) {
            return 0d;
        }
        if (list.size() % 2 == 0) {
            return (list.get(list.size() / 2) + list.get(list.size()/2 - 1)) * 1d / 2;
        } else {
            return list.get(list.size() / 2);
        }
    }
}