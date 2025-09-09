/*
45. 跳跃游戏 II
给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
0 <= j <= nums[i] 且
i + j < n
返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
示例 1:
输入: nums = [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

示例 2:
输入: nums = [2,3,0,1,4]
输出: 2 

提示:
1 <= nums.length <= 104
0 <= nums[i] <= 1000
题目保证可以到达 n - 1

*/

//贪心算法，取区间最大值。

    public static void main(String[] args) {
        int[] nums = {4,1,1,3,1,1,1};
        System.out.println(jump(nums));


    }

    public static int jump(int[] nums) {

        int time = 0;                  // 设置跳的次数
        int cur = 0;                   // 设置当前的位置
        while (cur < nums.length) {    // 若当前的位置小于长度则进入循环
            if (cur + nums[cur] >= nums.length-1) {  // 如果当前位置加上当前位置的跳跃距离大于长度则跳出循环
                if (cur < nums.length-1){            // 如果当前的位置在没加上跳跃的距离小于长度则还需要跳一下，若已经大于则不需要跳
                    time++;
                }
                break;
            }
            int max = 0;               // 设置当前能跳的格子里最大距离。
            int temp = 0;              // 设置当前的位置跳多少格到达最大距离
            for (int i = nums[cur]; i > 0; i--) {   // 遍历当前位置能跳的格子所能跳的最大距离，即若当前能跳1，2格则比较第2格的跳的距离和第一格跳的距离大小
                if (max < nums[cur + i]+i-1) {      // 若大于当前的最大距离则将最大值设为该值并将当前位置跳的格数修改。
                    max = nums[cur + i]+i-1;
                    temp = i;
                }
            }
            time++;                     // 循环结束跳跃次数加一
            cur = cur + temp;           // 将当前位置设置为跳跃后的位置
        }
        return time;
    }
