该题仍可优化，贪心＋二分查找使得时间复杂度仅为O（nlogn）
/*
300. 最长递增子序列
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 
示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

示例 2：
输入：nums = [0,1,0,3,2,3]
输出：4

示例 3：
输入：nums = [7,7,7,7,7,7,7]
输出：1
 
提示：
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
*/

  // 该题我使用的方法为动态规划，倒序遍历dp。由于第一个值的最长递增子序列会受到后续数的影响所以考虑倒序遍历。因为最后一个数不会受到其他数的影响



      public static void main(String[] args) {
        int[] nums = {0,1,0,3,2,3};
        System.out.println(lengthOfLIS(nums));
    }

    public static  int lengthOfLIS(int[] nums){
        int res = 0;
        int[] dp = new int[nums.length];         // 建立给定数组中每个数的位置表，代表当前位置的数的最长递归子序列，初始化所有值为1，因为最少即为他们本身
        Arrays.fill(dp,1);                       
        for (int i = dp.length-2;i >= 0; i--) {  // 倒序遍历动态数组

            for (int j = i+1; j < nums.length; j++) { // 遍历目前数之后的数的位置表，因为当前位置的最长子序列由后续数的最长子序列决定
                if (nums[j] > nums[i]){               // 若后续数本身大于当前数，则只需要将后续数的最长子序列加1（即加上目前遍历到的数）
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        for (int i = 0; i < dp.length; i++) {      // 依次比较取出最长子序列值
            res = Math.max(res, dp[i]);
        }
        return res;
    }
