/*
64. 最小路径和
给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。

示例 1：
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
  
示例 2：
输入：grid = [[1,2,3],[4,5,6]]
输出：12
  
提示：
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
*/

// 该题是多维动态规划，和leetcode-62题方法一致，首先建立一个动态数组来保存到达当前位置的最小值。牺牲空间换时间
// 由于每次只能向下移动或向右移动一步，所以先将第一行和第一列的最短路径计算出来，紧接着遍历余下位置，比较当前位置的左侧和上面位置的最短路径

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid){
        int res = 0;
        int[][] dp = new int[grid.length][grid[0].length];  // 建立动态数组来记录当前位置的路径最小值
        dp[0][0] = grid[0][0];                        // 将动态数组的第一个最短路径设置为原数组的值
        for (int i = 1; i < grid.length; i++) {       // 将第一列的最短路径计算出来
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {    // 将第一行的最短路径计算出来
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {       // 依次遍历余下数组
            int min ;
            for (int j = 1; j < grid[0].length; j++) {
                min = Math.min(grid[i][j] + dp[i][j-1], grid[i][j] + dp[i-1][j]);  // 比较当前位置的左侧动态数组的值和上面的动态数组的值来判断最小值
                dp[i][j] = min;
            }
        }

        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[grid.length-1][grid[0].length-1];
    }
