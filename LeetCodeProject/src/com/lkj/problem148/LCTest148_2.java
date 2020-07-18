package com.lkj.problem148;

/**
 *
 * 时间复杂度：O(nlogn)，
 * 空间复杂度：O(1)
 */
public class LCTest148_2
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode sortList(ListNode head)
    {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode p = head;
        //首先计算次链表长度
        int length = 0;
        while (p != null)
        {
            length++;
            p = p.next;
        }

        //下面根据链表长度进行切割合并，当合并的区间size大于 length/2+1 的时候，
        // 这一次 size大于 length/2+1 的切割合并执行完，已经可以使得链表有序，不需要继续下一轮切割合并
        //我们这里使得 size<length，然后在执行完某一次 size大于 length/2+1后 ，判断size是否大于 length/2+1，是的话跳出循环！
        for (int size = 1; size < length ; size *= 2)
        {
            /**
             注意，我们每一轮都需要从链表的头部开始进行切割和合并，即要求linkNode每次都指向前一轮合并后链表的前一个结点，
             cur指向前一轮合并后链表的第一个结点。
             最开始我们使得 linkNode 指向 dummyHead，这样linkNode与dummyHead其实就是同一个结点，即使linkNode 进行移动，合并了每一轮排序后的链表，
             dummyHead.next 依然指向每一轮排序后的链表，那么此时cur指向 dummyHead.next，也指向前一轮合并后链表的第一个结点。

             总结：在循环内，一开始就使得 linkNode 与 dummyHead 位于同一个结点，这样linkNode进行移动，连接合并，形成新的链表后，
             dummyHead 依然是指向新的链表的前一个结点，那么我们新一轮的切割合并前，再将 listNode 设置为 dummyHead，
             将cur设置为dummyHead.next即可！！！
             最后合并完，dummyHead 就会指向排序成功的链表的前一个结点 ！
             */
            ListNode linkNode = dummyHead;//用于连接合并后的段成为一个新的链表，以便下一轮继续分割合并
            ListNode cur = dummyHead.next;//用于切割链表

            //当cur不为null的时候，我们就进行切割合并
            while(cur!=null)
            {
                /**
                 这里需要注意，切割后有几种情况：
                 1）left长度为size，right长度为size，我们将其合并，然后linkNode连接，不需要管剩下的链表是否为空（为空cur=null，会直接终止，不为null继续连接）
                 2）left长度为size，right长度小于size，合并，此时cut(right , size)返回cur为null，下一轮会终止切割合并
                 3）left长度为size或者小于size，right长度为null，我们也正常合并left与right，但是cur=null，下一轮终止切割合并

                 对于cut()方法，出现切割后剩下链表长度为0，会直接返回null；要切割的链表本身就是null，也直接返回null
                 */
                ListNode left = cur;//左链表头结点
                ListNode right = cut(cur , size);//切割出左链表，返回右链表头结点
                cur = cut(right , size);//切割出链表，并将剩下链表的结点设置为cur，便于下一次的切割

                //将左右链表合并，并使用linkNode连接起来
                linkNode.next = merge(left , right);
                //将linkNode移动到合并后链表的尾部，便于合并下一次的链表
                while (linkNode.next != null)
                    linkNode = linkNode.next;
            }
            if(size >= length/2+1)
                break;
        }
        return dummyHead.next;//返回排序后的链表
    }

    //切割链表head的前k个结点，返回切割后链表剩下部分的头结点
    private ListNode cut(ListNode head , int k)
    {
        if(k<=0)
            return head;//无需切割

        //走k-1次到达切割的链表的最后一个结点
        while (k>1 && head!=null)
        {
            k--;
            head = head.next;
        }
        /**
        切割可能出现的情况：
         1）head一开始就是null，直接返回null，不需要切割
         2）链表剩下的结点不够k个，走到中间head=null，说明没有剩下的链表，即切割后剩下链表的头结点设置为null，也可以直接返回null
         3）刚刚好切割完，head不为null，head.next=null，返回剩下链表的头结点为null，返回head.next
         4）切割后剩下的链表还有结点，返回head.next
         */
        if(head == null)
            return null;//1、2类情况
        //先将head.next保存，再断开切割段与剩下段
        ListNode next = head.next;
        head.next = null;
        return next;
    }


    //合并2个链表
    private ListNode merge(ListNode leftHead , ListNode rightHead)
    {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (leftHead!=null && rightHead!=null)
        {
            if(leftHead.val < rightHead.val)
            {
                cur.next = leftHead;
                leftHead = leftHead.next;
            }
            else
            {
                cur.next = rightHead;
                rightHead = rightHead.next;
            }
            cur = cur.next;
        }
        cur.next = (leftHead == null)?rightHead:leftHead;
        return dummyHead.next;
    }
}
