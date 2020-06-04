package com.lkj.problem350;

import java.util.ArrayList;
import java.util.TreeMap;

public class LCTest350
{
    public int[] intersect(int[] nums1, int[] nums2)
    {
        //对于349题，349题数组的重复元素只算作一个，使用Set（不包含重复元素）
        //这里350题，重复元素算作多个，我们可以使用Map，键为元素，value为元素在数组中出现的次数
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int num : nums1)
        {
            if(tm.containsKey(num))
                tm.put(num , tm.get(num)+1);//将num的次数加1
            else
                tm.put(num , 1);//第一次出现设置为1
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int num : nums2)
        {
            /**
             * 由于nums2存在的num的数量，可能比 nums1 中num的数量多，也可能少。我们每次在nums2中找到一个 num，往ArrayList中添加num
             * 并将tm中num的数量减1，当tm中num的数量为0的时候，我们将tm中的num移除，以后就算再在nums2中找到num，我们也不往ArrayList中添加。
             * 这样就可以保证num 在 nums1 与 nums2 中数量不一致的时候，交集num的数量不会出错。
             */
            if(tm.containsKey(num))
            {
                arrayList.add(num);
                tm.put(num , tm.get(num)-1);
                //为什么要移除？因为tm中value可能小于0，在等于0的时候不移除，下一次在nums2中找到num的时候，tm中num的数量就=-1，出错
                if(tm.get(num) == 0)
                    tm.remove(num);
            }
        }

        int[] res = new int[arrayList.size()];
        for(int i = 0 ; i < arrayList.size() ; i ++)
            res[i] = arrayList.get(i);

        return res;
    }
}
