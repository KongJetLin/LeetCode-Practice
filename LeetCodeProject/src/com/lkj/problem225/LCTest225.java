package com.lkj.problem225;

import java.util.LinkedList;
import java.util.Queue;

/** 这里是用队列实现栈，对比232题，用栈实现队列
用2个队列实现。
 答案链接在：https://leetcode-cn.com/problems/implement-stack-using-queues/solution/yong-dui-lie-shi-xian-zhan-by-leetcode/
 指针解法关键是使用top来记录栈顶元素，同时出栈的时候，要记录最新的栈顶元素。

假设用链表实现的队列， 入队时间复杂度是O(1),出队时间复杂度是O(n)。
 那么
 入栈：O(1)
 出栈：O(n^2)，因为进行n-1次入队出队操作
 top：O(1)
 isEmpty()：O(1)

 */
public class LCTest225
{
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int top;//用于记录栈顶元素

    public LCTest225() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        //新元素直接入栈（入队）到队列1
        q1.add(x);
        //新入栈的元素在栈顶（队尾），将top设置为新入栈元素
        top = x;
    }

    public int pop() {
        //当q1的元素个数大于1的时候，将元素出队到q2,
        while(q1.size() > 1)
        {//注意设置此时的栈顶元素，q1的倒数第二个元素就是出栈栈顶元素后，新的栈顶元素，需要记录最新的栈顶元素到top
            top = q1.remove();
            q2.add(top);
        }
        //q1的最末尾的元素就是前面栈顶元素，将其出队，就是将之前的栈顶元素移除
        int outTop = q1.remove();
        //为了避免q1与q2的元素来回交换，将q1与q2互换
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return outTop;
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
