/*
347. 前 K 个高频元素
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
示例 1：
输入：nums = [1,1,1,2,2,3], k = 2
输出：[1,2]

示例 2：
输入：nums = [1], k = 1
输出：[1]

示例 3：
输入：nums = [1,2,1,2,1,2,3,1,3,2], k = 2
输出：[1,2]

提示：
1 <= nums.length <= 105
k 的取值范围是 [1, 数组中不相同的元素的个数]
题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
*/

// 第一步：答案与元素的出现次数有关，我们首先用一个哈希表 cnt 统计每个元素的出现次数。哈希表的 key 是元素值，value 是 key 在数组中的出现次数。
// 第二步：设出现次数最大值为 maxCnt，由于 maxCnt≤n，我们可以用桶排序，把出现次数相同的元素，放到同一个桶中。
// 创建一个大小为 maxCnt+1 的列表 buckets，其中 buckets[c] 存储出现次数为 c 的元素。（每个 buckets[c] 都是一个列表）遍历 cnt，把出现次数为 c 的元素 x 添加到 buckets[c] 中。
// 倒序遍历 buckets，把 buckets[c] 中的元素加到答案中。一旦答案的长度等于 k，就立刻返回答案

public static void main(String[] args) {
        int[] nums = {5,3,1,1,1,3,73,1};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));

    }

    public static int[] topKFrequent(int[] nums, int k) {
        // 第一步：统计每个数字出现的次数（用 HashMap 存：数字 -> 次数）
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }

        // 第二步：找出现次数的最大值（用来确定桶的大小）
        int mxCnt = 0;
        for (int cnt : map.values()) {
            mxCnt = Math.max(mxCnt,cnt);
        }

        // 第三步：创建“桶数组”（索引 = 出现次数，值 = 该次数对应的所有数字）
        // 比如：次数=3的数字都放在 buckets[3] 里
        List<Integer>[] buckets = new ArrayList[mxCnt + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        // 把数字按出现次数放进对应的桶里
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();       // 数字
            int cnt = entry.getValue();   // 出现次数
            buckets[cnt].add(num);        // 放进次数对应的桶
        }

        // 第四步：倒序遍历桶（从次数最多的桶开始），收集前 k 个数字
        int[] ans = new int[k];
        int idx = 0; // 结果数组的当前位置
        // 从最大次数桶往最小次数桶遍历
        for (int i = mxCnt; i >= 0; i--) {
            // 遍历当前桶里的所有数字（都是出现次数为 i 的数字）
            for (int num : buckets[i]) {
                // 把数字放进结果数组，直到收集够 k 个
                ans[idx++] = num;
                // 如果已经收集了 k 个，直接返回结果
                if (idx == k) return ans;
            }
        }
        return ans;
    }
