/*
15. 三数之和
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。

示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
  
示例 2：
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。
  
示例 3：
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。

提示：
3 <= nums.length <= 3000
-105 <= nums[i] <= 105
*/

// 对数组进行排序后，首先确认第一个数，然后后续后续两个数的和加上第一个数为0，则只需要用双指针降低复杂度
// 若第一个数大于0，则后续数均大于0，即不用再进行遍历，直接返回当前集合中的集合组

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> res = threeSum(nums);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);                        // 对给定的数组进行排序，方便找到后两个数
        for (int i = 0; i < nums.length; i++) {   // 依次遍历数组当做第一个数。
            if (nums[i] > 0){                     // 若第一个数大于0，则说明后续第二三个数都大于0，即结束遍历，返回当前结果集合
                break;
            }
            if (i > 0 && nums[i] == nums[i-1]){   // 若当前元素等于前一个元素则跳过，通过该方法来去重复
                continue;
            }

            int left = i + 1;                     // 设置左右指针，左指针为第一个元素加一  
            int right = nums.length - 1;          // 右指针为末尾
            int cur = nums[i];                    
            while (left < right ){
                List<Integer> list = new ArrayList<>();
                if (cur + nums[left] + nums[right] > 0 ){  // 若当前三个数大于0，则说明三数和偏大，右指针左移使得三数和减小
                    right--;
                    continue;
                }
                while (left < right && right < nums.length - 1 && nums[right] == nums[right + 1]){  // 若当前右指针的数等于右指针右边的数说明已经遍历过，跳过来去重
                    right--;
                }
                if (cur + nums[left] + nums[right] < 0 ){   // 若当前三个数小于0，则说明三数和偏小，左指针右移使得三数和增大
                    left++;
                    continue;
                }
                while (left < right && left > i + 1 && nums[left] == nums[left - 1]){              // 若当前左指针的数等于左指针左侧的数，说明已经遍历过，跳过来去重
                    left++;
                }
                if (cur + nums[left] + nums[right] == 0 && left != right){  // 若当前三个数等于0，则加入结果集合中，也可以用Arrays.asList(2,3,4)来添加
                    list.add(nums[i]);  
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left++;
                    right--;
                }

            }

        }
        return res;
    }

