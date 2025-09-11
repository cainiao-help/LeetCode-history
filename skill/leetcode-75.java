该代码可简化
/*
75. 颜色分类
给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
必须在不使用库内置的 sort 函数的情况下解决这个问题。

示例 1：
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
  
示例 2：
输入：nums = [2,0,1]
输出：[0,1,2]
 
提示：
n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2
*/

//不修改元素。只替换元素，该代码可简化，首先确认数组中各有0,1,2三种元素多少个，然后确定出，0,1,2三种元素的分界线。接着用双指针遍历数组。左指针依次从左到右，右指针从右向左交换。

    public static void main(String[] args) {
        int[] num = {2,0,2,1,1,0};
        sortColors(num);
    }

    public static void sortColors(int[] nums){
        int[] set = new int[3];                       // 创建三种元素的分界线统计表
        for (int i = 0; i < nums.length; i++) {       // 统计三种元素的数量
            if (nums[i] == 0){
                set[0]++;
            }
            if (nums[i] == 1){
                set[1]++;
            }
            if (nums[i] == 2){
                set[2]++;
            }
        }
        set[1] = set[1] + set[0];                     // 计算各个元素的分界线
        set[2] = set[1] + set[2];
        int left = 0;
        for (int i = 0; i < 3; i++) {                // 按元素依次遍历数组

            int right = nums.length-1;               
            while (left < set[i]){                   // 若左侧指针小于当前遍历的元素分界线则需进入循环
                if (nums[left] == i){                // 若左指针当前的值已经等于当前元素则左指针右移
                    left++;
                    continue;
                }
                if (nums[right] != i){               // 若右指针不等于当前元素则右指针左移 
                    right--;
                    continue;
                }
                if (nums[left] != i && nums[right] == i){  // 若当前左指针不等于当前元素且右指针等于当前元素则交换两个指针所对应的元素
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    left++;
                    right--;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }
