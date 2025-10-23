/*
46. 全排列
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

示例 1：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

示例 2：
输入：nums = [0,1]
输出：[[0,1],[1,0]]

示例 3：
输入：nums = [1]
输出：[[1]]

提示：
1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同
*/


// 该题要求输出全排列数组，只能通过全部枚举，枚举出所有的排列，这就要用到递归
// 递归的边界条件为集合的元素个数等于数组的大小，因此需要用到回溯


    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> res = permute(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();  // 设置结果集合

        for (int i = 0; i < nums.length; i++) {       // 依次遍历数组
            List<Integer> temp = new ArrayList<>();   
            temp.add(nums[i]);                        // 从第一个位置开始遍历进入循环，这步实际来说可省略
            helper(nums, temp, res);
        }

        return res;
    }

    public static void helper(int[] nums, List<Integer> temp, List<List<Integer>> res){
        if (temp.size() == nums.length){              // 设置递归出口，
            List<Integer> p = new ArrayList<>(temp);  // 复制当前数组，要不然会出bug，导致存储的结果数组消失，因为下面回溯的时候清除了临时数组
            res.add(p);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!temp.contains(nums[i])){
                temp.add(nums[i]);
                helper(nums, temp, res);
                temp.remove(temp.size() - 1);          // 回溯清除新加入的数
            }
        }
    }
