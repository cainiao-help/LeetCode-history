/*
103. 二叉树的锯齿形层序遍历
给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[20,9],[15,7]]
  
示例 2：
输入：root = [1]
输出：[[1]]
  
示例 3：
输入：root = []
输出：[]
 
提示：
树中节点数目在范围 [0, 2000] 内
-100 <= Node.val <= 100
*/

// 该题很像二叉树层序遍历，若直接使用队列实现二叉树层序遍历并不能实现题目要求的效果，所以既可以选择翻转队列，也可以选择使用双端队列
// 以下方法选择使用双端队列


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        List<List<Integer>> res = zigzagLevelOrder(treeNode);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();   // 初始化双端队列
        if (root != null){                    // 若二叉树头节点不为空则加入双端队列中
            queue.addLast(root);
        }
        int count = 0;                        // 判断当前需要从左到右还是从右到左层序遍历二叉树，0代表从左到右，1代表从右到左
        while (!queue.isEmpty()){
            int n = queue.size();             // 记录当前队列中的个数，即当前层的二叉树节点个数
            List<Integer> list = new ArrayList<>();  
            if (count == 0){
                for (int i = 0; i < n; i++) {
                    TreeNode temp = queue.pollLast();  // 从队列尾部依次出队
                    list.add(temp.val);
                    if (temp.left != null){            // 先使左节点入队，再使右节点入队
                        queue.addFirst(temp.left);
                    }
                    if (temp.right != null){
                        queue.addFirst(temp.right);
                    }
                }
                res.add(list);
                count++;                                // 下次从右到左
            }else {
                for (int i = 0; i < n; i++) {
                    TreeNode temp = queue.pollFirst();  // 从队列头部依次出队
                    list.add(temp.val);
                    if (temp.right != null){            // 先使右节点入队，再使左节点入队
                        queue.addLast(temp.right);
                    }
                    if (temp.left != null){
                        queue.addLast(temp.left);
                    }
                }
                res.add(list);
                count = 0;                              // 下次从左到右
            }
        }

        return res;
    }
