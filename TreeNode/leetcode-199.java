/*
199. 二叉树的右视图
给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例 1：
输入：root = [1,2,3,null,5,null,4]
输出：[1,3,4]
解释：
  
示例 2：
输入：root = [1,2,3,4,null,null,null,5]
输出：[1,3,4,5]
解释：
  
示例 3：
输入：root = [1,null,3]
输出：[1,3]
  
示例 4：
输入：root = []
输出：[]


提示:
二叉树的节点个数的范围是 [0,100]
-100 <= Node.val <= 100 
*/

// 感觉是变种的层序遍历，只不过每层记录的是最右侧的那个节点的值，执行广度优先搜索，右结点排在左结点之前，
// 这样，我们对每一层出队时，只需要将第一个记录在结果集合中即可。其他都是层序遍历的做法。

    public static void main(String[] args) {      // 测试用
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(5);
        treeNode.right = new TreeNode(3);
        treeNode.right.right = new TreeNode(4);
        System.out.println(rightSideView(treeNode));
    }

    public static List<Integer> rightSideView(TreeNode root){
        List<Integer> res = new ArrayList<>();        // 设置结果集合
        Queue<TreeNode> queue = new LinkedList<>();   // 设置队列
        if (root != null){                            // 如果给定的二叉树不为空就将节点入队
            queue.add(root);
        }
        while (!queue.isEmpty()){      
            int n = queue.size();                     // 统计当前队列中的个数
            for (int i = 0; i < n; i++) {             // 依次让这层的节点出队并将下层节点入队
                TreeNode temp = queue.poll();        
                if (i == 0){                          // 将第一个节点值入队
                    res.add(temp.val);
                }
                if (temp.right != null){              // 先入队右节点确保右侧在第一个，下一次第一个出队
                    queue.add(temp.right);
                }
                if (temp.left != null){
                    queue.add(temp.left);
                }
            }
        }
        return res;    // 返回结果集合
    }
