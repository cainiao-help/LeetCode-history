/*
416. 分割等和子集
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

示例 1：
输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。

示例 2：
输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。
*/

// 01背包问题的变形，动态规划，由于若被分成两个子集，则每个子集的元素和为总元素和的一半，所以首先要先需要建立一个数组，代表是否能找到当前元素
// 通过最后返回需要找的值是否为true来判断是否能分割

    public static void main(String[] args) {
        int[] nums = {1,2,5};
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums){
        int all = 0;
        for (int i = 0; i < nums.length; i++) {
            all = all + nums[i];                  // 先计算元素总和
        }
        if (all % 2 != 0){                        // 若元素总和不能被2整除就说明不能分成两个子集
            return false;
        }
        int target = all / 2;
        boolean[] res = new boolean[target + 1];  // 建立一个数组表示前元素和一半的数能否通过子集相加得到，若能则设置为true
        res[0] = true;
        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target){
                for (int j = res.length-1 ; j >= 0; j--) {    // 每次遍历到一个小于元素和一半的数时则将当前判断数组加上当前数的位置设置为true
                    if (res[j] && nums[i] + j < res.length){
                        res[nums[i] + j] = true;
                    }

                }
                res[nums[i]] = true;
            }

        }
        System.out.println(Arrays.toString(res));
        if (res[target]){
            return true;
        }else {
            return false;
        }

    }
