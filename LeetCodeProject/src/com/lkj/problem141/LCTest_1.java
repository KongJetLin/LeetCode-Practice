package com.lkj.problem141;

/** 快慢指针
最差的情况是整个链表都是环，时间复杂度为：O(n)
 */
public class LCTest_1
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean hasCycle(ListNode head)
    {
        if(head == null || head.next == null)
            return false;

        //必须将fast与slow指针初始值设置为不同值，否则一开始就相同
        ListNode slow = head;
        ListNode fast = head.next;

        //若有环，则最后 slow=fast，若没有环，最后fast或者fast.next为null（因为fast要移动2步，因此要判断fast.next是否为null）
        while(slow != fast)
        {
            if(fast == null || fast.next == null)
                return false;//块指针先到末尾，则先返回false
            //否则快慢指针一直前进，直到slow=fast
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;//fast=slow，有环
    }
}
