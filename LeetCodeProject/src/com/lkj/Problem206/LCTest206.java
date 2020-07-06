package com.lkj.Problem206;

import java.util.Stack;

/** 对比92题：反转链表II
 * 栈法
时间复杂度：O(2n)=O(n)，包含入栈出栈操作！
 空间复杂度：O(n)，多使用一个栈！
 */
public class LCTest206
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode reverseList(ListNode head)
    {
        if(head == null)
            return null;

        Stack<ListNode> stack = new Stack<>();
        while(head != null)
        {
            stack.push(head);
            head = head.next;
        }

        ListNode ret = stack.pop();
        ListNode cur = ret;
        while (!stack.isEmpty())
        {
            cur.next = stack.pop();
            cur = cur.next;
        }
        /** 为什么加 cur.next = null 这一句？这时为了避免环的出现，否则结果会出现环。
            比如对于 1->2->3->null，我们将各个元素分别添加到栈中，栈内的元素分别是 [栈顶 3 , 2 , 1 栈底]，注意，此时 1.next = 2，2.next=3，3.next=null。
         我们在将他们取出连接起来，先取出 3，此时3.next=null，再将2取出，3.next设置为2，链表为：3->2->3...(环)；
         再将 1取出，此时1.next=2，将2.next设置为1，此时链表变为：3->2->1->2（环），此时cur=1，我们需要将cur.next设置为null，
         最后链表变为 3->2->1->null，这才是正确的结果！
         */
        cur.next = null;

        return ret;
    }

    public static void main(String[] args)
    {
        ListNode l = new ListNode(1);
        ListNode head = l;
        l.next = new ListNode(2);
        l = l.next;
        l.next = new ListNode(3);
        l = l.next;
        l.next = new ListNode(4);
        l = l.next;
        l.next = new ListNode(5);
        l = l.next;
        l.next = null;

        ListNode listNode = reverseList(head);
        while(listNode!=null)
        {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
