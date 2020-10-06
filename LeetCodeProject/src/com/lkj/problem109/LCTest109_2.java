package com.lkj.problem109;

/**
1）快慢结点法，参考876题
 2）时间复杂度：我们每次都将链表拆分为一半，知道链表只有一个元素，即1*2^k = N，需要 k = logN的时间。
    每次遍历N个元素，因此时间复杂度是O(NlogN)级别！
 3）空间复杂度 O(logN)
 */
public class LCTest109_2
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
        //先找到中间结点
        ListNode midNode = findMidNode(head);
        //中间结点就是当前树的根结点，构造当前树的根结点
        TreeNode root = new TreeNode(midNode.val);

        //如果head=midNode，说明当前的链表只有一个结点，它构造的树叶没有左右孩子结点，
        //并不需要为当前结点设置左右孩子结点，直接返回当前树即可
        if(head == midNode)
            return root;

        /**
        下面根据链表 head到midNode的前一个结点的部分（已经断开，可以找到这一段结点的中间结点），找到左子树的结点；
         根据 midNode.next 到原先链表尾部的部分，找到右子树！
         */
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(midNode.next);

        return root;
    }

    //找到链表的中间结点，如果链表结点不止一个，从中间结点处将链表断开
    private ListNode findMidNode(ListNode head)
    {
        //定义快慢指针，从头开始寻找中间结点
        ListNode slow = head;
        ListNode fast = head;
        ListNode preMid = null;//用于存储中间结点(这里就是最后的slow结点)的前一个结点，用于断开链表

        while(fast!=null && fast.next!=null)
        {
            preMid = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        //如果链表只剩下一个结点或者没有结点（结点为null），此时preMid=null，中间结点就是slow或者fast，不需要断开链表
        //否则，我们将 preMid.next = null，即断开前后链表（如果不断开链表，就不能区分左右子树的中间结点）
        if(preMid != null)
            preMid.next = null;
        return slow;//返回找到的中间结点
    }
}
