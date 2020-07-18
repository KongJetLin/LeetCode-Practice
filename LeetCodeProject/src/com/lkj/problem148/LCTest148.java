package com.lkj.problem148;

/**
 * 递归+归并排序，时间复杂度：O(nlogn)，
 * 空间复杂度：由于递归需要使用系统栈，递归的最大深度是 logn，所以需要额外 O(logn) 的空间。
 */
public class LCTest148
{
    public class ListNode
    {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public ListNode sortList(ListNode head)
    {
        /** 递归终止条件
            当head=null，这种情况只有可能是一开始进来head就是next，此时无需排序，直接返回head。
         当head.next=null，只要初始的head的长度大于等于，根据我们的快慢指针的走法，最后都会变成一个结点，而不会出现head=null的情况，
         此时head.next=null。
         当开始 head=null ，或者走到最后，head.next=null，即只剩下一个结点，可以终止递归
         */
        if(head == null || head.next == null)
            return head;

        /** 快慢指针取中间结点法。
         1）奇数个结点，走到中间结点；偶数个结点，走到中间2个结点的前一个；
         2）终止情况：2个结点 a->b，分割成为 a,b2个结点，下一次递归终止
            3个结点 a->b->c，从b分割，分为 a->b、c，最后同上
         */
        ListNode slow = head;
        ListNode fast = head.next;

        //我们以快指针为移动的标准，必须先判断fast不为null，否则后面fast.next会出现空指针异常
        while (fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        //出来后，slow就是分割的点，注意将 slow.next设置为null，这样才算将链表分割
        ListNode temp = slow.next;//右边链表的开始结点
        slow.next = null;

        //递归排序左右链表
        ListNode leftHead = sortList(head);
        ListNode rightHead = sortList(temp);

        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        while (leftHead!=null && rightHead!=null)
        {
            if(leftHead.val < rightHead.val)
            {
                cur.next = leftHead;
                leftHead = leftHead.next;
            }
            else
            {
                cur.next = rightHead;
                rightHead = rightHead.next;
            }
            cur = cur.next;
        }
        cur.next = (leftHead == null)?rightHead:leftHead;
        return newHead.next;
    }
}
