package com.lkj.Problem206;

/** 对比92题：反转链表II
 * 迭代法！
 时间复杂度：O(n)
 空间复杂度：O(1)
 */
public class LCTest206_2
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    //在脑海里想象整个过程！
    public ListNode reverseList(ListNode head)
    {
        //首先，顶以cur指针，用于在链表上移动，初始指向链表头结点
        ListNode cur = head;
        //其次，定义ret，用于记录反转后的链表，指向反转后的链表的头结点，初始值为null
        ListNode ret = null;

        while(cur!=null)
        {
            //1、先保存 cur.next，因为下面要将cur.next反转指向前面
            ListNode temp = cur.next;
            //2、将cur.next指向前面的ret，此时cur的指针反转过来
            cur.next = ret;
            //3、更新此时的cur为记录反转链表头结点的 ret，cur还要重新赋值为temp继续向下指
            ret = cur;
            //4、最后，将cur重新设置为 temp，继续向下指，直到cur=null，则遍历完整条链表，结束
            cur = temp;
        }
        return ret;

    }
}
