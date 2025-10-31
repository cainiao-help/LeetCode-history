/*
415. 字符串相加
给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。

示例 1：
输入：num1 = "11", num2 = "123"
输出："134"
  
示例 2：
输入：num1 = "456", num2 = "77"
输出："533"
  
示例 3：
输入：num1 = "0", num2 = "0"
输出："0"
 
提示：
1 <= num1.length, num2.length <= 104
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
*/

// 本题我们只需要对两个大整数模拟「竖式加法」的过程。竖式加法就是我们平常学习生活中常用的对两个整数相加的方法，回想一下我们在纸上对两个整数相加的操作，是不是如下图将相同数位对齐，从低到高逐位相加，
// 如果当前位和超过 10，则向高位进一位？因此我们只要将这个过程用代码写出来即可。具体实现也不复杂，我们定义两个指针 i 和 j 分别指向 num 1  和 num 2  的末尾，即最低位，同时定义一个变量 add 维护当前是否有进位，
// 然后从末尾到开头逐位相加即可。你可能会想两个数字位数不同怎么处理，这里我们统一在指针当前下标处于负数的时候返回 0，等价于对位数较短的数字进行了补零操作，这样就可以除去两个数字位数不同情况的处理，具体可以看下面的代码。

// 重点是StringBuilder或Stringbuffer的使用，要掌握，还有字符变成数字只需要减去字符 '0' .
    public static void main(String[] args) {
        String num1 = "99";
        String num2 = "9";
        System.out.println(addStrings(num1, num2));
    }


    public static String addStrings(String num1, String num2){
        StringBuilder stringBuilder = new StringBuilder();
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        int add = 0;
        if (n1 < n2){
            String res = addStrings(num2, num1);
            return res;
        }
        while (n1 >= 0 && n2 >= 0){
            int temp = num1.charAt(n1) - '0' + num2.charAt(n2) - '0';
            int last = (temp + add) % 10;
            stringBuilder.append(last);
            add = (temp + add) / 10;
            n1--;
            n2--;
        }
        while (n1 >= 0){
            int temp = num1.charAt(n1) - '0';
            int last = (temp + add) % 10;
            stringBuilder.append(last);
            add = (temp + add) / 10;
            n1--;
        }
        if (add != 0){
            stringBuilder.append(add);
        }
        return stringBuilder.reverse().toString();
    }
