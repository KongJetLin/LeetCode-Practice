package com.lkj.problem347;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution2
{
    private class ComTemp implements Comparable<ComTemp>
    {
        public int num , freq;
        public ComTemp(int num , int freq)
        {
            this.num = num;
            this.freq = freq;
        }

        @Override
        public int compareTo(ComTemp anotherComTemp)
        {
            //优先队列是最小堆实现，定义频率越高优先级越高
            if(this.freq > anotherComTemp.freq)
                return 1;
            else if(this.freq < anotherComTemp.freq)
                return  -1;
            else
                return 0;
        }

    }

    public int[] topKFrequent(int[] nums, int k)
    {
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for (int num : nums)
        {
            if(tm.containsKey(num))
                tm.put(num , tm.get(num)+1);
            else
                tm.put(num , 1);
        }

        /**
         * 我们使用java提供的优先队列，注意，java提供的优先队列是最小堆实现的，即会将优先级最小的元素放在队首
         * 因此，我们在定义优先级大小的时候，定义出现频率freq越高优先级越大，这样，我们每次才能把优先级最低（频率最低）
         * 的元素替换出队列，换进来优先级较高（出现频率较高）的元素
         *
         * java的优先队列，入队是 add() 方法，出队方法是 remove() ，获取队首元素方法是 peek()
         */

        PriorityQueue<ComTemp> pq = new PriorityQueue<>();
        for (int key : tm.keySet())
        {
            if(pq.size()<k)
            {
                pq.add(new ComTemp(key, tm.get(key)));
            }
            else if(pq.peek().freq < tm.get(key))
            {
                pq.remove();
                pq.add(new ComTemp(key , tm.get(key)));
            }
        }

        int[] res = new int[pq.size()];
        int i = 0;
        while(!pq.isEmpty())
        {
            res[i] = pq.remove().num;
            i++;
        }
        return res;
    }
}
