/*
4. 寻找两个正序数组的中位数
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
算法的时间复杂度应该为 O(log (m+n)) 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

提示：
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
*/
// 参考华南溜达虎,若不交换长数组和短数组则会出现bug
// 该题要想使时间复杂度满足要求就需要使用二分查找。把该问题想成一个绳子，绳子两端是nums1和nums2的边界，左边界是指当前绳子的端点，右边界则是当前数组的左侧均在绳子上
// 将问题转化为仅需满足nums1的左边界小于nums2的右边界，nums的左边界小于nums1的右边界即可，求出一段绳子边界即可求出是、另一段，只需要用总的绳子长度减去一段绳子即可

    public static void main(String[] args) {
        int[] sum1 = {2};
        int[] sum2 = {};
        System.out.println(findMedianSortedArrays(sum1, sum2));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length){                       // 确保当前的nums1的的长度始终是最短的，要不然会出bug。导致边界错误
            double res = findMedianSortedArrays(nums2, nums1);
            return res;
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0;          // 设置当前段绳子的左边界为第一个数组的长度
        int right = m;
        while (left <= right){
            int i = (left + right) / 2;    // 第一段的绳子边界为0 - i,第二段的绳子边界就为绳子总长度减去当前段的长度
            int j = (m + n + 1) / 2 - i;   // 
            int left1 = (i == 0 ) ? Integer.MIN_VALUE : nums1[i - 1];// 设置第一段绳子左边界和右边界
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int left2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];// 设置第二段绳子左边界和右边界
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];
            if (left1 <= right2 && left2 <= right1){      // 若当前绳子满足要求则按奇偶数返回结果
                if ((m + n) % 2 != 0){
                    return Math.max(left1, left2) * 1.0;
                }
                return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
            }else if (left1 > right2){     // 若当前段的左边界大于第二段的右边界则需要将当前段的右边界左移，反之左边界右移
                right = i - 1;
            }else {
                left = i + 1;
            }
        }
        return 0;
    }
