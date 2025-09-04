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
        if (cur == s.length()){
            res.add(new ArrayList<>(strings));
            return;
        }
        if (cur + number > s.length()){
            return;
        }

        String temp = s.substring(cur,cur + number);
        int right = temp.length()-1;
        int left = 0;
        boolean is = true;
        while (left < right){
            if (temp.charAt(left) != temp.charAt(right)){
                is = false;
                break;
            }
            left++;
            right--;
        }
        if (is){
            strings.add(temp);
            helper(res, s, 1, cur + number, strings );
            strings.remove(strings.size() - 1);
        }
        helper(res, s, number + 1, cur, strings);


    }
}

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
