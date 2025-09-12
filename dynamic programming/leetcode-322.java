/*
322. 零钱兑换
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。

示例 1：
输入：coins = [1, 2, 5], amount = 11
输出：3 
解释：11 = 5 + 5 + 1

示例 2：
输入：coins = [2], amount = 3
输出：-1

示例 3：
输入：coins = [1], amount = 0
输出：0
 
提示：
1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 104
*/

// 动态规划问题，该方法先建立和目标数加一相等的数组，代表从0-amount每个数的最小零钱数，首先将数组中零钱对应的数的位置设为1（即只需要一个零钱）
// 接着依次遍历数组，设置为当前数的最小零钱数，在遍历每个数时，依次遍历给出的零钱数，当前的最小值即为当前位置减去零钱处的最小值＋零钱的最小值（即1）


    public static void main(String[] args) {
        int[] num = {1};
        System.out.println(coinChange(num, 1));
    }

    public static int coinChange(int[] coins, int amount){
        int[] dp = new int[amount + 1];  // 建立从0-amount的数组
        Arrays.fill(dp, 100000);         // 将每个数填入较大值
        dp[0] = 0;                       // 若目标数为0，则返回0

        for (int i = 0; i < coins.length; i++) {
            long p = coins[i] ;                 // 由于硬币的最大值为int的最大值，要注意。若超出int会变为负数，因为java中整数在内存中使用的是补码的形式表示，最高位是符号位，0表示正数，1表示负数
            if (p < dp.length && p >= 0 ){      // int 类型最大值是 2147483647若加一则变成-2147483648
                dp[coins[i]] = 1;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {                   // 遍历硬币数组
                if (i - coins[j]  < 0 ){                               // 若当前数减去硬币数小于0，则继续遍历
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i-coins[j]] + dp[coins[j]]); // 选择当前位置的和当前位置减去硬币数的加1的最小值
            }
            if (dp[i] == 100000){                                       // 若当前位置还为原先设定的值，则将当前数设置为-1，即当前数不能由硬币组成
                dp[i] = -1;
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
