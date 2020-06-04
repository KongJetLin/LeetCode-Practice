package com.lkj.Problem203;

public class ListNode
{
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * 功能：创建一个数组，在创建一个结点的时候将数组存入，就可以根据数组值，创建一个以该结点为第一个结点的链表。
     * 为了实现这个功能，我们需要创建一个ListNode类可以接收数组的构造函数
     */
    public ListNode(int nums[])
    {
        //首先判断传入的数组是否合法
        if(nums == null || nums.length == 0)
            throw new IllegalArgumentException("arr can not be empty");

        this.val = nums[0];//当前被创建的结点为链表第一个结点，我们将当前结点指设置为数组第一个值
        ListNode cur = this;
        for (int i = 1; i < nums.length ; i++)
        {
            //创建一个新的结点，结点值为nums[i]，将cur.next指向这个结点
            cur.next = new ListNode(nums[i]);
            cur = cur.next;//将cur后移一位
        }
    }

    //将以当前创建结点为第一个结点的链表值打印出来
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;//创建一个指针，指向当前结点

        while (cur != null)
        {
            sb.append(cur.val+"->");
            cur = cur.next;
        }
        sb.append("NULL");

        return sb.toString();
    }
}
