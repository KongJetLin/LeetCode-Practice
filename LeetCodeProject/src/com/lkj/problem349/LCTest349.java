package com.lkj.problem349;

import java.util.ArrayList;
import java.util.TreeSet;

public class LCTest349
{
    public int[] intersection(int[] nums1, int[] nums2)
    {
        //创建一个TreeSet集合用于保存数组中的元素
        TreeSet<Integer> ts = new TreeSet<>();
        for (int num : nums1)//这里消耗O(n)的时间
        {//将nums1中的元素添加到Set集合中，并实现去重
            ts.add(num);//这里消耗 O(logn)的时间，消耗O(n)的空间
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int num : nums2)
        {
            //如果ts中包含num，那么说明num2中一个元素num与nums1中的元素相同，此时将这个相同的元素添加到ArrayList
            //另外。nums2中可能有多个nums，为了避免多次添加，将ts中的num移除
            //当然，我们也可以将ArrayList换为 Set，这样就不会添加重复的元素吗，也就不需要将num从ts中移除
            if(ts.contains(num))
            {
                arrayList.add(num);
                ts.remove(num);
            }
        }

        int[] res = new int[arrayList.size()];
        for (int i = 0; i < res.length ; i++)
        {
            res[i] = arrayList.get(i);
        }
        return res;
    }
}
