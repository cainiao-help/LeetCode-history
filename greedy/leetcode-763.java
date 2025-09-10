/*
给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。

注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。

返回一个表示每个字符串片段的长度的列表。

 

示例 1：
输入：s = "ababcbacadefegdehijhklij"
输出：[9,7,8]
解释：
划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。 
示例 2：

输入：s = "eccbbbbdec"
输出：[10]
 

提示：

1 <= s.length <= 500
s 仅由小写英文字母组成
*/

// 自己用的方法
// 由于给出的字符串全是小写字母，所以考虑先建立一个字母表，然后依次遍历字符串，将遍历到的字符在字母表中加一
// 创建字符动态数组，依次遍历数组，若遍历到动态数组中没有的字符则加入动态数组中，将遍历到的字符在字母表中减一
// 由于最先遍历的字符加入在动态数组的最前面，所以判断动态数组第一个字符在字母表中次数是否为0，若为0则将第一个字符去掉。
// 若字符动态数组的数量为0，则将当前遍历到的位置加入结果数组中

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));

    }

    public static List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();             // 建立结果数组
        char[] chars = s.toCharArray();                    // 将字符串转换为字符数组
        int temp = 0;                                  
        int[] record = new int[26];                        // 建立字母表
        for (int i = 0; i < chars.length; i++) {
            record[chars[i] - 97]++;                       // 遍历字符数组，将遍历到的字符在对应的字母表中数量加一 
        }
        List<Character> list = new ArrayList<>();          // 建立动态字符数组，来确认遍历过且字符串中还有的字符
        for (int i = 0; i < chars.length; i++) {
            if (!list.contains(chars[i])){                 // 若动态字符数组中没有当前字符，则将其加入动态 字符数组
                list.add(chars[i]);              
            }
            record[chars[i] - 97]--;                       // 将遍历到的字符在字母表中的数量减一
            while (list.size() > 0 && record[list.get(0)-97] == 0 ){  // 若当前的动态字符数组的数量大于0且动态数组第一个字符在字母表中的数量为0则将当前第一个字符删去
                list.remove(0);                      
            }
            if (list.size() == 0){                        // 若当前的动态字符数组数量为0，则将当前遍历到的位置加入结果数组中
                if (res.size() == 0){                     // 若当前结果数组的数量为0，则直接将当前的位置加入结果数组
                    temp = i + 1;                         
                    res.add(temp);                        // 记录当前数组分割的位置
                }else {                                    
                    res.add(i+1 - temp);                  // 由于题目要求的是分割后的字符串数量并不是字符串位置，所以用当前位置减去上次分割数组的位置
                    temp = i + 1;
                }
            }
        }
        return res;
    }
