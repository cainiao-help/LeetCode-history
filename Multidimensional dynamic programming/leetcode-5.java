/*
5. 最长回文子串
给你一个字符串 s，找到 s 中最长的 回文 子串。

示例 1：
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。

示例 2：
输入：s = "cbbd"
输出："bb"

提示：

1 <= s.length <= 1000
s 仅由数字和英文字母组成
*/

// 该题力扣官方标记的是多维动态数组，但目前理解的是技巧类的题
// 该题可用中心扩展法来做，分别列举以字符串第一个为中心字母，第二个为中心字母直到最后一个为中心字母分别向两边扩展
// 若左右的字母相同则继续向两侧扩展
// 上面的方法适用于回文串为奇数的，若为偶数则应该一次选定两个字母，从两个字母向两侧扩展。

    public static void main(String[] args) {
        String s = "nnzfgcqlbdpgxlcrtqomliphnlehgrzgwufwsyeqqzrlhzbc";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s){
        String res = s.substring(0,1);                        // 将结果设置为第一个字母
        int max = 1;                                          // 将回文串字母数设置为1
        for (int i = 0; i < s.length(); i++) {
            int left = i;                                     // 从一个字母中间往两边遍历
            int right = i;
            while (left >= 0 && right  < s.length() && s.charAt(left) == s.charAt(right)){

                String temp1 = s.substring(left, right + 1);  // shubstring方法包左不包右，所以要加一。
                if (max < right - left + 1){                  // 若当前的回文字符串个数大于当前最大的回文字符串个数则
                        max = right - left + 1;
                        res = temp1;
                    }
                left--;
                right++;
            }

            int l = i;                                        // 从两个字母往两边遍历
            int r = i + 1;
            if (r >= s.length()){                             // 若大于字符串总长则继续循环
                continue;
            }
            String temp2 = s.substring(l, r + 1);             

                while (l  >= 0 && r  < s.length() && s.charAt(l) == s.charAt(r)){   // 如果两个字母相等则进入循环
                    temp2 = s.substring(l, r + 1);
                    if (max < r - l + 1){
                        max = r - l + 1;
                        res = temp2;
                    }
                    l--;
                    r++;
           }
        }

        return res;
    }
