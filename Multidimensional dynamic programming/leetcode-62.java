/*
62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
问总共有多少条不同的路径？

示例 1：
输入：m = 3, n = 7
输出：28

示例 2：
输入：m = 3, n = 2
输出：3

解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下

示例 3：
输入：m = 7, n = 3
输出：28

示例 4：
输入：m = 3, n = 3
输出：6
 
提示：
1 <= m, n <= 100
题目数据保证答案小于等于 2 * 109
*/

// 多维动态规划，首先确定状态，当前位置的不同路径由左边的路径和上面的路径决定，第一行和第一列的路径只有一个，因为该题只能向右或向下走
// 首先设置第一个位置（0,0）的位置为0，然后设置第一列和第一行的位置路径为1


    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }

    public static int uniquePaths(int m, int n){
        if (m == 1 || n == 1){   
            return 1;
        }
        int[][] dp = new int[m][n];         // 建立二维数组表示当前位置有多少不同的路径
        dp[0][0] = 0;
        for (int i = 1; i < m; i++) {       // 将第一列和第一行的值设置为1。
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];  // 当前位置由上面的路径和下左边的路径和相加确定
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[m-1][n-1];

    }
