package com.lkj.problem225;

import java.util.LinkedList;
import java.util.Queue;

/**
第二种解法，使用一个队列实现。
关键是入队的时候，使得q1出队入队 q1.size()-1次

 空间复杂度 O(n)
 时间复杂度：假设有n个元素
 入栈：需要入队出队n-1次，而队列如果使用链表实现，入队出队必然有一个是O(n)，一个是O(1)，
 那么总的时间复杂度是O(n^2)
 出栈：假设出队时间复杂度是O(1)，那么出栈时间复杂度就是O(1)
 peek()：O(1)
 isEmpty()：O(1)

 这里出栈不需要判断栈是否为空，因为这只是一个内部方法，我们实际使用会使用isEmpty()判断不为空后，再使用pop()
 */
public class LCTest225_2
{
    private Queue<Integer> q1;

    public LCTest225_2() {
        q1 = new LinkedList<>();
    }

    public void push(int x) {
        q1.add(x);
        /**
        每次入栈一个新元素，我们都让q1的队首元素出队，然后再将这个出队的元素添加到队列尾部。
         如此重复 q1.size()-1 次，就可以将后入队的元素排列到队列前面。（画个图就知道）
         */
        int time = q1.size()-1;
        while(time > 0)
        {
            q1.add(q1.remove());
            time--;
        }
    }

    public int pop() {
        return q1.remove();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
