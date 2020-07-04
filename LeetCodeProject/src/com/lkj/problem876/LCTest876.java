package com.lkj.problem876;

public class LCTest876
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    //数组法
    public ListNode middleNode(ListNode head)
    {
        ListNode[] temp = new ListNode[100];//链表最大长度为100

        int t = 0;
        while(head != null)
        {
            temp[t++] = head;
            head = head.next;
        }

        return temp[t/2];
    }

    //快慢指针法
    public ListNode middleNode1(ListNode head)
    {
        ListNode slow = head;
        ListNode fast = head;

        //当 fast不为null，且fast.next不为null，此时快指针才可以前进，只要fast=null或者fast.next=null满足，说明快指针不可前进了！
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
