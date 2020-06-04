package com.lkj.problem347;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution6
{
//    private class ComTemp
//    {
//        public int num , freq;
//        public ComTemp(int num , int freq)
//        {
//            this.num = num;
//            this.freq = freq;
//        }
//    }

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
         * 其实我们可以不用比较ComTemp对象，我们只比较数字的出现次数，即 tm 的value，
         * 然后我们根据出现次数找出前 tm中 出现次数前k多的元素后，再将tm的这些元素的key放入队列，
         * 最后我们将队列中保存的key出队到数组即可
         */
        //队列中保存数字，但是我们比较的时候比较的是数字的出现次数
        PriorityQueue<Integer> pq = new PriorityQueue<>(
//                new Comparator<Integer>()
//        {
//            @Override
//            public int compare(Integer o1, Integer o2)
//            {
//                //注意，队列中保存的是数字，但是我们想比较的是数字出现的次数，因此，我们必须要通过数字，获取tm中保存的其出现的次数
//                return tm.get(o1) - tm.get(o2);//出现次数越多越大
//            }


//        }
          //上面这一段，我们也可以使用 Lambda 表达式实现
                (o1, o2) -> tm.get(o1) - tm.get(o2)
        );

        for (int key : tm.keySet())
        {
            if(pq.size()<k)
            {
                pq.add(key);//将数字放入队列
            }
            else if(tm.get(pq.peek()) < tm.get(key))//如果新进数字的出现次数大于队首数字的出现次数，替换
            {
                pq.remove();//将队首数字移除
                pq.add(key);//将新进的数字放入队列
            }
        }

        int[] res = new int[pq.size()];
        int i = 0;
        while(!pq.isEmpty())
        {
            res[i] = pq.remove();//将队列中保存的出现次数前k 的数字放入数组
            i++;
        }
        return res;
    }
}
