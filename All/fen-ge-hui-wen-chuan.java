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


