/*
79. 单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
示例 1：
输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
输出：true

示例 2：
输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE"
输出：true

示例 3：
输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCB"
输出：false
 
提示：
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board 和 word 仅由大小写英文字母组成
*/

// 该题要搜索网格内是否有字符串单词，是回溯问题，由于同一个单元格内的字母不能重复使用，所以首先设立一个和网格同样大的网格判断是否已经访问过，
// 接着，找到是否有单词的第一个字母，若没有则直接返回，若有则运用递归，设置边界条件，递归遍历四周是否能和单词的下一个字母对应。

public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(exist(board, "ABCB"));

    }

    public static   boolean exist(char[][] board, String word) {
        char[] cur = new char[word.length()];
        char[] target = word.toCharArray();  // 将寻找的字符串变为单个字符数组
        int sum = 0; 
        boolean[][] isOrNot = new boolean[board.length][board[0].length]; // 设立一个和网格同样大的布尔网格判断是否已经遍历过
        boolean[] res = new boolean[1];   // 这是为了防止力扣上判题按上一个来判（即若使用static boolean res 会导致他为静态变量，会使力扣传入的一组字符串结果一致，也可以说是力扣传入一串字符串并不会刷新静态变量）
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == target[sum] && isOrNot[i][j] != true){
                    helper(board, cur, target, i, j, word, sum, isOrNot, res); // 若寻找到目标字符串的第一个字母则开始递归网格四周的字母
                }
            }
        }
        return res[0];
    }

    public static void helper(char[][] board,char[] cur, char[] target , int x, int y, String word, int sum, boolean[][] isOrNot, boolean[] res){
        if (sum == word.length()){  // 若目前遍历的总数和目标字符串的长度相等，即为找到，将结果设置为true
            res[0] = true;
        }
        if (x >= board.length || y >= board[0].length || x < 0 || y < 0 || isOrNot[x][y] == true){  
            return;                                             // 设置边界条件，若x，y的值超过网格边界则返回，若当前数组已经被遍历过（即布尔网格数组当前为true），返回
        }

        if (sum < target.length && board[x][y] == target[sum]){ // 若遍历总数小于目标字符串的长度且当前遍历的字母和目标字符的字母相等，则
            isOrNot[x][y] = true;                               // 把当前布尔数组设置为true代表已经访问过
            helper(board, cur, target, x+1, y, word, sum+1, isOrNot,res);  // 依次上下左右遍历网格，并将遍历总数sum加1

            helper(board, cur, target, x-1, y, word, sum+1, isOrNot, res);

            helper(board, cur, target, x, y-1, word, sum+1, isOrNot, res);

            helper(board, cur, target, x, y+1, word, sum+1, isOrNot, res);

            isOrNot[x][y] = false;                              // 遍历结束后将当前的布尔网格恢复原状，以利于下一个字母遍历，
                                                                // 作用是可能有两个匹配第一个字母，但第一个四周并无剩余的字母，但第二个有。
        }
        return;
    }
