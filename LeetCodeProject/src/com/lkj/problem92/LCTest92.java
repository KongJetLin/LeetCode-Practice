package com.lkj.problem92;

/** 对比 206 题：反转链表
1、对比 206 题的递归解法;
 2、这里也使用递归解法！
 */
public class LCTest92
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode reverseBetween(ListNode head, int m, int n)
    {
        /**
        若 m=1，那么就是直接翻转前 n-m+1 个元素；
         若m!=1，那么我们使得head.next 一直指向 reverseBetween(head.next, int m-1, int n-1)，
         即翻转以head.next 为头结点的链表的m-1到n-1部分，知道m-1，可以直接调用reverseN()
         */
        if(head.next == null)
            return head;

        if(m == 1)
        {
            return reverseN(head , n);
        }
        head.next = reverseBetween(head.next , m-1 , n-1);

        return head;
    }

    ListNode successor = null;//用于记录翻转段的后继结点
    //用于翻转以head为头结点的链表前 N 个元素的方法，返回翻转后的头结点
    private ListNode reverseN(ListNode head , int n)
    {
        if(n == 1)
        {
            /**
            当n=1的时候，找到需要翻转的最后一个结点，我们先记录翻转段的下一个结点，即后继结点，随后
             返回当前结点，作为翻转后的链表的头结点。
             */
            successor = head.next;
            return head;
        }
        //翻转以head.next为头结点的链表的前n-1个结点，返回翻转后的头结点
        ListNode reverseHead = reverseN(head.next , n-1);

        /**
        我们知道，返回的链表的最后一个结点 head.next 的next指向successor，
         我们使得 head.next.next 指向head，随后，将head.next 指向successor
         */
        head.next.next = head;
        head.next = successor;

        return reverseHead;
    }
}
