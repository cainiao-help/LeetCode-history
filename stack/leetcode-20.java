/*
20. 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。
 
示例 1：
输入：s = "()"
输出：true

示例 2：
输入：s = "()[]{}"
输出：true

示例 3：
输入：s = "(]"
输出：false

示例 4：
输入：s = "([])"
输出：true

示例 5：
输入：s = "([)]"
输出：false

提示：
1 <= s.length <= 104
s 仅由括号 '()[]{}' 组成
*/

// 该题可以直接用栈来解决，栈先入后出特点恰好与本题括号排序特点一致，即若遇到左括号入栈，遇到右括号时将对应栈顶左括号出栈，则遍历完所有括号后 stack 仍然为空；


    public static void main(String[] args) { // 测试用
        String s = "()[]{}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s){
       boolean res = true;
       Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '['){  // 遇到左括号则入栈
                stack.add(s.charAt(i));
            }
            if (s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']' ){ // 遇到右括号则与栈顶元素对比
                if (stack.isEmpty()){     // 若栈顶无元素则说明无效括号
                    res = false;
                    break;
                }
                if (s.charAt(i) == ')' && stack.pop() != '('){  // 若栈顶左括号不匹配则说明为无效括号
                    res = false;
                    break;
                }
                if (s.charAt(i) == '}' && stack.pop() != '{'){  
                    res = false;
                    break;
                }
                if (s.charAt(i) == ']' && stack.pop() != '['){
                    res = false;
                    break;
                }
            }
        }
        if (!stack.isEmpty()){    // 如果栈中还有元素则也说明无效括号
            res = false;
        }

       return res;
    }
