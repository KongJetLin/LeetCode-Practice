package com.lkj.Problem203;

//第203：移除链表元素
/* 题目描述
    删除链表中等于给定值 val 的所有节点。

    示例:

    输入: 1->2->6->3->4->5->6, val = 6
    输出: 1->2->3->4->5
 */

/**  解题模板：利用以下结点构造链表解题（在LeetCode中不需要创建这个结点类，但是我们自己在IDE中测试的时候需要添加这个结点类）
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class LCTest203
{
    /**
     * 解析：这题实际上是对链表中的元素进行删除，删除结点需要找到该结点在链表中的上一个结点
     * 我们可以采用虚拟头结点的方式进行删除，这样就能找到所有结点的上一个结点。
     * 或者是分第一个结点和其他结点的方式进行分情况删除。
     */

    //首先，我们采用不涉及头结点的方式进行删除，这就要分情况删除：分删除第一个结点和删除其他结点的情况
    public ListNode removeElements1(ListNode head, int val)
    {
        /*
        首先，我们先判断第一个结点，如果这个结点的值符合val，就对他进行删除。但是有可能开头连续几个都是满足val，
        因此，我们必须循环判断。但是，有可能整个链表都满足val，删除到最后链表为空，我们还进行 head.val = val
        的判断就会出现空指针异常，因此我们还需要判断head是否为null，既链表是否为空。
        这样判断，就把一开始链表为空的情况也考虑进去。
         */

        //当链表不为空 head!=null，且head.val = val时，进行删除
        while (head != null && head.val == val)
        {
            //没有前一个结点的删除方法
//            ListNode delNode = head;
//            head = head.next;
//            delNode.next = null;
            /**
             * 如果我们不考虑前面说的 Loitering Object ，那么我们对于开头值是val的结点，可以不进行删除，
             * 而是直接将head移动到下一个结点。其实此时原来结点中的值还存在于内存中，但是LeetCode中运行完
             * 之后会将内存中的值全部释放，因此没什么关系
             */
            head = head.next;
        }
        //如果一开始链表就为null，就会不会进入循环；当链表头的val删除完毕后，就会跳出循环。
        //如果一开始链表就为null，或者整个链表都是val，整个链表被删除，那么此时head=null，我们直接返回head或者null
        if (head == null)
            return head;//返回null与返回head是相同的

        //当链表不为null，且开头不为val，对链表除开头外其他元素进行循环判断
        ListNode pre = head;//创建一个指针指向链表的第一个结点，同时可以用pre代表这个结点（这个结点的值不为val）
        while (pre.next != null)
        {
            //如果链表的下一个结点不为null，循环判断。
            // 如果下一个结点的值=val，那么就删除它，下下个结点就变成下一个结点，继续判断下一个结点值是否=val（此时不能向后移动。因为我们不知道之前下下个结点值是否为val）
            if (pre.next.val == val)
            {
                //有前一个结点的删除方法
//                ListNode delNode = pre.next;
//                pre.next = delNode.next;
//                delNode.next = null;
                /**
                 * 这里同样，如果不考虑Loitering Object，我们不需要将这个结点的元素删除。
                 * 直接将pre.next指定为pre.next.next，直接跳过下一个结点到下下个结点即可.
                 * 这样后面下一个结点的内存就会被自动回收
                 */
                pre.next = pre.next.next;
            }
            else
            {
                //如果下一个结点的值 ！= val，我们将当前结点后移一位
                pre = pre.next;
            }
        }

        return head;//最后，将删除val完毕的链表的第一个结点返回
    }

    //对上面方法进行简化
    public ListNode removeElements2(ListNode head, int val)
    {
        while (head != null && head.val == val)
        {
            /**
             * 如果我们不考虑前面说的 Loitering Object ，那么我们对于开头值是val的结点，可以不进行删除，
             * 而是直接将head移动到下一个结点。其实此时原来结点中的值还存在于内存中，但是LeetCode中运行完
             * 之后会将内存中的值全部释放，因此没什么关系
             */
            head = head.next;
        }
        if (head == null)
            return head;

        ListNode pre = head;
        while (pre.next != null)
        {
            if (pre.next.val == val)
            {
                /**
                 * 这里同样，如果不考虑Loitering Object，我们不需要将这个结点的元素删除。
                 * 直接将pre.next指定为pre.next.next，直接跳过下一个结点到下下个结点即可.
                 * 这样后面下一个结点的内存就会被自动回收
                 */
                pre.next = pre.next.next;
            }
            else
            {
                pre = pre.next;
            }
        }
        return head;
    }

    //使用头结点进行删除（方便很多，因为所有结点都可以使用前一个结点进行删除）
    public ListNode removeElements3(ListNode head, int val)
    {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;//创建一个头结点，值为-1，指向链表第一个结点

        ListNode pre = dummyNode;//创建前一个结点指向头结点（创建前一个才能将这个结点后移，而dummyNode不能直接后移）

        while (pre.next != null)
        {
            if(pre.next.val == val)
            {
//                ListNode delNode = pre.next;
//                pre.next = delNode.next;
//                delNode.next = null;
                pre.next = pre.next.next;
            }
            else
            {
                pre = pre.next;
            }
        }

        //最后我们要返回链表第一个结点。那么dummyNode的下一个结点指的结点链表第一个结点，直接返回dummyNode.next即可
        /*
        不能返回head，因为我们一直在改变的是dummyHead.next的值，虽然链表中满足条件的元素被删除了，但是head这个指针仍然指向原来链表的第一个结点，
        因为我们一直没有改变head指针的指向。如果原来链表的第一个结点的值满足，那么这个结点被dummyNode.next删除了，
        它不存在于链表中，但是指针head仍然指向这个结点，那么返回head就会返回一个链表中不存在的结点，而不是链表的第一个结点！
         */
        return dummyNode.next;
    }


    public static void main(String[] args)
    {
        /*
            编写可以用于测试的链表用例
            我们创建一个数组，在创建一个结点的时候将数组存入，就可以根据数组值，创建一个以该结点为第一个结点的链表。
         */
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        //虽然我们这里是创建一个结点，但是同时通过构造函数也创建了以该结点为第一个结点的链表
        //并且我们执行该结点 toString 的时候，也会打印 以该结点为第一个结点的链表的值的字符串。（我们在ListNode 的toString方法设置）
        ListNode head = new ListNode(nums);
        System.out.println(head.toString());

        /*
        我们使用 removeElements 方法将以head结点为头结点的链表的值为6的结点删除，然后返回删除后的链表的第一个结点。
        那么根据ListNode的toString方法，我们执行 resultNode.toString()，也会打印以 resultNode 结点为头结点的链表
         */
        ListNode resultNode = (new LCTest203()).removeElements3(head, 6);
        System.out.println(resultNode.toString());
    }
}
