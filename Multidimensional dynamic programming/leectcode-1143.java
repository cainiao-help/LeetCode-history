/*
1143. 最长公共子序列
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

示例 1：
输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace" ，它的长度为 3 。

示例 2：
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。

示例 3：
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0 。

提示：
1 <= text1.length, text2.length <= 1000
text1 和 text2 仅由小写英文字符组成
*/

// 两个字符串可以选择用多维动态数组，建立一个dp[m][n],m和n分别为两个字符串的长度，具体的例子如下dp[i][j]代表了若选取第一字符串的第0至i-1个字母，
// 选取第二个字符串的第0至j-1个字母的最长公共子序列，当前的最长公共子序列由以下因素决定，第i-1和第j-1的字母是否相等，若相等则说明选取了当前两个位置的字母
// 则当前前一位的最长子序列加一即可确定当前位置的最长公共子序列，即和dp[i-1][j-1]的位置的值有关，若第i-1和第j-1的字母不相等，则说明未选取当前位置的两个元素
// 则当前位置的最长公共子序列由dp[i-1][j]和dp[i][j-1]的位置的值确定

    public static void main(String[] args) {
        String s = "abcde";
        String x = "ace";
        System.out.println(longestCommonSubsequence(s, x));
    }

    public static int longestCommonSubsequence(String text1, String text2){
        int m = text1.length();                      // 确认两个字符串的长度
        int n = text2.length();  
        int[][] dp = new int[m][n];                  // 由两个长度来建立一个二维数组来保存当前位置的最长公共子序列
        if (text1.charAt(0) == text2.charAt(0)){     // 若两个字符串的第一个字母相同则将第dp[0][0]个位置设置为1
            dp[0][0] = 1;
        }
        for (int i = 1; i < m; i++) {                 // 将特殊情况列出，即只让第二个字符串的第一个字母逐次与第一个字符串的前i个字母确认最长公共子序列
            if (text1.charAt(i) == text2.charAt(0)){  // 若当前与第一个字母一致则将当前位置设为1，否则等于前一位的位置的值
                dp[i][0] = 1;
            }else {
                dp[i][0] = dp[i-1][0];
            }
        }
        for (int i = 1; i < n; i++) {
            if (text1.charAt(0) == text2.charAt(i)){
                dp[0][i] = 1;
            }else {
                dp[0][i] = dp[0][i-1];
            }
        }
        for (int i = 1; i < m; i++) {                  // 从dp[1][1]位置依次遍历二维数组，若当前第一个字符串的第i-1个字母和第第二个字符串的第j-1的字母相等
            for (int j = 1; j < n; j++) {              // 则dp[i][j]位置的值由dp[i-1][j-1]的位置加一确定，若当前位置的字母不相等则由dp[i-1][j]和dp[i][j-1]的位置最大值确定
                if (text1.charAt(i) == text2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }

        return dp[m-1][n-1];
    }
