/*
160. 相交链表
给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
图示两个链表在节点 c1 开始相交：

题目数据 保证 整个链式结构中不存在环。
注意，函数返回结果后，链表必须 保持其原始结构 。
自定义评测：
评测系统 的输入如下（你设计的程序 不适用 此输入）：
intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
listA - 第一个链表
listB - 第二个链表
skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。
5->6->1->|
         |
   4->1->8->4->5

示例 1：
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
输出：Intersected at '8'
解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
— 请注意相交节点的值不为 1，因为在链表 A 和链表 B 之中值为 1 的节点 (A 中第二个节点和 B 中第三个节点) 是不同的节点。换句话说，它们在内存中指向两个不同的位置，而链表 A 和链表 B 中值为 8 的节点 (A 中第三个节点，B 中第四个节点) 在内存中指向相同的位置。
 
示例 2：
输入：intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Intersected at '2'
解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
  
示例 3：
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：No intersection
解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
这两个链表不相交，因此返回 null 。
 
提示：
listA 中节点数目为 m
listB 中节点数目为 n
1 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
如果 listA 和 listB 没有交点，intersectVal 为 0
如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
 
进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？
*/

// 1.可以选择正常思路，先统计出；两个链表的长度，然后再对齐两个链表，最后同时遍历进行寻找，这种办法较普通，代码繁琐
// 2.也可以选择使用建两个节点指针 A​ , B 分别指向两链表头节点 headA , headB ，做如下操作：
// 指针 A 先遍历完链表 headA ，再开始遍历链表 headB ，当走到 node 时，共走步数为：
//             a+(b−c)
// 指针 B 先遍历完链表 headB ，再开始遍历链表 headA ，当走到 node 时，共走步数为：
//             b+(a−c)
// 如下式所示，此时指针 A , B 重合，并有两种情况：
//           a+(b−c)=b+(a−c)
// 若两链表 有 公共尾部 (即 c>0 ) ：指针 A , B 同时指向「第一个公共节点」node 。
// 若两链表 无 公共尾部 (即 c=0 ) ：指针 A , B 同时指向 null 。


    public static void main(String[] args) {   // 测试用
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(9);
        node1.next.next = new ListNode(1);
        node1.next.next.next = new ListNode(2);
        node1.next.next.next.next = new ListNode(4);
        ListNode node2 = new ListNode(3);
        node2.next = node1.next.next.next;
        System.out.println(getIntersectionNode(node1, node2));
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode res = null;
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        int n1 = 1;
        int n2 = 1;
        while (temp1.next != null){    // 遍历两个链表得到链表长度
            temp1 = temp1.next;
            n1++;
        }
        while (temp2.next != null){
            temp2 = temp2.next;
            n2++;
        }
        if (temp1 != temp2){            // 若此时末尾节点是同一个则说明两个链表有相交，反之则没有相交直接返回null
            return res;
        }else {                         
            int target = Math.abs(n2 - n1);      // 计算出两个链表的相差节点个数，由于两个链表相交后的节点一定相同，所以后面节点个数一定相同，只需要对齐两个链表即可
            if (n1 > n2){                        // 如果链表A较长，则使A先对齐，反之使B对齐
                for (int i = 0; i < target; i++) {  
                    headA = headA.next;
                }

            }else {
                for (int i = 0; i < target; i++) {
                    headB = headB.next;
                }

            }
            while (headA != headB){                 // 对齐后遍历后续链表
                    headA = headA.next;
                    headB = headB.next;
                }
                res = headA;
        }
        return res;
    }

// 以下为第二种方法，具体见https://leetcode.cn/problems/intersection-of-two-linked-lists/solutions/12624/intersection-of-two-linked-lists-shuang-zhi-zhen-l/

    public static void main(String[] args) {    // 测试 用
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(9);
        node1.next.next = new ListNode(1);
        node1.next.next.next = new ListNode(2);
        node1.next.next.next.next = new ListNode(4);
        ListNode node2 = new ListNode(3);
        node2.next = node1.next.next.next;
        System.out.println(getIntersectionNode(node1, node2));
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode a = headA,b = headB;
        while (a!=b) {
            a=a==null?headB:a.next;
            b=b==null?headA:b.next;
        }
        return a;
    }
