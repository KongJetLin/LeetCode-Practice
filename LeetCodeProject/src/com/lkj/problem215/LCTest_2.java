package com.lkj.problem215;

import java.util.PriorityQueue;

/**
 * 最小堆实现
 * 时间复杂度：O(NlogK)
 * 空间复杂度：O(k)
 */
public class LCTest_2
{
    public int findKthLargest(int[] nums, int k)
    {
        //使用 Lambda 表达式的时候注意前后使用泛型指定操作类型，否则是 Object类型
        PriorityQueue<Integer> pq = new PriorityQueue<>(k , (a,b) -> a-b);

        for (int i = 0; i < k ; i++)
        {
            pq.offer(nums[i]);
        }

        for (int i = k; i < nums.length ; i++)
        {
            if(nums[i] > pq.peek())
            {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }
}
