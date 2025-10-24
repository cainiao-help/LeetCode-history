/*
82. 删除排序链表中的重复元素 II
给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。

示例 1：
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]

示例 2：
输入：head = [1,1,1,2,3]
输出：[2,3]
 

提示：
链表中节点数目在范围 [0, 300] 内
-100 <= Node.val <= 100
题目数据保证链表已经按升序 排列

*/

// 该题可能前面都相同，所以要设置一个链表头节点，将头节点的next指针指向当前链表，然后再用两层循环确保没有重复元素

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(2);

        System.out.println(deleteDuplicates(listNode));
    }

    public static ListNode deleteDuplicates(ListNode head){

        ListNode res = new ListNode(-1);
        ListNode temp = res;
        res.next = head;
        while (temp.next != null && temp.next.next != null){    // 若头节点的下一链表节点和下下个链表节点不为空，即始终保持
            int val = temp.next.val;
            if (temp.next.next.val == val){                // 后两个节点值相同
                 // 值等于 val 的节点全部删除
                while (temp.next != null && temp.next.val == val){
                    temp.next = temp.next.next;
                }
            }else {
                temp = temp.next;
            }
        }

        return res.next;
    }
