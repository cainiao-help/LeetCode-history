/*
25. K 个一组翻转链表
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

示例 1：

输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
  
示例 2：

输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
 
提示：
链表中的节点数目为 n
1 <= k <= n <= 5000
0 <= Node.val <= 1000
*/

// 该题考察翻转链表，相比之前的简单翻转链表，该题多了一个条件按个数翻转。由于翻转后会破坏头结点，所以不能单纯返回head，要建立一个链表特意指向当前链表头结点
// 难点在于如何将指向链表指向头结点，且保存每段的第一个分段链表，翻转后将保存的第一个链表指向第二个分段链表的头结点也就是第二段链表的尾节点。

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        System.out.println(reverseKGroup(listNode, 3));
    }

    public static  ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1){                    // 如果每个节点自身翻转则相当于没有变化直接返回头结点即可
            return head;
        }
        ListNode temp = head;           // 设置一个临时指针指向当前链表头结点开始遍历数组
        ListNode res = new ListNode(-1); // 设置一个最终指针链表，指向最终头结点
        ListNode copy = res;             // 设置一个用来连接两段链表之间的指针链表
        res.next = head;                // 将最终指针链表指向当前头结点，该步可省略
        int num = 0;
        while (temp != null){           // 首先统计给定的链表的个数
            num++;
            temp = temp.next;
        }
        int curNum = num;              // 设置当前剩余的链表个数
        temp = head;
        ListNode linked = res;
        while (curNum / k != 0){       // 若当前剩余的个数除k等于0说明余下数组小于k不用翻转，否则进入循环进行翻转
            curNum = curNum - k;       // 首先将剩余链表个数减去k
            int count = 1;             // 设置已经翻转个数

            ListNode pre = temp;       // 将当前这段链表的头结点设为第一个链表
            ListNode last = temp;      // 将当前这段链表的尾节点也设为第一个链表
            temp = temp.next;          // 将临时指针指向下一个数
            while (count < k){         // 当前已经翻转的个数小于k则进入循环开始翻转
                count++;               // 将当前已经翻转的个数加一
                pre.next = null;       // 将头结点的后节点设为null防止出现环无法debug
                ListNode cur = temp;   // 记录当前的链表并让当前链表指向下一链表然后将当前链表翻转指向last，并将last设为当前链表
                temp = temp.next;
                cur.next = last;
                last = cur;

            }
            pre.next = temp;           // 将当前段的链表的尾节点指向下一段链表的头结点
            copy.next = last;          // 将最终指针指向当前段链表的尾节点
            copy = pre;                // 设置连接指针为第一段和第二段的连接链表

        }

        return res.next;
    }
