/*
33. 搜索旋转排序数组
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
示例 1：
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4

示例 2：
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1

示例 3：
输入：nums = [1], target = 0
输出：-1
 
提示：
1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums 中的每个值都 独一无二
题目数据保证 nums 在预先未知的某个下标上进行了旋转
-104 <= target <= 104
*/

// 由于数组是经过左移后的，所以可以将数组分为两个递增段，第一段的所有元素均大于第二段，只需要比较目标数和最后一个数的大小即可判断
// 若数组最后一个数小于目标数，则目标数在第一段元素中，反之则目标数在第二段元素中。
// 使用两次二分查找即可完成，第一次查找找到两段数据的分界点，也就是最小值。根据数组最后一个数和目标数比较的结果来完成第二次二分查找

   public static void main(String[] args) {
        int[] nums = {5,1,3};
        System.out.println(search(nums, 3));

    }

    public static int search(int[] nums, int target) {
        int res = -1;
        if (nums.length == 1){
            if (nums[0] == target){
                return 0;
            }else {
                return res;
            }
        }
        int edge = helper(nums, target);
        if ((nums[nums.length-1] >= target || edge == 0) && edge != nums.length - 1){
            int left = edge;
            int right = nums.length - 1;
            while (left <= right){
                int mid = (right - left) / 2 + left;
                if (nums[left] == target){
                    res = left;
                    break;
                }
                if (nums[mid] == target){
                    res = mid;
                    break;
                }
                if (nums[right] == target){
                    res = right;
                    break;
                }
                if (nums[mid] < target){
                    left = left + 1;
                }
                if (nums[mid] > target){
                    right = mid - 1;
                }

            }
        }else {
            int left = 0;
            int right = edge;
            while (left <= right){
                int mid = (right - left) / 2 + left;
                if (nums[left] == target){
                    res = left;
                    break;
                }
                if (nums[mid] == target){
                    res = mid;
                    break;
                }
                if (nums[right] == target){
                    res = right;
                    break;
                }
                if (nums[mid] < target){
                    left = left + 1;
                }
                if (nums[mid] > target){
                    right = mid - 1;
                }

            }
        }

        return res;
    }
    public static int helper(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (right - left) / 2 + left;
            if (nums[left] > nums[mid]){
                right = mid;
            }else {
                left = left + 1;
            }
        }
        return left;
    }
