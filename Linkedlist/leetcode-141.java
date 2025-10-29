/*
141. 环形链表
给你一个链表的头节点 head ，判断链表中是否有环。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
如果链表中存在环 ，则返回 true 。 否则，返回 false 。
  
示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
  
示例 2：
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
  
示例 3：
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。

提示：
链表中节点的数目范围是 [0, 104]
-105 <= Node.val <= 105
pos 为 -1 或者链表中的一个 有效索引 。
 
进阶：你能用 O(1)（即，常量）内存解决此问题吗
*/

// 要判断链表中是否有环，可以使用快慢指针来判断


    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(0);
        node.next.next.next = new ListNode(-4);
        node.next.next.next.next = node.next;
        System.out.println(hasCycle(node));
    }


    public static boolean hasCycle(ListNode head){
        boolean res = false;
        ListNode low = head;      // 设置慢指针
        ListNode fast = head;     // 设置快指针
        while (low != null && fast != null && fast.next != null){   // 若当前慢指针当前不为null，快指针当前不为null，快指针下一个链表也不为null，则进入循环
            low = low.next;          // 慢指针一次遍历一位
            fast = fast.next.next;   // 快指针一次遍历两位
            if (low == fast){        // 若快指针和慢指针相遇，则说明有环，设置结果为true并跳出循环
                res = true;
                break;
            }
        }

        return res;
    }
