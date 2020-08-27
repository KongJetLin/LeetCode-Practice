package com.lkj.problem25;

//时间复杂度：O(n)，对比24题
public class LCTest25
{
    public class ListNode
    {
        int val;
        ListNode next;

        ListNode(int x)
        {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k)
    {
        //创建虚拟头结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        /**
        创建2个指针 pre,end指向虚拟头结点，其中，pre用来指向每一段需要翻转链表的前一个结点，end用来指向每一段需要翻转链表的最后一个结点。
         为什么这样创建：pre指向翻转链表的前一个结点，用于将前面的链表与翻转后的链表连接起来；
         end指向翻转链表的最后一个结点，为了在翻转之前使得要翻转的链表与后面的链表段分隔开！
         （记住 2 个指针在链表前一个、链表最后一个！且一开始都在翻转链表的前一位！）
         开始的时候，end与pre都指向链表段的前一位！

         这里需要4个参数：start、end、pre、next，其中pre、end我们需要先确定，因为pre用于确定start，end用于确定next
         pre -> start ->...-> end -> next
         */
        ListNode end = dummy;
        ListNode pre = dummy;

        //while里面的代码用于尝试翻转每一组，但是里面的代码只能翻转一组，为了能持续翻转，
        // 用 while(end.next != null)，用于判断是否需要进入下一组的翻转
        while (end.next != null)
        {
            /**
            寻找链表的最后一个结点，当end=null的时候，因为end最多指向需要翻转的链表的结尾，
             说明剩下的链表长度不足k，不需要翻转，直接跳出循环
             */
            for (int i = 0; i < k && end != null; i++)
                end = end.next;
            if(end == null)
                break;

            //下面开始翻转
            ListNode start = pre.next;//找到翻转链表的开头
            ListNode next = end.next;//记录链表段的下一个结点
            end.next = null;//将链表段的下一个结点设置为null，这样下面的翻转才会及时结束
            pre.next = reverse(start);//将 start-end 段翻转，同时将pre.next指向这一段
            start.next = next;//这时，start在翻转链表段的最后一位，将其next指向next

            //最后，需要重新设置pre与end，便于下一轮的翻转’
            end = start;
            pre = end;
        }
        return dummy.next;
    }
    //翻转以 head 为头结点的链表
    private ListNode reverse(ListNode head)
    {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null)
        {
            //先保存cur.next
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
