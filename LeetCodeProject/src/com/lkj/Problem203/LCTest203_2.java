package com.lkj.Problem203;

public class LCTest203_2
{
    /** 使用递归的方式解决
     1、 分析：
     首先，理解这个函数的涵义：这个函数是要删除以head为开头的链表的所有值为val的结点。而递归是由一个一个的小问题组成的，每次只能删除一个。
     如果我们想保证每一次递归调用的方法返回的 以head开头的链表内部的值为val的结点 都被删除，我们只能从尾部开始删除。
     我们将head指针移到尾部，从最后以head开头的链表为空开始，进行判断。
     如，对于链表 1->2->3->4->null(5)
     1) 我们先将head移动到尾部找到 以 5结点开头 的链表，此时head=null，那么removeElements4(5,val)就是删除以5为head的链表
     内满足 值=val 的结点，此时没有满足，返回head=null（null本身也可以作为一个链表）；
     2) 然后对 head=4结点开头 的链表进行判断，它接收以5为开头的链表，该链表满足值=val的结点全部被删除，最多只有head=4结点满足值=val，
     那么removeElements4(4,val)就是删除以4为head的链表内满足 值=val 的结点，我们需要对head=4进行判断，满足将head=4删除，
     不满足则返回以head=4为开头的链表；
     .....
     3)  最后，对最大的链表head进行判断，满足删除，不满足就返回head开头的链表，就大功告成！


     2、步骤：
     1) 解决最基本的问题
     我们将链表第一个结点的指针head移动到链表尾部，当head=null的时候，说明到达链表尾部，我们直接返回head=null，那么
     if(head == null)
     return head; //这一句就是最基本的问题吧

     2) 将原问题转换为更小的问题
     为了从尾部开始进行判断，我们先递归调用 removeElements4，每一次都将head向后移动一位，直到head=null，就会停止递归调用。
     ListNode res = removeElements4(head.next,val);  —— head.next到removeElements4就会被看作是head
     当判断到head=null，就会返回 res=null，此时结束递归调用。
     接下来从尾部的小链表开始删除，这些小链表最多只有一个结点的值=val，就是这些小链表的第一个结点head。
     因为比他更小的链表传递回来的链表都将满足值=val的结点删除了！因此当前链表只需要判断第一个结点head是否满足即可！

     需要注意的是，当前链表的head结点的处理，必须在递归调用removeElements4 删除更小一级链表的满足val的结点之后。
     因为我们是从尾到头进行判断，必须先将小链表的val点删除，返回小链表后，再判断大链表的head。
     */
    public ListNode removeElements4(ListNode head, int val)
    {
        //如果一开始链表就是空，或者找到最后一个小链表（该链表为null，既开头head为null），就将这个链表返回
        if(head == null)
            return head;

        /*
        先递归调用removeElements4删除较小链表的val点，返回较小的链表。
        这个调用方法 removeElements4 的涵义是：删除以head.next为开头的更小的链表的val点！！！（注意理解其涵义！）
         */
        ListNode res = removeElements4(head.next, val);//将head后移一位，删除后返回以res开头的较小的链表，该res链表不含val点

        //等递归调用方法的较小链表res返回后，接下来对当前链表的head结点进行判断删除
        if(head.val == val)
        {
            return res;//如果当前链表的head满足，直接忽略head，返回较小的res开头的链表
        }
        else
        {
            //如果head不满足，我们返回head开头的链表。
            // 注意由于当前head结点的下一个结点head.next可能被删除，返回的res可能没有接在head结点后面，我们必须先将head.next指定为res再返回
            head.next = res;
            return head;
        }

        /**
         * 这里的关键是：
         * 1）理解这个函数的涵义 removeElements4(head.next, val)：删除以head.next为开头的链表的val点 。
         * 然后每次链表只有第一个结点head有可能是val点，因为这个链表除head外，后面的结点是更小的链表res返回的，因此对于每一个链表只需要判断head即可；
         *
         * 2）当前链表head结点的判断，必须在递归调用 removeElements4 返回更小的链表res之后。否则判断head后，无法接上更小的链表！
         *
         * 3）这种方法对于第一个结点删除不需要第一个结点的上一个结点。
         * 因为我们是从后向前删除，如果第一个结点为val点，直接返回第二个结点开始的链表，忽略第一个结点即可，
         * 其实此时并没有删除第一个结点，只是将其忽略了！
         */

    }

    //上面方法的更简便写法
    public ListNode removeElements5(ListNode head, int val)
    {
        if(head == null)
            return head;

        //先将head.next指向返回的更小的链表，将将head与上面说的res连接起来
        head.next = removeElements5(head.next , val);
        //如果 head.val == val，那么忽略head结点，直接返回head.next，否则返回head（此时head.next以及指向更小的链表，与head连接起来！）
        return head.val == val?head.next:head;
    }



    //结合视频6的方法进行调试
    public ListNode removeElements6(ListNode head, int val , int depth)//depth为递归深度，每次递归调用都加1
    {
        String depthString = generateDepthString(depth);
        //下面2句的意思：在某一个递归深度下，删除某一个链表中的值为val的结点
        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);


        if(head == null)
        {
            //下面2句表明，这某一个递归深度下，已经找到规模最小的问题，直接返回
            System.out.print(depthString);
            System.out.println("Return:"+head);
            return head;
        }


        ListNode res = removeElements6(head.next, val, depth + 1);//每进行一次递归的调用，就将递归深度+1
        //下面2句的意思是，在删除较小链表的val结点后，得到的链表的字符串，并打印此时的深度
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);


        ListNode ret;
        if(head.val == val)
            ret = res;
        else{
            head.next = res;
            ret = head;
        }
        //下面2句的意思是，在某一递归深度下，我们将较小问题的解整合成为当前较大问题的解后得到的链表的字符串。
        System.out.print(depthString);
        System.out.println("Return: " + ret);

        return ret;
    }

    private String generateDepthString(int depth)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth ; i++)
        {
            sb.append("--");
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head.toString());

        ListNode resultNode = (new LCTest203_2()).removeElements6(head, 6 , 0);//初始递归深度为0，因为还没有进行递归的调用
        System.out.println(resultNode.toString());
    }
}
