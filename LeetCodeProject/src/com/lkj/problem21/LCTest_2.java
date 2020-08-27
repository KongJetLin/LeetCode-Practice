package com.lkj.problem21;

import java.util.HashSet;

/** 哈希表存储ListNode地址，比较地址即可（ListNode指的就是该结点在内存的地址）
时间：O(n)
 空间：O(n)
 */
public class LCTest_2
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
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;

        while (cur!=null)
        {
            //哈希表包含这个地址，则说明有环
            if(set.contains(cur))
                return true;
            else//否则将这个地址添加到哈希表
                set.add(cur);
            cur = cur.next;//链表向后移动
        }
        return false;//走到链表结尾，说明没有环
    }
}
