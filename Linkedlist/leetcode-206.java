/*
206.反转链表
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

示例 1：
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]

示例 2：
输入：head = [1,2]
输出：[2,1]

示例 3：
输入：head = []
输出：[]
 
提示：
链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000

进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
*/

// 该题可使用双指针或者栈来解决，但栈要占用格外的空间
//


// 使用双指针，遍历一次链表


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        System.out.println(reverseList(listNode));
    }

    public static ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode temp = new ListNode();
        temp = head;
        head = head.next;
        temp.next = null;               // 将头链表的下一位置置为空
        while (head != null){
            ListNode next = head.next;  // 记录链表下一个位置
            head.next = temp;           // 将当前位置的下一位置修改为上一个链表位置
            temp = head;                // 将第一个指针后移（即将上一链表的指针指向当前链表）
            head = next;                // 将当前链表的指针指向下一个链表。
        }

        return temp;
    }


// 使用栈来反转链表，空间复杂度较高

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        System.out.println(reverseList(listNode));
    }

    public static ListNode reverseList(ListNode head){
        if (head == null){                // 考虑特殊情况若当前链表为空则直接返回空
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head.next != null){        // 遍历一次链表将链表依次放入栈中，遍历到最后一个停止
            stack.add(head);              
            head = head.next;
        }
        ListNode temp = head;
        while (!stack.isEmpty()){         // 依次出栈然后将链表反转
            ListNode listNode = stack.pop();
            listNode.next = null;
            temp.next = listNode;
            temp = temp.next;
        }

        return head;
    }
