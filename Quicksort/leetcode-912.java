/*
912. 排序数组
给你一个整数数组 nums，请你将该数组升序排列。
你必须在 不使用任何内置函数 的情况下解决问题，时间复杂度为 O(nlog(n))，并且空间复杂度尽可能小。

示例 1：
输入：nums = [5,2,3,1]
输出：[1,2,3,5]
解释：数组排序后，某些数字的位置没有改变（例如，2 和 3），而其他数字的位置发生了改变（例如，1 和 5）。
  
示例 2：
输入：nums = [5,1,1,2,0,0]
输出：[0,0,1,1,2,5]
解释：请注意，nums 的值不一定唯一。

提示：
1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104
*/

有多种方法，一是常规方法（即左右指针）进行分区，二是利用快慢指针进行分区

// 该题是使用快速排序完成，首先是最常见的分区方法，选定一个基准元素，然后从右侧进行遍历，若右侧当前值大于等于基准元素则右指针左移，直到找到当前值小于基准元素或者到达最左边，然后将右侧值放到左侧指针位置上
// 接着开始遍历左指针，若左指针的元素小于等于基准元素，则左指针右移，直到找到左指针元素大于基准元素或者到达最后，将左指针元素放到右侧指针位置上

    public static void main(String[] args) {
        int[] nums = {110, 100, 0};
        System.out.println(Arrays.toString(sortArray(nums)));
    }

    public static int[] sortArray(int[] nums) {
        helper(nums, 0, nums.length - 1);

        return nums;
    }

    public static void helper(int[] nums, int left, int right){
        if (left >= right || left < 0 || right < 0){        // 若当前左指针大于等于右指针，或者左右指针小于0则返回，否则进入函数
            return;
        }
        int i = left;                                       // 设置左右指针位置。
        int j = right;
        int target = nums[i];                               // 设置基准元素
        while (i < j){                                      // 若左指针小于右指针则继续循环
            while (i < j && nums[j] >= target){             // 当左指针小于右指针且右指针元素大于等于基准元素则右指针左移直到找到小于基准元素的值或者到最左侧
                j --;
            }
            nums[i] = nums[j];                              // 将右指针元素移到左指针位置
            while (i < j && nums[i] <= target){             // 当左指针小于右指针且左指针元素小于等于基准元素则左指针右移直到找到大于基准元素的值或者到最右侧
                i++;
            }
            nums[j] = nums[i];

        }
        nums[i] = target;
        helper(nums, left, i - 1);                          // 递归最终位置左侧
        helper(nums, i + 1, right);                         // 递归最终位置右侧
    }
