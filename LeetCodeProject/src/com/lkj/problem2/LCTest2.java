package com.lkj.problem2;

/** 解法如下
 1、将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补 00，比如 987 + 23 = 987 + 023 = 1010
 2、每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
 2、如果两个链表全部遍历完毕后，进位值为 1，则在新链表最后方添加节点 11

 tips：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。
 使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
 */
public class LCTest2
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        //定义虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        //进位
        int carry = 0;

        //当l1与l2有一个不为null的时候，说明较长的一个还没有遍历完，应该继续遍历。
        //当 l1与l2 全为null，说明2个链表遍历完毕，结束循环
        //对于为null的一方，使用0替代
        while (l1!=null || l2!=null)
        {
            //如果l1/l2 为null，将这一位视为补的0
            int x = (l1==null)?0:l1.val;
            int y = (l2==null)?0:l2.val;

            //先计算当前位的值（注意将上一次的进位carry加进来）
            int sum = x + y + carry;
            cur.next = new ListNode(sum%10);//将进位去除（如果有进位）
            cur = cur.next;

            //根据sum的值计算下一轮的进位，进位carry只能为0/1，因为9+9+1=19，最多只有一个进位
            if(sum>=10)
                carry = 1;
            else
                carry = 0;

            /*
            需要将l1与l2向后移动。但是需要在 l1与l2 不为null的时候才可以取next，否则null.next 就是空指针异常。
            假设l1较长，l2遍历到最后null，剩下的l1比l2长的位都要补0，就是剩下的l2的位都是null，此时l2已经是null，
            不可以也不需要 l2.next，保存l2=null即可

            l1: 2 -> 3 ->  4  ->  5   -> 6
            l2: 7 -> 3 -> null   null   null
             */
            if(l1!=null)
                l1 = l1.next;
            if(l2!=null)
                l2 = l2.next;
        }

        //注意，有可能l1与l2位数相同，他们最后一位有一个进位carry=1，都是由于l1.next与l2.next都是null，因此循环结束，但是carry应该计算
        if(carry!=0)
            cur.next = new ListNode(carry);
        else
            cur.next = null;

        return dummy.next;
    }
}
