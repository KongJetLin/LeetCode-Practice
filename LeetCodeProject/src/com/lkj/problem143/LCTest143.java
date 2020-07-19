package com.lkj.problem143;

/**
 * 时间复杂度：找到中间结点，花了O(n/2)时间，翻转链表，话了O(n/2)时间，
 * 最后连接2个链表，还要再遍历O(n)的结点，因此总的时间复杂度是：O(2n)=O(n)
 * 空间复杂度是：O(1)
 *
 * 2、最简单粗暴的解法：把链表存储到线性表中，然后用双指针依次从头尾取元素即可。
 * 存储线性表元素花费：O(n)时间，O(n)空间，最后排序花费：O(n)时间
 * 总的时间复杂度是：O(n)，空间复杂度是：O(n)  （当然这里可以用栈，更好一点）
 * 这种占用多余空间的方法，不推荐。
 */
public class LCTest143
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     1、先找处链表L的中间结点，快慢指针法（必须熟悉）；
     2、然后将L的后半段结点原地逆置，反转链表非递归法（必须熟悉）；
     3、从单链表前后两段中 依次各取一个结点，按要求重排；
     */
    public void reorderList(ListNode head)
    {
        //排除不需要翻转的特殊情况
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        //1、寻找中间结点
        /**
        注意，这里不要适用虚拟头结点来为快慢指针赋值，这会使得 head的值变化，dummy.next = head
         */
        ListNode slow = head;
        ListNode fast = head;
        //使用快指针作为移动指标，必须如下设置，才能使得链表长度为偶数的时候，慢指针指向中间2个结点的前一个
        while (fast.next!=null && fast.next.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode newHead = slow.next;//右边段的第一个结点
        slow.next = null;//将2段断开

        //2、翻转右边段链表
        newHead = reverse(newHead);

        //3、构造新的链表
        //注意下面的翻转方法，这种方法对于左段与右段等长，左段比右段长1的情况都适用
        while(newHead!=null)
        {
            ListNode temp = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = temp;
        }
    }

    private ListNode reverse(ListNode head)
    {
        if(head == null)
            return head;
        //2、翻转右边段链表
        ListNode pre = null;
        while(head != null)
        {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
