/*
34. 在排序数组中查找元素的第一个和最后一个位置
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]
*/
//因为数组是有序排列的，所以可以使用二分查找，但是不查找这个数，二分查找区间，比如比这个数大的下标，以及比这个数小的下标，两个下标就是出现的第一个位置和最后一个位置

package com.test;


import java.util.*;
import java.util.stream.Collectors;

public class hot {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));

    }

    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0){
            res[0] = res[1] = -1;
            return res;
        }

        int left = helper(nums, target);      // 二分查找是否有目标值，若无目标值则说明没有比目标值更小的数，调用的的函数中right会缩小至0
        if (left == nums.length || left != target){
            res[0] = res[1] = -1;
            return res;
        }
        int right = helper(nums,  target + 1) - 1;

        return res;
    }
    //    函数返回最小的满足 nums[i] >= target 的下标 i
    //    如果数组为空，或者所有数都 < target，则返回 nums.length
    //    要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
    public static int helper(int[] nums, int target){
        int left = -1;
        int right = nums.length;
        while (left + 1 < right){
            // num[left] < target
            // num[right] >= target
            int mid = (left + right) / 2;
            if (nums[mid] < target){
                left = mid ;
            }else {
                right = mid;
            }
        }
        // 循环结束后left + 1 = right
        // 此时nums[left] < target , nums[right] >= target
        // 所以此时right就是大于等于target的第一个下标
        return right;
    }
}




