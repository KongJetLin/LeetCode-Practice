package com.lkj.problem232;

import java.util.Stack;

/** 用栈实现队列，对比225题，用队列实现栈
 * 参考文章：https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/yong-zhan-shi-xian-dui-lie-by-leetcode/
 方法2：使用2个栈 S1、S2 来实现，其中入栈元素的时候，入栈到S1，出栈的时候，从S2出栈。
 若S2没有元素，则将 s1 中的元素出栈放入s2。然后从s2出队。

 注意：我们使用一个变量 frontS1 来记录s1栈最下面的元素，即最先进入s1的元素。
 注意，与方法1 不同，frontS1 不是队首的元素，而是栈s1最下面的元素！（这个元素与s2无关。）

 */
public class LCTest232_2
{
    private Stack<Integer> s1;
    private Stack<Integer> s2;
    int frontS1;

    public LCTest232_2()
    {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** 入队
    直接把元素放到 s1，如果s1为空，则记录s1最下面的元素（即队列在s1段最前面的元素，这个元素与s2无关）
     复杂度是 O(1)
     */
    public void push(int x)
    {
        if(s1.isEmpty())
            frontS1 = x;
        s1.push(x);
    }

    /** 出队
        出队是在s2出队，而与s1无关，我们不需要更新s1的元素 frontS1。
     复杂度：
     在最坏情况下，s2 为空，算法需要从 s1 中弹出 n个元素，然后再把这 n 个元素压入 s2，在这里n代表队列的大小。
     这个过程产生了 2n 步操作，时间复杂度为 O(n)。但当 s2 非空时，算法就只有 O(1) 的时间复杂度。
     因此这整个过程的均摊复杂度是 O(1)，因为对于每个元素，实际上只花费出栈s1，入栈s2 O(2) 的时间。
     */
    public int pop()
    {
        //若s2不为空，出s2的队首
        if(!s2.isEmpty())
        {
            return s2.pop();
        }
        else
        {
           //若s2为空，将当s1不为空的时候，将s1的全部元素压入s2
           if(!s1.isEmpty())
           {
               while (!s1.isEmpty())
               {
                   s2.push(s1.pop());
               }
               return s2.pop();//出对s2的栈顶元素
           }
           else
           {//s1、s2 都为空，抛出异常
               throw new RuntimeException("queue is empty");
           }
        }
    }

    /** 队首元素
    如果s2不为空，队首元素是s2。否则队首元素是s1的最底层元素frontS1。
     O(1)的时间复杂度。
     */
    public int peek()
    {
        if(!s2.isEmpty())
            return s2.peek();
        else
        {
            if(!s1.isEmpty())
                return frontS1;
            else
                throw new RuntimeException("queue is empty");
        }
    }

    /**
    判断是否为空，当s1、s2都为空，队列才是空的。
     */
    public boolean empty()
    {
        return s1.isEmpty()&&s2.isEmpty();
    }
}
