package com.lkj.problem234;
/** 递归法
 时间复杂度：O(n)
 空间复杂度：O(n)

 为什么递归的空间是O(n)?
 我们要理解计算机如何运行递归函数，在一个函数中调用一个函数时，计算机需要在进入被调用函数之前跟踪它在当前函数中的位置（以及任何局部变量的值），
 通过运行时存放在堆栈中来实现（堆栈帧）。在堆栈中存放好了数据后就可以进入被调用的函数。
 在完成被调用函数之后，他会弹出堆栈顶部元素，以恢复在进行函数调用之前所在的函数。
 在进行回文检查之前，递归函数将在堆栈中创建 n 个堆栈帧，计算机会逐个弹出进行处理。所以在使用递归时要考虑堆栈的使用情况。
 */
public class LCTest_2
{
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    //设置在外面，而不设置在recursivelyCheck()方法的参数，是为了recursivelyCheck递归到最后一个结点，frontNode才开始从第一个结点开始向后
    ListNode frontNode;

    public boolean isPalindrome(ListNode head)
    {
        frontNode = head;
        return recursivelyCheck(head);
    }

    //这个方法用于递归比较
    private boolean recursivelyCheck(ListNode head)
    {
        /*
        若递归到head =null，则说明所有结点都比较完毕，停止递归，返回给上一层一个true，
        此时回到倒数第一个结点，与第一个结点比较，返回比较结果给上一层倒数第2个结点，同时将第一个结点后移一位到第二个结点...
        这样便实现了回文比较
         */
        if(head != null)
        {
            //首先看一下底层的比较结果，如果底层都已经false，这一层没必要比较，返回false；否则再比较这一层
            if(!recursivelyCheck(head.next))
                return false;
            //比较当前结点与 frontNode
            if(head.val != frontNode.val)
                return false;
            //记得将 frontNode后移
            frontNode = frontNode.next;

            return true;
        }
        return true;//当head=null，返回给上一层一个true
    }
}
