/*
739. 每日温度
给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
示例 1:
输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]

示例 2:
输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]

示例 3:
输入: temperatures = [30,60,90]
输出: [1,1,0]
 
提示：
1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
*/


//该题用栈来解决（注意压入栈中的是元素下标而不是元素本身），从左到右遍历数组，若栈顶所对应的下标值小于等于当前指向的值则压入栈中，确保压入栈中的元素是非增的
//否则开始遍历栈顶元素，若栈顶元素对应的下标值大于当前指向的值则出栈并在对应位置填入值，并继续遍历栈顶元素，最后再把当前元素下标压入栈中

    public static void main(String[] args) {
        int[] sums = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures(sums)));

    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];         // 初始化结果数组
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty()){    // 若栈为空则将当前元素下标压入栈中
                stack.add(i);       
                continue;
            }
            if (!stack.isEmpty() && temperatures[stack.peek()] >= temperatures[i] ){  // 如果栈顶所对应的下标值小于等于指向的值则将指向元素的下标压入栈中
                stack.add(i);
            }else {
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i] ){  //否则则开始遍历栈顶元素
                    res[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.add(i);
            }
        }


        return res;
