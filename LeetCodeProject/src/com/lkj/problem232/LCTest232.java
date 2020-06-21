package com.lkj.problem232;

import java.util.Stack;

/** 用栈实现队列，对比225题，用队列实现栈
 * 参考文章：https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/yong-zhan-shi-xian-dui-lie-by-leetcode/
 方法1：使用2个栈 S1、S2 来实现，其中入队元素的时候，入栈到S1，通过S2进行翻转，出队元素元素也是从S1出队。
 注意：我们使用一个变量 front 来记录队首元素，这样方便取出队首元素。

 */
public class LCTest232
{
    private Stack<Integer> s1;
    private Stack<Integer> s2;
    int front;

    public LCTest232()
    {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** 入队
     复杂度分析：最差情况s1中原来有n个元素，这个n个元素出栈入栈2次，每一次出栈入栈都是 O(n) 的复杂度，一共是 O(4n)。
     而新元素入栈出栈各一次，O(2)，总的时间复杂度是 O(4n+2)=O(n)
     */
    public void push(int x)
    {
        //若s1为空，则直接放到s1中，并记录 front 队首元素（最先入队的就是队首元素）
        if(s1.isEmpty())
        {
            front = x;
            s1.push(x);
        }
        else
        {
            //若s1不为空，先将s1的所有元素出栈到s2，随后将新元素压入s2，最后将s2的所有元素出栈重新压入s1。
            //这时s1中的元素的出栈顺序队列的出队顺序
            while (!s1.isEmpty())
            {
                s2.push(s1.pop());
            }
            s2.push(x);
            while (!s2.isEmpty())
            {
                s1.push(s2.pop());
            }
        }
    }

    /** 出队
      当s1不为空的时候，直接出队 s1 即可，此时如果s1不为空，需要更新栈顶元素front
        当s1为空，如果出栈，抛出异常。
     复杂度分析：O(1)
      */
    public int pop()
    {
        if(!s1.isEmpty())
        {
            int res = s1.pop();
            //当剩下的s1不为空的时候，才更新front。
            // 如果s1为空，front没法更新，但是没有关系，因为此时根本没有队首元素。我们新入元素的时候更新front即可
            // 我们下面取出栈顶元素的时候，要判断其是否为空
            if(!s1.isEmpty())
            {
                front = s1.peek();
            }
            return res;
        }
        else
        {
            throw new RuntimeException("queue is empty");
        }
    }

    /** 获队首元素
      复杂度分析：O(1)
      */
    public int peek()
    {
        //需要判断s1不为空才有队首元素，否则front就是s1的最后一个已经出栈的元素，不是当前队首元素
        if(!s1.isEmpty())
            return front;
        else
            throw new RuntimeException("queue is empty");
    }

    /** 判断是否为空，s1不为空队列就不为空，s2只是在入队的时候翻转用  */
    public boolean empty()
    {
        return s1.isEmpty();
    }
}
