/*
3. 无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。

示例 1:
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
  
示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 
提示：

0 <= s.length <= 5 * 104
s 由英文字母、数字、符号和空格组成
*/

// 该题用滑动窗口最好，这样可以把时间复杂度控制在O(n)，有多种方法实现滑动窗口，可以正常通过记录下标来实现滑动窗口，也可以用双端队列实现滑动窗口。
// 可以使用int[128]来统计是否出现过。然后通过左右侧边界来控制窗口大小

1、正常实现
    public static void main(String[] args) {
        String s = "aabaab!bb";
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String S){
        char[] s = S.toCharArray(); // 转换成 char[] 加快效率（忽略带来的空间消耗）
        int n = s.length;
        int ans = 0;
        int left = 0;
        int[] cnt = new int[128]; // 也可以用 HashMap<Character, Integer>，这里为了效率用的数组
        for (int right = 0; right < n; right++) {
            char c = s[right];
            cnt[c]++;
            while (cnt[c] > 1) { // 窗口内有重复字母
                cnt[s[left]]--; // 移除窗口左端点字母
                left++; // 缩小窗口
            }
            ans = Math.max(ans, right - left + 1); // 更新窗口长度最大值
        }
        return ans;
    }

2、双端队列

// 双端队列则是将未遍历过的字符加入队尾，若遇到遍历过的字符则从队头开始遍历依次出队。
    public static void main(String[] args) {
        String s = "aabaab!bb";
        System.out.println(lengthOfLongestSubstring(s));
    }
    
    public static int lengthOfLongestSubstring(String s){
       int res = 0;
       int max = 0;
       List<Character> list = new ArrayList<>();
       char[] chars = s.toCharArray();
       Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            // if (!list.contains(chars[i])){    // 若未遍历过则进入队尾
                list.add(chars[i]);      
                deque.addLast(chars[i]);
                max++;
                res = Math.max(max, res);
            }else {                              // 否则则从队头开始出队
                while (deque.peekFirst() != chars[i]){
                    char temp = deque.removeFirst();
                    list.remove(list.indexOf(temp));
                    max--;
                }

                deque.addLast(deque.removeFirst()); // 最后将当前值出队再入队
            }
        }


       return res;
    }
