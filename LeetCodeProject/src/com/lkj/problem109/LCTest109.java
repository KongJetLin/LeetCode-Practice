package com.lkj.problem109;

//对比108题
public class LCTest109
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
//------------------------------------------------------------
    /**
    自己的方法，超时，可能是因为每次都要使用快慢指针进行中间结点的寻找，时间是 n/2 + n/2 + .. ，时间复杂度是O(n^2)级别
     */
    public TreeNode sortedListToBST(ListNode head)
    {
        ListNode tail = head;
        while(tail.next != null)
        {
            tail = tail.next;
        }
        return buildAVL(head, tail);
    }

    private TreeNode buildAVL(ListNode start , ListNode end)
    {
        if(start == null)
            return null;

        if(start == end)
            return new TreeNode(start.val);

        ListNode slow = start;
        ListNode fast = start;
        ListNode preSlow = null;
        while(fast!=null && fast.next!=null)
        {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode cur = new TreeNode(slow.val);
        cur.left = buildAVL(start , preSlow);
        cur.right = buildAVL(slow.next , end);

        return cur;
    }
}
