/*
295. 数据流的中位数
中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
例如 arr = [2,3,4] 的中位数是 3 。
例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
实现 MedianFinder 类:
MedianFinder() 初始化 MedianFinder 对象。
void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。

示例 1：
输入
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
输出
[null, null, null, 1.5, null, 2.0]

解释
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
提示:

-105 <= num <= 105
在调用 findMedian 之前，数据结构中至少有一个元素
最多 5 * 104 次调用 addNum 和 findMedian
*/

/*
比如现在有 6 个数：1,5,6,2,3,4，要计算中位数，可以把这 6 个数从小到大排序，得到 1,2,3,4,5,6，中间 3 和 4 的平均值 3.5 就是中位数。
中位数把这 6 个数均分成了左右两部分，一边是 left=[1,2,3]，另一边是 right=[4,5,6]。我们要计算的中位数，就来自 left 中的最大值，以及 right 中的最小值。
随着 addNum 不断地添加数字，我们需要：
保证 left 的大小和 right 的大小尽量相等。规定：在有奇数个数时，left 比 right 多 1 个数。
保证 left 的所有元素都小于等于 right 的所有元素。
只要时时刻刻满足以上两个要求（满足中位数的定义），我们就可以用 left 中的最大值以及 right 中的最小值计算中位数。

分类讨论：
如果当前 left 的大小和 right 的大小相等：
如果添加的数字 num 比较大，比如添加 7，那么把 7 加到 right 中。现在 left 比 right 少 1 个数，不符合前文的规定，所以必须把 right 的最小值从 right 中去掉，添加到 left 中。如此操作后，可以保证 left 的所有元素都小于等于 right 的所有元素。
如果添加的数字 num 比较小，比如添加 0，那么把 0 加到 left 中。
这两种情况可以合并：无论 num 是大是小，都可以先把 num 加到 right 中，然后把 right 的最小值从 right 中去掉，并添加到 left 中。
如果当前 left 比 right 多 1 个数：
如果添加的数字 num 比较大，比如添加 7，那么把 7 加到 right 中。
如果添加的数字 num 比较小，比如添加 0，那么把 0 加到 left 中。现在 left 比 right 多 2 个数，不符合前文的规定，所以必须把 left 的最大值从 left 中去掉，添加到 right 中。如此操作后，可以保证 left 的所有元素都小于等于 right 的所有元素。
这两种情况可以合并：无论 num 是大是小，都可以先把 num 加到 left 中，然后把 left 的最大值从 left 中去掉，并添加到 right 中。
最后，我们需要什么样的数据结构？这个数据结构要能高效地执行如下操作：

添加元素。
找到最大（小）值。
删除最大（小）值。
这个数据结构是堆。

left 是最大堆，right 是最小堆。

如果当前有奇数个元素，中位数是 left 的堆顶。
如果当前有偶数个元素，中位数是 left 的堆顶和 right 的堆顶的平均值。
*/

public class MedianFinder {
    private final PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b - a); // 最大堆
    private final PriorityQueue<Integer> right = new PriorityQueue<>(); // 最小堆

    public void addNum(int num) {
        if (left.size() == right.size()) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        }
        return (left.peek() + right.peek()) / 2.0;
    }
}
