/*
239. 滑动窗口最大值
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。

示例 1：
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 
示例 2：
输入：nums = [1], k = 1
输出：[1]

提示：
1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
*/

// 该题虽然是滑动窗口问题但用队列解决更好，总而言之就是建立一种能记录当前k个元素的最大值并及时将窗口遍历过的元素弹出。
// 首先建立一个队列，若在未形成窗口前，依次遍历至k到窗口形成，若队列不为空且当前遍历到的元素大于队列的末尾元素则末尾元素弹出。这样保证当前窗口最大值在最前面，稍小的在后面。后续若窗口向后移动则只需将最前面的元素弹出


    public static void main(String[] args) {
        int[] sum = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow(sum, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k){
        if(nums.length == 0 || k == 0)
            return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for(int i = 0; i < k; i++) {                              // 若在未形成窗口前，依次遍历至k到窗口形成，若队列不为空且当前遍历到的元素大于队列的末尾元素则末尾元素弹出。
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();                               // 将当前第一个元素（即最大值）设置为第一个窗口的最大值
        // 形成窗口后
        for(int i = k; i < nums.length; i++) {                    // 窗口依次向后移动
            if(deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
