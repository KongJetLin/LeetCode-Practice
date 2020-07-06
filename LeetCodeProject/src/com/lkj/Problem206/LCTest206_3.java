package com.lkj.Problem206;

/** 对比92题：反转链表II
 * 递归法
 时间复杂度：O(n)
 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间。
 */
public class LCTest206_3
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode reverseList(ListNode head)
    {
        /*
        1、当head=null，直接返回null，不需要反转链表；
        2、当head.next=null，说明head是最后一个结点，直接返回head，如果遍历到head=null，下面head.next会出现空指针一次
         */
        if(head == null || head.next == null)
            return head;

        /**
         返回head.next到链表最后这一段链表反转后的头结点 p，其实p=head，在到达链表末尾后，
         会一直将p返回给上一层的递归，而且p在每一层的递归中不改变，会一直传递到最后！
         */
        ListNode p = reverseList(head.next);
        //此时head仍然指向 反转的p链表的最后一个结点last，即 head.next = last，last.next=null使得p链表最后一个结点指向head，
        head.next.next = head;
        //此时 last.next = head，但是head.next依然是last，形成环，因此将head.next设置为null
        head.next = null;

        return p;
    }
}
