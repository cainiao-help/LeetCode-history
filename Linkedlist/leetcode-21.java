/*
21. 合并两个有序链表
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例 1：

输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

示例 2：
输入：l1 = [], l2 = []
输出：[]

示例 3：
输入：l1 = [], l2 = [0]
输出：[0]

提示：
两个链表的节点数目范围是 [0, 50]
-100 <= Node.val <= 100
l1 和 l2 均按 非递减顺序 排列

*/ 该题后面第二遍做有优化

// 该题是合并链表的题，可用多种方式解决，如首先建立数组保存全部数然后进行排序，最后构建新的链表，但该方法的时间复杂度较长，排序需要消耗O（nlogn）的时间,不如遍历一遍数组然后直接构建排序好的链表
// 构建一个头结点来作为返回值，设置一个临时指针指向链表头，然后依次遍历比较两个链表，将当前新建链表的下一个节点设置为两个链表当前比较元素的较小值，并将临时节点指向当前节点，然后将较小值的链表遍历为下一个
// 若最后两个链表有没有遍历完的链表则将当前的临时指针指向当前没有遍历完的链表节点，并将临时节点指向当前节点，然后继续遍历未遍历完的节点


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        System.out.println(mergeTwoLists(l1, l2));
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2){
        ListNode head = new ListNode(-1);              // 设置新建链表的头结点
        ListNode temp = head;                          // 将临时节点指向当前头节点
        if (list1 == null && list2 == null){           // 将特殊情况列出
            return head.next;
        }
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        while (list1 != null && list2 != null){        // 遍历两个不为空的链表，比较两个链表当前位置的值
            if (list1.val < list2.val){
                temp.next = list1;
                temp = temp.next;
                list1 = list1.next;
            }else {
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }
        }
        while (list1 != null || list2 != null){        // 若有一个链表为空则依次遍历不为空的链表
            if (list1 != null){
                temp.next = list1;
                temp = temp.next;
                list1 = list1.next;
            }
            if (list2 != null){
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }
        }
        return head.next;                          
    }


// 该题是基础的链表题，构建一个新的链表，返回头结点下一位，依次遍历比较两个链表

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(4);
        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);
        System.out.println(mergeTwoLists(listNode1, listNode2));
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);          // 构建头结点链表
        ListNode ans = res;                       // 记录头结点地址最终结果返回ans.next
        ListNode temp1 = list1;                   // 利用临时指针链表依次遍历两个链表
        ListNode temp2 = list2;
        while (temp1 != null && temp2 != null){   // 若两个链表都不为空，则遍历比较两个链表
            if (temp1.val > temp2.val){           // 若链表1的节点值大于链表2的节点值则将新建链表的下一个指向链表2的节点
                res.next = temp2;
                temp2 = temp2.next;
                res = res.next;
            }else {                                // 反之则指向链表1的节点
                res.next = temp1;
                temp1 = temp1.next;
                res = res.next;
            }
        }
        if (temp1 != null){                        // 此时链表1和链表2有一个为空，哪个不为空就将后续的链表加入结果链表中
            res.next = temp1;
        }
        if (temp2 != null){
            res.next = temp2;
        }

        return ans.next;
    }
