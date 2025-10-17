/*
143. 重排链表
给定一个单链表 L 的头节点 head ，单链表 L 表示为：
L0 → L1 → … → Ln - 1 → Ln
请将其重新排列后变为：
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
  
示例 1：
输入：head = [1,2,3,4]
输出：[1,4,2,3]
  
示例 2：
输入：head = [1,2,3,4,5]
输出：[1,5,2,4,3]

提示：
链表的长度范围为 [1, 5 * 104]
1 <= node.val <= 1000
*/

// 该方法是使用双向队列将其全部链表存入，然后依次取出队列的第一个和最后一个，使用第一个链表指向最后一个链表。

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);
        reorderList(listNode);
    }

    public static void reorderList(ListNode head) {
        ListNode temp = head;
        Deque<ListNode> deque = new LinkedList<>();  // 设置双向链表
        while (temp != null){                        // 将全部节点都存入双向队列中
            deque.add(temp);
            temp = temp.next;
        }
        ListNode last = new ListNode();              // 设置一个链表来接收双向队列中取出的最后一个链表
        ListNode res = new ListNode(-1);
        temp = res;
        while (!deque.isEmpty()){
            ListNode first = deque.pollFirst();     // 设置一个链表来接收双向队列中取出的第一个链表
            first.next = null;
            temp.next = first;                      // 使用上一个末尾节点连接这次的头节点
            if (!deque.isEmpty()){                  // 队列不为空则取出双向队列中的最后一个链表
                last = deque.pollLast();            // 并将temp指针设置在末尾节点上
                last.next = null;
                temp = last;
            }else {
                last = null;
            }
            first.next = last;                       // 头节点指向尾节点

        }
    }
