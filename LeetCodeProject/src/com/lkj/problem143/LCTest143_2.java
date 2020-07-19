package com.lkj.problem143;

/**
 * 递归：参考：https://leetcode-cn.com/problems/reorder-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-34/
 * 时间复杂度：O(n)，空间复杂度：O(n)（递归栈消耗的时间）
 */
public class LCTest143_2
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void reorderList(ListNode head)
    {
        //排除不需要翻转的特殊情况
        if (head == null || head.next == null || head.next.next == null)
        {
            return;
        }

        //计算链表传递
        ListNode p = head;
        int length = 0;
        while(p!=null)
        {
            length++;
            p = p.next;
        }

        //递归连接相应结点，使用链表长度来控制递归出口
        reorderHelper(head , length);//不需要记录返回值
    }

    //将长度为 length 的内部链表的结点按题目的要求连接，并返回次内部链表得出后面一个结点outTail，
    // 这个结点就是外面一层链表的尾结点
    private static ListNode reorderHelper(ListNode node , int length)
    {
        //递归结束条件
        if(length == 1)
        {
            //只剩下一个结点（链表长度为奇数）
            ListNode outTail = node.next;//找到外部链表尾结点
            node.next = null;//将当前链表与外部链表尾结点断开
            return outTail;//将外部链表尾结点返回
        }
        if(length == 2)
        {
            //剩下2个结点（链表长度为偶数）
            ListNode outTail = node.next.next;
            node.next.next = null;
            return outTail;
        }

        //记录内部链表头结点
        ListNode innerHead = node.next;
        //将内部链表按题目要求排列，并返回当前层链表尾结点
        ListNode tail = reorderHelper(innerHead , length-2);
        ListNode outTail = tail.next;//先记录当前层外面一层的尾结点
        //开始连接
        node.next = tail;
        tail.next = innerHead;//连接到内部链表
        return outTail;//将外部链表的尾结点返回
    }

    public static void main(String[] args)
    {
        ListNode head = new ListNode(1);
        ListNode p = head;
        int length = 2;
        while(length <= 4)
        {
            p.next = new ListNode(length);
            p = p.next;
            length++;
        }
        reorderList(head);
    }
}
