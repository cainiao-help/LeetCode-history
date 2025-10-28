/*
56. 合并区间

以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
示例 1：
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
  
示例 2：
输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
  
示例 3：
输入：intervals = [[4,7],[1,4]]
输出：[[1,7]]
解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。

提示：
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
*/

// 该题要正常进行合并需要O（n^2）的时间复杂度，可以先按区间第一个数组排序数组，然后只需要比较区间第一个值的大小即可
// 如{{1,3},{2,6},{8,10},{15,18}},已经按区间第一个数值进行排序，只需要比较前一个数的最大区间和后一个数的最小区间即可，
// 例如{1,3}和{2,6}，只需要比较3 > 2就可以判断二者区间可以合并，只需要将大的区间写入3的位置即可

    public static void main(String[] args) {
        int[][] a = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = merge(a);
        for (int[] ints : res) {
            System.out.println(Arrays.toString(ints));
        }
    }


    public static int[][] merge(int[][] intervals){
        Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] a = intervals[i];
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j][0] <= a[1]){
                    if (a[1] <= intervals[j][1]){
                        a[1] = intervals[j][1];
                    }
                }else {
                    break;
                }
                i = j;
            }
            list.add(a);
        }

        return list.toArray(new int[list.size()][]);
    }
