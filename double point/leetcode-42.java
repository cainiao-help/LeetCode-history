/*
42. 接雨水
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
示例 1：
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 

示例 2：
输入：height = [4,2,0,3,2,5]
输出：9

提示：

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/

// 该题可使用双指针解决，下面方法用的是动态规划，分别记录左右的最大值。
// 若使用动态规划，则先建立两个数组，分别保存左侧和右侧的最大高度，若计算当前位置左侧最大高度，则需要从1开始依次遍历数组，当前位置的左侧最大高度由原数组上一个值和左侧最大动态数组的上一个值决定。
// 最终得到两个数组来表示当前位置的左侧和右侧的最大高度，若要计算能存多少水则由当前位置的左右最大高度的较小值决定，即用当前数减去当前位置左右最大高度的最小值就是当前位置能存多少水。


    public static void main(String[] args) {
        int[] sum = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(sum));

    }

    public static int trap(int[] height){
        int ans = 0;
        int[] maxl = new int[height.length];                        // 建立左侧最大高度的数组和右侧最大高度的数组来记录
        int[] maxr = new int[height.length];
        for (int i = 1; i < height.length; i++) {                   // 从1到末尾依次遍历数组，当前位置的左侧最大值由原数组前一个数和动态数组前一个数的最大值确定
            maxl[i] = Math.max(height[i-1], maxl[i - 1]);
        }
        for (int i = height.length - 2; i >= 0 ; i--) {            // 从n-1处倒序遍历原数组，当前位置的右侧最大值由原数组后一个数和动态数组后一个数的最大值确定
            maxr[i] = Math.max(height[i + 1], maxr[i + 1]);
        }
        for (int i = 0; i < height.length; i++) {                  // 依次遍历原数组，用当前位置的左右侧最大值的较小数决定能存水的数量，减去当前位置的值即为当前位置能否存水。
            if (Math.min(maxl[i], maxr[i]) - height[i] >= 0){
                ans = ans + Math.min(maxl[i], maxr[i]) - height[i];
            }
        }


        return ans;

    }
