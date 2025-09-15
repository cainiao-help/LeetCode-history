/*
152. 乘积最大子数组
给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
测试用例的答案是一个 32-位 整数。

示例 1:
输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。

示例 2:
输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 
提示:
1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
nums 的任何子数组的乘积都 保证 是一个 32-位 整数
*/

// 该题用动态规划来解决较快，牺牲空间换时间，由于会出现负值所以要建立两个动态数组，一个记录前n个最大子数组，一个记录前n个最小子数组
// 比较的时候比较三个值，一个是当前本身的值，一个是当前值乘以最大子数组上一个值，一个是当前值乘最小子数组上一个值

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        System.out.println(maxProduct(nums));
    }

    public static  int maxProduct(int[] nums){
        int[] dpMax = new int[nums.length];      // 建立最大子数组和最小子数组
        int[] dpMin = new int[nums.length];
        dpMax[0] = dpMin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(Math.max(nums[i], dpMax[i-1] * nums[i]), dpMin[i-1] * nums[i]); // 比较三个数的大小
            dpMin[i] = Math.min(Math.min(nums[i], dpMin[i-1] * nums[i]), dpMax[i-1] * nums[i]); 
        }
        System.out.println(Arrays.toString(dpMax));
        System.out.println(Arrays.toString(dpMin));
        int res = 0;
        for (int i = 0; i < dpMax.length; i++) {      // 遍历得到最大值
            res = Math.max(res, dpMax[i]);
        }
        return res;
    }
