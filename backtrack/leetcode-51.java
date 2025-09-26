/*
51. N 皇后
n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

示例 1：
输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。
  
示例 2：
输入：n = 1
输出：[["Q"]]

提示：
1 <= n <= 9
*/

// 该题是经典的回溯问题，可以建立一个遍历表boolean[][]，这样所使用的空间较大且时间较长，所以可以观察数组发现当皇后位于一个位置时，该位置的斜线方向均不能有皇后
// 135°的斜线方向的x+y的值相同，45°斜线方向的y-x的值相同，例如若皇后位于[1][1]位置，则[i][j] i + j = 2 的135°斜线位置均不能有皇后，j - i = 0的45°斜线位置均不能有皇后
// 然后正常进行回溯即可

    public static void main(String[] args) {
        List<List<String>> res = solveNQueens(4);
        for (List<String> list : res) {
            System.out.println(list);
        }
    }

    public static  List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[] queens = new int[n]; // 皇后放在 (r,queens[r]) 
        boolean[] col = new boolean[n];            // 当前列是否有皇后
        boolean[] diag1 = new boolean[n * 2 - 1];  // 两条斜线是否有皇后，由于i+j的最大值是n - 1 + n - 1即为2n - 2，例如若n为4则最大值为3+3=6，加上0一共7个
        boolean[] diag2 = new boolean[n * 2 - 1];  // 由于j-i的最小值为 0 - （n - 1）即为1 - n，例如若n为4则最小值为 0 - 3 = -3，小于0，则都加上n-1使得所有值均大于等于0，此时最大值也为2n - 2
        dfs(0, queens, col, diag1, diag2, ans);    // 所以需要设置两个2n-1大小的数组空间来判断45°和135°上是否有n皇后
        return ans;
    }

    public static void dfs(int r, int[] queens, boolean[] col, boolean[] diag1, boolean[] diag2, List<List<String>> ans) {
        int n = col.length;
        if (r == n) {         // 如果当前的皇后数量和需要的数量相同则进行保存
            List<String> board = new ArrayList<>(n); // 预分配空间
            for (int c : queens) {   
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[c] = 'Q';
                board.add(new String(row));
            }
            ans.add(board);
            return;
        }
        // 在 (r,c) 放皇后
        for (int c = 0; c < n; c++) {
            int rc = r - c + n - 1;
            if (!col[c] && !diag1[r + c] && !diag2[rc]) { // 判断能否放皇后
                queens[r] = c; // 直接覆盖，无需恢复现场
                col[c] = diag1[r + c] = diag2[rc] = true; // 皇后占用了 c 列和两条斜线
                dfs(r + 1, queens, col, diag1, diag2, ans);
                col[c] = diag1[r + c] = diag2[rc] = false; // 恢复现场
            }
        }

    }
