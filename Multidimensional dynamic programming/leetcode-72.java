/*
72. 编辑距离
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。

你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符

示例 1：
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

示例 2：
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

提示：
0 <= word1.length, word2.length <= 500
word1 和 word2 由小写英文字母组成
*/

// 该题也是两个字符串的问题，该方法还是用了二维数组来进行动态规划，将大问题拆解为小问题并将结果保存
// 建立二维数组dp[m][n],m和n为第一个字符串和第二个字符串的长度，位置的含义如下：例dp[i][j]表示第一个字符串的前i-1个字母需要多少步操作变为第二个字符串的前j-1个字母
// 当前位置的字母有两种情况，一：相等，即第i-1个字母和第j-1个字母相等，则该位置的最少操作数即为dp[i-1][j-1]位置的操作数，因为当前位置不需要改变字母
// 二：不相等，即第i-1个字母和第j-1个字母不相等，则当前位置的最少操作数由dp[i-1][j],dp[i][j-1],dp[i-1][j-1]三个位置的最小值加一确定。


    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(minDistance(word1, word2));
    }

    public static int minDistance(String word1, String word2){
        int m = word1.length();                      // 确定第一个字符串和第二个字符串的长度，若长度等于0则返回另一个的长度。即删除另一个字符串
        int n = word2.length();
        if (m == 0){
            return n;
        }
        if (n == 0){
            return m;
        }
        int[][] dp = new int[m][n];                   // 建立二维数组来保存当前位置需要的最小操作数
        if (word1.charAt(0) == word2.charAt(0)){      // 第一个字母若相等则将dp[0][0]位置设为0，反之设为1
            dp[0][0] = 0;
        }else {
            dp[0][0] = 1;
        }
        for (int i = 1; i < m; i++) {                 // 特殊情况，只有第二个字符串的第一个字母依次和前i个字母确定最小操作数
            if (word1.charAt(i) == word2.charAt(0)){  // 若第一个字符串当前位置的字母和第二个字符串的第一个字母相等则当前最小操作数即为删去前i-1个值，操作数为i
                dp[i][0] = i;
            }else {
                dp[i][0] = dp[i-1][0] + 1;
            }
        }
        for (int i = 1; i < n; i++) {
            if (word1.charAt(0) == word2.charAt(i)){
                dp[0][i] = i;
            }else {
                dp[0][i] = dp[0][i-1] + 1;
            }
        }
        for (int i = 1; i < m; i++) {                  // 依次遍历二维数组，当前位置的字母有两种情况，一：相等，即第i-1个字母和第j-1个字母相等，则该位置的最少操作数即为dp[i-1][j-1]位置的操作数，因为当前位置不需要改变字母
            for (int j = 1; j < n; j++) {              //  二：不相等，即第i-1个字母和第j-1个字母不相等，则当前位置的最少操作数由dp[i-1][j],dp[i][j-1],dp[i-1][j-1]三个位置的最小值加一确定。
                if (word1.charAt(i) == word2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    int min = Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1);
                    min = Math.min(min, dp[i-1][j-1] + 1);
                    dp[i][j] = min;
                }
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }

        return dp[m-1][n-1];
    }
