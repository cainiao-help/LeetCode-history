/*
92. 反转链表 II
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 
示例 1：
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
  
示例 2：
输入：head = [5], left = 1, right = 1
输出：[5]

提示：
链表中节点数目为 n
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n

进阶： 你可以使用一趟扫描完成反转吗
*/

// 该题很像翻转链表I，也需要头结点来作为中间值，要不然若全部翻转最后返回的不是第一个节点而是最后一个节点。只不过该题要先让临时指针指向开始排序的位置

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        System.out.println(reverseBetween(listNode, 2, 4));
    }
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (right == left){
            return head;
        }
        int all = right - left;
        int count = 0;
        int cur = 1;
        ListNode res = new ListNode(-1);
        res.next = head;
        ListNode temp = res;
        while (cur <= left - 1){      // 指向开始排序的位置
            temp = temp.next;
            cur++;
        }
        ListNode pre = temp;
        ListNode last = new ListNode();
        temp = temp.next;
        ListNode first = temp;
        ListNode listNode1 = temp.next;
        pre.next = null;
        temp.next = null;
        while (count < all && listNode1 != null){
            ListNode listNode2 = listNode1.next;
            listNode1.next = temp;
            count++;
            if (count == all){
                last = listNode1;
            }
            temp = listNode1;
            listNode1 = listNode2;
        }
        pre.next = last;
        if (listNode1!= null){
            first.next = listNode1;
        }
        return res.next;
