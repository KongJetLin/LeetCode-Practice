package com.lkj.problem19;

public class LCTest19
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    //2次遍历
    public ListNode removeNthFromEnd(ListNode head, int n)
    {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //先找到链表长度
        ListNode cur = dummy;
        int length = 0;
        while(cur.next != null)
        {
            length++;
            cur = cur.next;
        }
        //n大于链表长度，没有删除的结点，直接返回原先链表
        if(n > length)
            return dummy.next;

        //找到删除结点的前一个结点
        cur = dummy;//指向虚拟头结点，注意，这里必须要用虚拟头结点进行删除，因为有可能第一个结点就需要删除！
        int moveTime = length-n;
        while (moveTime > 0)
        {
            cur = cur.next;
            moveTime--;
        }
        //进行删除
        cur.next = cur.next.next;

        return dummy.next;
    }


    //双指针，一次遍历
    public ListNode removeNthFromEnd1(ListNode head, int n)
    {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = dummy;

        /**
         * 使得 first 先移动n+1次，此时first与second之间间隔3个结点，
         * 此时first与second同时开始移动，直到first=null，此时second指向删除链表的前一个结点（画图可知）
         */
        int moveTime = n+1;
        while (moveTime > 0)
        {
            first = first.next;
            moveTime--;
        }

        while (first!=null)
        {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
