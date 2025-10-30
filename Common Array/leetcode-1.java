/*
1. 两数之和
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
你可以按任意顺序返回答案。

示例 1：
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
  
示例 2：
输入：nums = [3,2,4], target = 6
输出：[1,2]
  
示例 3：
输入：nums = [3,3], target = 6
输出：[0,1]
 
提示：
2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
只会存在一个有效答案
 
进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
*/

// 由于该题要返回下标，且并不是排序好的数组，所以不能先对数组进行排序，然后使用双指针从两侧遍历。可以使用map集合来保存遍历过的数组下标
// 然后将当前遍历的数放入结果数组中，计算出target - 当前数得到有没有需要的数，若有则加入结果数组并返回。



    public static void main(String[] args) {
        int[] nums = {3,2,4};
        System.out.println(Arrays.toString(twoSum(nums, 6)));
    }


    public static int[] twoSum(int[] nums, int target){
        int[] res = new int[2];                        // 设置结果数组
        Map<Integer, Integer> map = new HashMap<>();   // 设置遍历过的map集合
        for (int i = 0; i < nums.length; i++) {        // 遍历数组
            res[0] = i;                                // 将当前值下标存入结果数组
            if (map.containsKey(target - nums[i])) {   // 若存在和当前数组相加等于target则将其下标加入结果数组
                res[1] = map.get(target - nums[i]);    
                break;
            }
            map.put(nums[i], i);                       // 将当前值下标存入map集合
        }
        return res;
    }

// 以下为两次遍历，效果较差


    public static void main(String[] args) {
        int[] nums = {3,2,4};
        System.out.println(Arrays.toString(twoSum(nums, 6)));
    }


    public static int[] twoSum(int[] nums, int target){
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int m = target - nums[i];
            if (map.containsKey(m) && map.get(m) != i){
                res[0] = i;
                res[1] = map.get(m);
                return res;
            }
        }


        return res;
    }
