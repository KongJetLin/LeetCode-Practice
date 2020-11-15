package com.lkj.problem21;

/**
 递归法：时间复杂度：O(n+m)，n、m 是2个链表的长度---对比23题，合并k个排序链表
 */
public class LCTest21_1
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;

        if(l1.val <= l2.val)
        {
            l1.next = mergeTwoLists(l1.next , l2);
            return l1;
        }
        else
        {
            l2.next = mergeTwoLists(l1 , l2.next);
            return l2;
        }
    }
}
