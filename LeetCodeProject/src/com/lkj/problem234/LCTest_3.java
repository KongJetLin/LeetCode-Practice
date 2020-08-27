package com.lkj.problem234;

/** 快慢指针找中点，将后半段指针反转，与前半段比较
 时间复杂度：O(n)
 空间复杂度：O(1)

 这种方法要注意！
 （1）快慢指针取到slow为前半段最后一个结点，方便将前后断开；
 （2）注意边遍历边反转链表的方法。
 */
public class LCTest_3
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    public boolean isPalindrome(ListNode head)
    {
        //特判：没有结点
        if(head == null)
            return true;
        //一个结点的情况可以覆盖，不需要特判

        //定义快慢指针
        ListNode slow = head;
        ListNode fast = head;

        //找到中间结点（奇数）或者前半段最后一个结点（偶数）
        //找到前半段最后一个结点，是为了将前后断开
        while(fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHead = slow.next;//获取后半段的头结点
        slow.next = null;//将前后断开


        //反转后半段链表（后半段长度大于或等于前半段）
        secondHead = reverse(secondHead);//后半段反转后，重新获取头结点
        ListNode firstHead = head;//前半段头结点

        //前半段长度大于等于后半段
        while (firstHead!=null && secondHead!=null)
        {
            if(firstHead.val != secondHead.val)
                return false;
            firstHead = firstHead.next;
            secondHead = secondHead.next;
        }
        return true;
    }

    //反转链表，并返回翻转后的链表头结点(记住指针反转链表的方法)
    private ListNode reverse(ListNode node)
    {
        ListNode cur = node;
        ListNode pre = null;

        while(cur != null)
        {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
