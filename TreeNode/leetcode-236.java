/*
236. 二叉树的最近公共祖先
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

示例 1：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
  
示例 2：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
  
示例 3：
输入：root = [1,2], p = 1, q = 2
输出：1
 
提示：
树中节点数目在范围 [2, 105] 内。
-109 <= Node.val <= 109
所有 Node.val 互不相同 。
p != q
p 和 q 均存在于给定的二叉树中。
*?
*/

// 首先该题要寻找公共祖先，有两种情况，第一种情况，给定的两个节点互为祖先节点，即一个节点为另一个节点的祖先，即返回两者中的一个为结果。
// 第二种情况是两个节点不是为祖先节点，即一个节点不是另一个节点的祖先节点，就需要从根节点来往下确定哪个是二者的最近的公共祖先

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(-1);
        treeNode.left = new TreeNode(0);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(-2);
        treeNode.left.right = new TreeNode(4);
        treeNode.left.left.left = new TreeNode(8);
        TreeNode one = treeNode.left.left.left;
        TreeNode two = treeNode.left.right;
        System.out.println(lowestCommonAncestor(treeNode, one, two));
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        TreeNode res = new TreeNode();
        if (helper(p, q)){      // 若p为q的祖先则返回p
            return p;
        }
        if (helper(q, p)){      // 若q为p的祖先则返回q
            return q;
        }
        return dbs(root, p, q, root);  // 不然就返回从根节点向下的最近公共祖先

    }

    public static boolean helper(TreeNode p, TreeNode q){  // 判断一个为另一个的祖先
        if (p == null){              // 如果遍历到空则返回false
            return false;
        }
        if (p == q){                 // 如果遍历到q则说明p为q的祖先，即q在p的孩子
            return true;
        }else {                      // 否则继续向下遍历
            if (helper(p.left, q)){  
                return true;
            }
            if (helper(p.right, q)){
                return true;
            }
            return false;            // 最终若未遍历到则返回false
        }
    }

    public static TreeNode dbs(TreeNode root, TreeNode p, TreeNode q, TreeNode res){  // 从根节点往下判断最近的公共祖先
        if (helper(root, p) && helper(root, q)){    // 若根节点既为p的祖先又为q的祖先，则可以将当前最近公共祖先修改为当前根节点并继续向下遍历
            res = root;
            res = dbs(root.left, p, q, res);
            res = dbs(root.right, p, q, res);
            return res;
        }else {                                      // 否则则将当前最近的公共祖先返回
            return res;
        }
    }
