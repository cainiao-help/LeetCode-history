/*
102. 二叉树的层序遍历
给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 
示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[9,20],[15,7]]
  
示例 2：
输入：root = [1]
输出：[[1]]
  
示例 3：
输入：root = []
输出：[]

提示：
树中节点数目在范围 [0, 2000] 内
-1000 <= Node.val <= 1000
*/

// 该题要层序遍历二叉树，就是保存每一层的数
// 可以先用队列保存第一层的值，记录此时队列长度，然后循环出队，若左右节点有不为空的就加入队列中

    public static void main(String[] args) {  // 测试用
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        List<List<Integer>> res = levelOrder(treeNode);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();  // 设置最终返回的结果集合
        Queue<TreeNode> queue = new LinkedList<>();   // 设置队列
        if (root != null){                            // 若二叉树不为空则将第一层的节点放入队列
            queue.add(root);
        }
        while (!queue.isEmpty()){
            int n = queue.size();                     // 统计此时的队列长度（即这一层的节点数量）
            List<Integer> list = new ArrayList<>();   // 设置这一层的集合
            for (int i = 0; i < n; i++) {
                TreeNode treeNode = queue.poll();     // 依次出队，若当前节点的左右节点不为空则加入队尾
                list.add(treeNode.val);
                if (treeNode.left != null){          
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null){
                    queue.add(treeNode.right);
                }
            }
            res.add(list);                            // 将该层的集合加入结果集合
        }
        return res;

    }
