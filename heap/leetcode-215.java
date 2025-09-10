/*
215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5
  
示例 2:
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
 
提示：
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
*/

// 快速选择方法 ：以数组某个元素（一般选取首元素）为基准数，将所有小于基准数的元素移动至其左边，大于基准数的元素移动至其右边。
// 对 左子数组 和 右子数组 递归执行 哨兵划分，直至子数组长度为 1 时终止递归，即可完成对整个数组的排序。
// 然而，对于包含大量重复元素的数组，每轮的哨兵划分都可能将数组划分为长度为 1 和 n−1 的两个部分，这种情况下快速排序的时间复杂度会退化至 O(N^2);
// 可以使用「三路划分」，即每轮将数组划分为三个部分：小于、等于和大于基准数的所有元素。这样当发现第 k 大数字处在“等于基准数”的子数组中时，便可以直接返回该元素。

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        System.out.println(findKthLargest(nums, 2));


    }

    public static int findKthLargest(int[] nums, int k) {
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        return quickSelect(numsList, k);
    }

    public static int quickSelect(List<Integer> nums, int k){
        // 随机选择基准
        Random random = new Random();
        int pivot = nums.get(random.nextInt(nums.size()));
        // 将大于、小于、等于pivot的元素划分至big, small,equal中
        List<Integer> big = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        for (Integer num : nums) {
            if (num > pivot){
                big.add(num);
            }
            else if (num < pivot){
                small.add(num);
            }
            else
                equal.add(num);
        }
        // 第 k 大元素在 big 中，递归划分
        if (k <= big.size()){
            return quickSelect(big, k);
        }
        // 第 k 大元素在 small 中，递归划分
        if (nums.size() - small.size() < k){
            return quickSelect(small, k - nums.size() + small.size());
        }
        // 第 k 大元素在 equal 中，递归划分
        return pivot;
    }
