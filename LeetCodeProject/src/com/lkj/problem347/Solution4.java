package com.lkj.problem347;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution4
{
    private class ComTemp
    {
        public int num , freq;
        public ComTemp(int num , int freq)
        {
            this.num = num;
            this.freq = freq;
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

        //如果我们不想自定义比较类，可以直接使用匿名内部类的方法实现Comparator接口
        PriorityQueue<ComTemp> pq = new PriorityQueue<>(new Comparator<ComTemp>()
        {
            @Override
            public int compare(ComTemp o1, ComTemp o2)
            {
                return o1.freq - o2.freq;
            }
        });

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
