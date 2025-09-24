/*
41. 缺失的第一个正数
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 
示例 1：
输入：nums = [1,2,0]
输出：3
解释：范围 [1,2] 中的数字都在数组中。
  
示例 2：
输入：nums = [3,4,-1,1]
输出：2
解释：1 在数组中，但 2 没有。
  
示例 3：
输入：nums = [7,8,9,11,12]
输出：1
解释：最小的正数 1 没有出现。
 
提示：
1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
*/

// 该题要求实现复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。若无这项要求可进行如下操作：将结果设为1，sort排序然后从头开始遍历数组，若遍历到和res值相等的值则将res值加一，最后返回即可，但sort排序本质是快速排序时间已经超了
// 可以考虑“换座位的解决方法”，只需要两次遍历数组，第一次遍历数组需要将当前遍历的数排序到他应该在的位置上，比如：若当前的数组是【0,1,4,3】，则最终排序结束后的数组应该为【1,0,3,4】，也就是将每个元素换到其i-1的位置
// （将1换到nums[0]的位置,将3换到nums[2]的位置），若该数小于0或该数大于数组长度则不进行操作。

    public static void main(String[] args) {
        int[] sum = {1,1};
        System.out.println(firstMissingPositive(sum));
    }

    public static int firstMissingPositive(int[] nums){
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i+1 && nums[i] < nums.length && nums[i] > 0 && nums[nums[i] - 1] != nums[i] ){   // 若当前的数满足大于0且小于数组长度说明该数在该数组中有一个位置，仅需判断该位置上是否已经坐上了一个数，若遍历到nums[2]的数为4即nums[num[2]-1]处是否已经有了一个数
                int temp = nums[nums[i] - 1];                                          // 该处要循环的目的主要是为了防止换过来的数还可以再换，比如说nums[] = {0,4,2,1},若将4换到1的位置上，则1仍可以和0进行交换，最后排序结束后的数组是【1,2,0,4】
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {              // 第二次遍历数组只需要比较当前位置的值是否和res值相同，若相同则加一。最后返回res
            if (res == nums[i]){
                res++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return res;
    }
