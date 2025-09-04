/*
给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

示例 1：

输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
示例 2：

输入：s = "a"
输出：[["a"]]
 

提示：

1 <= s.length <= 16
s 仅由小写英文字母组成

*/

//思路如下：把问题转化为判断这串字符的每两个字符之间的逗号是否需要保留，并且保留后是否是回文串，这样就把一个大问题分解为多个子问题
class Solution {
    public List<List<String>> partition(String s) {
                List<List<String>> res = new ArrayList<>();
        if (s.length() == 0 ){
            return res;
        }
        if (s.length() == 1){
            String temp = new String(s);
            List<String> list = new ArrayList<>();
            list.add(temp);
            res.add(list);
            return res;
        }
        int number = 1;
        int cur = 0;
        String temp = null;
        List<String> list = new ArrayList<>();
        helper(res, s, number, cur, list);

        return res;

    }
    public static void helper(List<List<String>> res,String s, int number, int cur,List<String> strings){
        if (cur == s.length()){     //只有当前指针指向末尾时，才可以保存
            res.add(new ArrayList<>(strings));
            return;
        }
        if (cur + number > s.length()){  //若当前指针加上需要增加的字符数大于字符串总长度则返回
            return;
        }

        String temp = s.substring(cur,cur + number);  // 从当前指针分割字符串，数量为number
        int right = temp.length()-1;     //指向末尾
        int left = 0;                    //指向头部
        boolean is = true;                  
        while (left < right){            //若头部和末尾的字符相同则头指针加1，尾指针减1，直至两指针相遇，若遇到不同的则不是回文串，循环结束
            if (temp.charAt(left) != temp.charAt(right)){
                is = false;
                break;
            }
            left++;
            right--;
        }
        if (is){                        //若是回文串则加入集合，继续判断下一个字符是否是回文串。
            strings.add(temp);
            helper(res, s, 1, cur + number, strings );
            strings.remove(strings.size() - 1);     // 判断结束则将集合回溯至之前的空集合
        }
        helper(res, s, number + 1, cur, strings);   // 若上面字符串不是回文数判断字符数量加1，或者当上个数量的回文串判断结束后继续判断加1是否是回文串


    }
}


