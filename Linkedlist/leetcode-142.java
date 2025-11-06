/*
142. 环形链表 II
给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
不允许修改 链表。

示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
  
示例 2：
输入：head = [1,2], pos = 0
输出：返回索引为 0 的链表节点
解释：链表中有一个环，其尾部连接到第一个节点。
  
示例 3：
输入：head = [1], pos = -1
输出：返回 null
解释：链表中没有环。
 
提示：
链表中节点的数目范围在范围 [0, 104] 内
-105 <= Node.val <= 105
pos 的值为 -1 或者链表中的一个有效索引

进阶：你是否可以使用 O(1) 空间解决此题
*/

// 假设进环前的路程为 a，环长为 b。设慢指针走了 x 步时，快慢指针相遇，此时快指针走了 2x步。显然 2x-x=nb（快指针比慢指针多走了 n 圈），
// 即 x=nb。也就是说慢指针总共走过的路程是 nb，但这 nb 当中，实际上包含了进环前的一个小 a，因此慢指针在环中只走了 nb-a 步，它还得再往前走 a 步，
// 才是完整的 n 圈。所以，我们让头节点和慢指针同时往前走，当他俩相遇时，就走过了最后这 a 步。

    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(0);
        listNode.next.next.next = new ListNode(-4);
        listNode.next.next.next.next = listNode.next;
        detectCycle(listNode);
    }

    public static ListNode detectCycle(ListNode head){
        ListNode res = null;        // 设置返回的链表
        ListNode slow = head;       // 设置快慢指针
        ListNode fast = head;
        while (fast != null && fast.next != null){  
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){    // 快慢指针相遇时,同时让慢指针和头节点后移，相遇的地方就是环形链表入口
                while (slow != head){    
                    slow = slow.next;
                    head = head.next;
                }
                res = slow;
                break;
            }
        }
        return res;
    }
