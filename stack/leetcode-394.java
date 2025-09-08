/*
394. 字符串解码
给定一个经过编码的字符串，返回它解码后的字符串。
编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
测试用例保证输出的长度不会超过 105。
示例 1：
输入：s = "3[a]2[bc]"
输出："aaabcbc"
  
示例 2：
输入：s = "3[a2[c]]"
输出："accaccacc"
  
示例 3：
输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"
  
示例 4：
输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"
 
提示：
1 <= s.length <= 30
s 由小写英文字母、数字和方括号 '[]' 组成
s 保证是一个 有效 的输入。
s 中所有整数的取值范围为 [1, 300] 
*/

// 看到括号用栈解决，变量字符串，如果遇到的不是 ']' 则将当前元素压入栈中，若遇到 ']',则需要将元素弹出栈直到遇到 '[',同时记录所弹出的元素
// 遇到'['则停止，同时判断栈顶元素是否为数字且栈是否为空，若为数字则记录数字并弹出，直至遇到不是数字
// 根据记录的数字来生成多个先前弹出的元素，最后将生成的总元素压入栈中
// 最后依次弹出栈直至栈为空，拼接字符串得到最终结果

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s) {
        Stack stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ']'){
                stack.add(s.charAt(i));
            }else {
                String temp = "";
                while (stack.peek().toString().charAt(0) != '['){
                    String cur = String.valueOf(stack.pop());
                    temp = cur + temp;
                }
                stack.pop();
                String num = "";
                while (!stack.isEmpty() && Character.isDigit(stack.peek().toString().charAt(0))){
                    num = stack.peek() + num;
                    stack.pop();
                }
                String str = "";
                int cnt = Integer.parseInt(num);
                while (cnt != 0){
                    str = str + temp;
                    cnt --;
                }
                stack.add(str);
            }

        }
        String res = "";
        while (!stack.isEmpty()){
            res = stack.pop() + res;
        }
        return res;
    }
