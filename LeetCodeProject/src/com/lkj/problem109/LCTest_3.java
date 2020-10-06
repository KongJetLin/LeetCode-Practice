package com.lkj.problem109;

import java.util.ArrayList;
import java.util.List;

/**
 * List集合保存链表结点法
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class LCTest_3
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
    public TreeNode sortedListToBST(ListNode head)
    {
        if(head == null)
            return null;

        ListNode cur = head;
        List<ListNode> list = new ArrayList<>();
        while (cur!=null)
        {
            list.add(cur);
            cur = cur.next;
        }

        return build(list , 0 , list.size()-1);
    }

    private TreeNode build(List<ListNode> list , int left , int right)
    {
        if(left>right)
            return null;

        int mid = (left+right)>>>1;
        TreeNode node = new TreeNode(list.get(mid).val);
        node.left = build(list , left , mid-1);
        node.right = build(list , mid+1 , right);
        return node;
    }
}
