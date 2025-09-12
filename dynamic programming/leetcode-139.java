/*
139. 单词拆分
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。

示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     注意，你可以重复使用字典中的单词。
     
示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false

提示：
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s 和 wordDict[i] 仅由小写英文字母组成
wordDict 中的所有字符串 互不相同
*/

// 该题使用动态规划，建立一个字符串数组，填个前n个字母，比如若给出的值为“apple” ，则字符串数组的值依次为{“a”，“ap”，“app”，……，“apple”}
// 接着建立一个判断字符串能否被已有的字典表示，若能被字典表示则字符串对应数组更新为1

    public static void main(String[] args) {
        String s = "a";
        List<String> list = new ArrayList<>();
        list.add("a");

        System.out.println(wordBreak(s, list));
    }

    public static  boolean wordBreak(String s, List<String> wordDict){
        char[] chars = s.toCharArray();               // 将给定的字符串转化为字符数组
        String[] res = new String[s.length()];        // 建立一个和给定字符串一样大小的字符串数组村粗字符串
        res[0] = s.substring(0,1);                    
        int[] dp = new int[res.length];

        for (int i = 1; i < dp.length; i++) {          // 依次将字符串加入字符串数组中
            res[i] = res[i-1] + chars[i];
            if (wordDict.contains(res[i])){
                dp[i] = 1;
            }
        }
        for (int i = 0; i < dp.length; i++) {          // 依次遍历字符串数组
            for (int j = i - 1; j >= 0; j--) {         // 遍历当前字符串对应的之前的判断数组
                if (dp[j] == 1){                       // 若当前判断数组为1则表示前j个字符可以由字典中的字符串表示
                    String temp = s.substring(j+1,i+1);// 将前j个字符串裁剪掉，保留后面的字符串，判断剩余字符串能否由字典中的字符串表示，若能则将对应判断数组的值设置为1，并跳出循环 
                    System.out.println(temp);          
                    if (wordDict.contains(temp)){     
                        dp[i] = 1;
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(dp));
        if (dp[dp.length-1] == 1){
            return true;
        }else {
            return false;
        }
    }
