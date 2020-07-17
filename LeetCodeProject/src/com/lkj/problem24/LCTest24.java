package com.lkj.problem24;

//对比第25题
public class LCTest24
{

    public class ListNode
    {
        int val;
        ListNode next;

        ListNode(int x)
        {
            val = x;
        }
    }

    //递归解法
    public ListNode swapPairs(ListNode head)
    {
        //当只剩下一个结点，或者一个结点也没有，不需要交换，直接返回head
        if(head == null || head.next == null)
            return head;

        ListNode next = head.next;
        //先将head.next指向原来next.next
        head.next = swapPairs(next.next);
        //再将next.next指向head
        next.next = head;

        return next;
    }

    //非递归解法
    public ListNode swapPairs1(ListNode head)
    {
        //头结点的前一个结点，用于最后的返回
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        //再创建一个temp指向preHead，用于交换过程中指针的移动
        ListNode temp = preHead;

        //只有2个结点都存在，才不需要进行交换
        while (temp.next != null && temp.next.next != null)
        {
            ListNode first = temp.next;
            ListNode second = temp.next.next;
            //注意下面的顺序，不能变化
            temp.next = second;
            first.next = second.next;
            second.next = first;
            temp = first;
        }
        return preHead.next;
    }
}
