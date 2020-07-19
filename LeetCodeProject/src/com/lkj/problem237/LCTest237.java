package com.lkj.problem237;

public class LCTest237
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void deleteNode(ListNode node)
    {
        /**
        此处 node 就是要删除的结点，我们想要删除一个结点，必须知道前一个结点，才能将该结点删除，
         但是这里并不知道前一个结点，因此我们将后一个结点的值赋值给该结点，然后将该节点（变成下个结点，此时有2个下个结点）
         指向下下个结点，这样下个结点就被删除。（这里要考虑下个结点是否存在的问题）
         */
        //下个结点不存在，直接删除当前结点，将当前结点设置为null
        if(node.next == null)
            node = null;
        //下个结点存在
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
        next.next = null;
    }
}
