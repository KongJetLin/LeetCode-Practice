package com.lkj.problem21;

//时间复杂度：O(n+m)，n、m 是2个链表的长度---对比23题，合并k个排序链表
public class LCTest21
{
    public class ListNode
    {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //该方法的广义含义：返回链表 l1，l2 合并后的链表的头结点
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        //当l1，l2 有一个为null（到达尾部），直接返回另一个剩下的结点。
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        //注意，这里是小于，因为较小的元素在前面
        if(l1.val < l2.val)
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
