package com.lkj.problem765;

import java.util.HashMap;
import java.util.Map;

public class LCTest765_2
{
    /** 贪心算法+散列表优化
     时间复杂度：O(n)
     空间复杂度：O(n)
     */
    public int minSwapsCouples(int[] row)
    {
        int ret = 0;
        //用一个散列表记录某个值的元素在数组的位置
        Map<Integer , Integer> map = new HashMap();
        for (int i = 0; i < row.length ; i++)
            map.put(row[i] , i);

        //下面同样为每一个偶数位置的数寻找“情侣数”
        for (int i = 0; i < row.length ; i = i+2)
        {
            if(row[i+1] == (row[i]^1))
                continue;

            //通过散列表找到 row[i] 情侣数对应的数组中的位置：O(1)
            int index = map.get(row[i]^1);

            //交换 row[i+1] 与 row[index]
            int temp = row[index];
            row[index] = row[i+1];
            row[i+1] = temp;

            /*
            row[i+1]的数被交换到数组 index 索引的位置，而且它还没有被匹配，那么我们必须修改 row[i+1] 这个数在map中的位置值，
            从 (row[i+1] , i+1)，修改为 (row[i+1] , index)， 否则后面我们再次要使用到 row[i+1] 的时候，它的实际位置就是错误的！
            另外，我们在交换后修改原来数 row[i+1] 的位置，此时它被换到 row[index] 位置，实际上要修改 map 中 row[index] 的位置为index。
             */
            map.put(row[index] , index);

            ret++;
        }
        return ret;
    }

}
