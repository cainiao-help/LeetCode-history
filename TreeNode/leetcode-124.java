/*
124. 二叉树中的最大路径和
二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
路径和 是路径中各节点值的总和。
给你一个二叉树的根节点 root ，返回其 最大路径和 。

示例 1：
输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6

示例 2：

输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 
提示：
树中节点数目范围是 [1, 3 * 104]
-1000 <= Node.val <= 1000
*/

// 该题首先要明确计算方法，将大问题变成多个子问题进行问题分解，找到最大路径的和即为找到当前节点左子树的最大路径和和右子树的最大路径和，见fig文件下的图片
// 若不设置全局变量，则该题可以依次求出以当前节点为根节点的最大路径和用用链表记录，最后再比较得出最大值

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.right = new TreeNode(3);
        treeNode.right.right.right = new TreeNode(4);
        treeNode.right.right.right.right = new TreeNode(5);
        System.out.println(maxPathSum(treeNode));
    }

    public static  int maxPathSum(TreeNode root) {
        int res = Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();    // 建立链表记录当前节点的最大路径和
        helper(root, list);                        // 进入递归
        for (int i = 0; i < list.size(); i++) {    // 遍历得到最大值
            res = Math.max(res, list.get(i));
        }
        System.out.println(list);
        return res;

    }
    public static int helper(TreeNode root, List<Integer> list){
        if (root == null){                // 设置递归出口，若当前递归至边界则返回0
            return 0;
        }
        int maxl = helper(root.left, list); // 记录左子树的最大路径和
        int maxr = helper(root.right, list);// 记录右子树的最大路径和
        list.add(maxl + maxr + root.val);   // 将当前节点加上左子树和右子树最大路径和即为以当前节点为根节点的最大路径和
        return Math.max(Math.max(Math.max(root.val, 0),maxl + root.val),maxr + root.val);   // 比较并1.返回左子树＋当前节点2.右子树+当前节点3.当前节点4.0的最大值
    }
