package com.lkj.problem347;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution3
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

    /**
     * 如果我们的 ComTemp 类没有实现 Comparable 接口，或者实现的 compareTo() 方法定义的大小比较不是我们需要的，
     * 我们可以定义一个类 MyComparator 实现 Comparator 接口，Comparator接口中有一个方法int compare(T o1, T o2)。
     * 这个方法返回值是int类型，如果返回值小于0，说明比较结果是o1<o2，如果返回值等于0，说明比较结果是o1=o2,如果返回值大于0，则说明比较结果是o1>o2。
     * 最后，我们创建队列对象的时候，将比较器 MyComparator 对象放入队列对象，那么放入队列的元素就会遵循 MyComparator定义的大小规则
     */
    //这种方法的好处是，如果我们比较的不是我们自己定义的类，而是java的类，而且java对这个类原来的比较方式不适合我们，
    // 那么我们就可以通过比较器重新定义java类的比较方式！
    private class MyComparator implements Comparator<ComTemp>
    {
        @Override
        public int compare(ComTemp o1, ComTemp o2)
        {
            // 设置频率越高ComTemp对象越大
            //如果 o1 的频率大于 o2 的频率，那么返回正数，说明 o1大于o2，满足！
            return o1.freq - o2.freq;
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

        //将 自定义的比较类的对象放入优先队列
        PriorityQueue<ComTemp> pq = new PriorityQueue<>(new MyComparator());
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
