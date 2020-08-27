package com.lkj.problem23;

/** 二分合并链表
假设所有 K 个链表结点总数为：N，那么平均每个链表有 N/K 个结点，
 从底层开始，两两合并链表，最后一层其实合并了N个结点，倒数第二层也是N个（因为每一层的每一个结点都需要进行合并）
 一共需要合并 logK 层，那么总的时间复杂度是：O(NlogK)

 还有一种两两合并的方式，先第一条与第二条合并，花费 2N/K 时间，他们的合并结果与第三条合并，花费 3N/K 时间...
 总的时间：（2N/K + 3N/K + ... + KN/K） = (K+2)(K-1)N/K = (k+1-2/K)N，
 因此总的时间复杂度是：O(NK)
 */
public class LCTest23_2
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeKLists(ListNode[] lists)
    {
        if(lists == null || lists.length==0)
            return null;

        return merge(lists , 0 , lists.length-1);
    }

    //进行二分合并链表，即合并由lists数组 start到end 范围内的链表组成的2个链表
    private ListNode merge(ListNode[] lists , int start , int end)
    {
        if(start == end)
            return lists[start];

        int mid = start + (end - start)/2;
        ListNode left = merge(lists , start , mid);
        ListNode right = merge(lists , mid+1 , end);
        //将底层合并的left与right链表合并起来
        return mergeTwoLists(left , right);
    }

    //合并2个链表的辅助方法
    private ListNode mergeTwoLists(ListNode l1, ListNode l2)
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
