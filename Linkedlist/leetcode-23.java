/*
23. 合并 K 个升序链表
请你将所有链表合并到一个升序链表中，返回合并后的链表。

示例 1：
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
  
示例 2：
输入：lists = []
输出：[]
  
示例 3：
输入：lists = [[]]
输出：[]
 
提示：

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按 升序 排列
lists[i].length 的总和不超过 10^4
*/

// 该题可以拆解为合并为两个链表的循坏，也就是依次合并链表数组中的链表


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(5);
        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);
        ListNode listNode3 = new ListNode(2);
        listNode3.next = new ListNode(6);
        ListNode[] listNodes ={listNode1,listNode2,listNode3};
        System.out.println(mergeKLists(listNodes));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = new ListNode(-1);
        for (int i = 0; i < lists.length; i++) {
            ListNode listNode = helper(ans.next, lists[i]);
            ans.next = listNode;
        }
        return ans.next;
    }

    public static ListNode helper(ListNode listNode1, ListNode listNode2){ // 合并两个链表
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        ListNode temp1 = listNode1;
        ListNode temp2 = listNode2;
        while (temp1 != null && temp2 != null){
            if (temp1.val > temp2.val){
                cur.next = temp2;
                cur = cur.next;
                temp2 = temp2.next;
            }else {
                cur.next = temp1;
                cur = cur.next;
                temp1 = temp1.next;
            }
        }
        if (temp1 !=null){
            cur.next = temp1;
        }
        if (temp2 != null){
            cur.next = temp2;
        }
        return res.next;
    }
