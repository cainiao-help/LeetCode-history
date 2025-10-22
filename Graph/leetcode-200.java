/*
200. 岛屿数量
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。

示例 1：
输入：grid = [
  ['1','1','1','1','0'],
  ['1','1','0','1','0'],
  ['1','1','0','0','0'],
  ['0','0','0','0','0']
]
输出：1
示例 2：

输入：grid = [
  ['1','1','0','0','0'],
  ['1','1','0','0','0'],
  ['0','0','1','0','0'],
  ['0','0','0','1','1']
]
输出：3
 
提示：
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] 的值为 '0' 或 '1'
*/

// 该题可以新建一个数组代表是否遍历过，也可以直接修改当前数组的值来代表是否走过，以下方法采用新建数组来代表是否遍历过
// 也就是说只需要先发现一座岛就将当前岛上所有区域都设置为走过，然后继续遍历整个二维数组

    public static void main(String[] args) {      // 测试用
        char[][] grid = {{'1','1','1','1','0'},
                         {'1','1','0','1','0'},
                         {'1','1','0','0','0'},
                         {'0','0','0','0','0'}};
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid){
        int res = 0;
        boolean[][] is = new boolean[grid.length][grid[0].length];  // 建立新的数组代表是否走过该岛
        for (int i = 0; i < grid.length; i++) {                     // 遍历二维数组来确定有几个岛
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && is[i][j] == false){        // 如果当前是新的岛并且未走过则将岛上所有陆地都设为走过
                    res++;
                    helper(grid, is, i, j);
                }
            }
        }

        return res;
    }

    public static void helper(char[][] grid, boolean[][] is, int i, int j){
        if (i < 0 || i > grid.length || j < 0 || j > grid[0].length || grid[i][j] != '1' || is[i][j] == true){  // 设置递归边界出口
            return;
        }
        is[i][j] = true;                 // 将当前位置记录为走过并以依次遍历周围上下左右二维数组
        helper(grid, is, i - 1, j);
        helper(grid, is, i + 1, j);
        helper(grid, is, i, j - 1);
        helper(grid, is, i, j + 1);
    }
