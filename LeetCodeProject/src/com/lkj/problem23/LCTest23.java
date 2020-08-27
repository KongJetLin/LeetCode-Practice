package com.lkj.problem23;

import java.util.Comparator;
import java.util.PriorityQueue;

/** 优先队列
时间复杂度：O(nlogk)，n为所有链表的元素个数，k为链表总数
 空间复杂度：O(k)
 */
public class LCTest23
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

        //构造优先队列，元素个数为lists的长度，将val值小的结点排在前面
        /**
        注意，java的优先队列是最小堆实现，因此会将比较大小最小的元素放在队首，即优先队列中，比较大小较小的元素在前面。
         如果 l1.val>l2.val，则返回正数，说明比较大小上，l1>l2，因此优先队列中，l2会排在l1前面。
         反正想让最小的放队列前面，就用 l1-l2；如果想让大的放前面，就用l2-l1
         */
        PriorityQueue<ListNode> queue = new PriorityQueue(lists.length , new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1 , ListNode l2)
            {
                return l1.val - l2.val;
            }
        });

        //先将各 k 个链表的第一个结点放入queue
        for(ListNode node : lists)
        {
            if(node != null)
                queue.add(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode moveNode = dummy;
        //下面将所有的结点排入新的链表
        while (!queue.isEmpty())
        {
            //将队列中值最小的结点取出
            ListNode cur = queue.remove();
            //将新的结点的下一个结点放入队列，使得队列的元素个数仍然是k
            if (cur.next != null)
                queue.add(cur.next);
            moveNode.next = cur;
            moveNode = moveNode.next;
        }

        return dummy.next;
    }
}
