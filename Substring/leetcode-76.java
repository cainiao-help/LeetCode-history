/*
76. 最小覆盖子串
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
注意：
对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
 
示例 1：
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
  
示例 2：
输入：s = "a", t = "a"
输出："a"
解释：整个字符串 s 是最小覆盖子串。

示例 3:
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串

提示：
m == s.length
n == t.length
1 <= m, n <= 105
s 和 t 由英文字母组成
*/

// 此题也是滑动窗口问题，属于最短型滑动窗口问题，首先统计给定的t中有多少字符，并用哈希表统计数量，然后再建立遍历表，统计出遍历过的元素，接着再定义左指针和右指针
// 右指针从左到右依次遍历字符串，若遍历到t中有的字母则遍历表的数量加一，若此时遍历表中的数量小于哈希表中的数量（说明当前的字符并没有超过目标数组的字符数量）
// 则将have值加一表示当前遍历的窗口中有have + 1个和目标字符串相同的，若此时遍历表中的数量大于等于哈希表中的数量（说明当前窗口中的字符数量已经超过目标数组中的字符数量）
// 此时只需要将遍历表中的对应的数加一，不需要将have的值加一，若have的值和目标字符串的长度相同则说明此时的窗口中包含了目标字符串的所有元素
// 此时左指针进入循环，若此时的窗口的总长度小于当前的窗口则将窗口设置为当前窗口，左指针从左到右遍历字符串，若当前遍历到的字符在遍历表中的数量大于哈希表中的数量
// 说明当前的窗口中仍包含多个字符，左指针可向右移动，继续循环，若当前遍历到的字符在遍历表中的数量小于等于哈希表中的数量，说明当前窗口中的字符数量不能再减，即跳出左指针循环


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t){
        String res = "";
        int min = s.length();                        // 设置最小窗口长度
        int have = 0;                                // 将当前窗口拥有目标字符串数量设置为0；
        int tcount = t.length();                     // 记录目标字符串的数量
        if (t.length() > s.length()){                // 若目标字符串数量大于当前字符串数量说明不可能得到包含目标字符串的窗口
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();// 建立哈希表，统计目标字符串中每个字符出现的数量
        for (int i = 0; i < t.length(); i++) {          
            if (map.containsKey(t.charAt(i))){
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            }else{
                map.put(t.charAt(i), 1);
            }
        }
        Map<Character, Integer> temp = new HashMap<>(); // 建立遍历数组，统计当前窗口中包含的目标字符串每个字符出现的数量
        for (int i = 0; i < t.length(); i++) {
            if (!temp.containsKey(t.charAt(i))){
                temp.put(t.charAt(i), 0);
            }
        }
        int left = 0;
        int right = 0;
        while (right < s.length()){      //右指针从左到右遍历目标字符串
            if (map.containsKey(s.charAt(right)) && map.get(s.charAt(right)) <= temp.get(s.charAt(right))){ // 若当前遍历到的字符在遍历表中的数量已经大于等于哈希表中的字符数量，则只将遍历表中的字符数量加一
                temp.put(s.charAt(right), temp.get(s.charAt(right)) + 1);
            }
            if (map.containsKey(s.charAt(right)) && map.get(s.charAt(right)) > temp.get(s.charAt(right))){  // 若当前遍历到的字符在遍历表中的数量仍小于哈希表中的字符数量，则需将have值加一，表示当前窗口拥有have + 1个目标字符串数量的字符，并将遍历表中的字符数量加一
                have++;
                temp.put(s.charAt(right), temp.get(s.charAt(right)) + 1);
            }


                while (have == tcount){      // 若当前窗口中已经拥有的目标字符串的数量等于目标字符串的总长度，则说明可以将左指针适当右移缩小窗口长度
                    if (min == s.length()){
                        res = s;
                    }
                    if (min > right - left + 1){
                        min = right - left + 1;
                        res = s.substring(left, right + 1);
                    }
                    if (map.containsKey(s.charAt(left)) && map.get(s.charAt(left)) >= temp.get(s.charAt(left))){ // 若当前遍历到的字符在遍历表中的数量小于等于哈希表中的数量，说明当前窗口中的字符数量不能再减，即跳出左指针循环
                        break;
                    }
                    if (map.containsKey(s.charAt(left)) && map.get(s.charAt(left)) < temp.get(s.charAt(left))){ // 若当前遍历到的字符在遍历表中的数量大于哈希表中的数量说明当前的窗口中仍包含多个字符，左指针可向右移动
                        temp.put(s.charAt(left), temp.get(s.charAt(left)) - 1);
                    }

                    left++;
                }
          
            right++;
        }
        return res;
    }
