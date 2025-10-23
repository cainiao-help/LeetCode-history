/*
54. 螺旋矩阵
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

示例 2：
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]

提示：
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
*/

// 该题可以分别用四个数来记录当前矩阵的边界，然后每次推进都将边界值缩减，再判断是否对立边界是否相同（若相同则说明已经全部遍历过）


    public static void main(String[] args) {
        int[][] grid = {{1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12},
                        };
        System.out.println(spiralOrder(grid));
    }

    public static List<Integer> spiralOrder(int[][] matrix){   // 测试用
        List<Integer> res = new ArrayList<>();
        int x = matrix.length;
        int y = matrix[0].length;
        int up = 0;
        int down = matrix.length;
        int left = 0;
        int right = matrix[0].length;
        int curX = 0; // 设置当前遍历的行
        int curY = 0; // 设置当前遍历的列
        while (true){
            for (int i = left; i < right; i++) { // 从左到右遍历
                res.add(matrix[up][i]);
            }
            up++;                                // 更新上边界
            if (up >= down){
                break;
            }
            for (int i = up; i < down; i++) {   // 从上到下
                res.add(matrix[i][right - 1]);
                curX = i;
            }
            right--;                             // 更新右边界
            if (right <= left){
                break;
            }
            for (int i = right - 1; i >= left; i--) {  // 从右到左
                res.add(matrix[down - 1][i]);
                curY = i;
            }
            down--;                              // 更新下边界
            if (down <= up){    
                break;
            }
            for (int i = down - 1; i >= up ; i--) { // 从下到上
                res.add(matrix[i][left]);
                curX = i;

            }
            left++;                               // 更新左边界
            if (left >= right){
                break;
            }
        }

        return res;
    } 
