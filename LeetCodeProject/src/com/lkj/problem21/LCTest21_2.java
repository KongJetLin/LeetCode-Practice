package com.lkj.problem21;

/**
 循环法：时间复杂度同样是 O(m+n)
 */
public class LCTest21_2
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
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (l1!=null && l2!=null)
        {
            if(l1.val<=l2.val)
            {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }
            else
            {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if(l1!=null)
            cur.next = l1;
        if(l2!=null)
            cur.next = l2;

        return dummy.next;
    }
}
