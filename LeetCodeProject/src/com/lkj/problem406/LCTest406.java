package com.lkj.problem406;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/** 贪心算法
思路：先将二维数组 (h,k) 按高度 h 降序排序，随后，从最高的数组开始，在同一高度的人中，按 k 值的升序排列，即将 (h,k) 插入到数组的 k 位置。
 如对于：[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 我们先取出高度最高为 7 的数组，将其放入 k 位置，因为 7 为最高，它“看不到”其他的较低的元素，那么我们先将其插入k位置： [[7,0] , [7,1]]
 随后取出高度为 6 的数组，因为此时6只能看见6或者7，而且对于7来说，它已经排序好，且它看不见6，那么我们只需要将6的数组插入到k位置即可，
 k表示 6 数组前面有 k 个数组，那么它此时放在数组的k位刚刚好，因为前面有 （0,1,2,...,k-1）共k个数组。
 .....

 时间复杂度：O(N^2)。排序使用了 O(NlogN) 的时间，将元素插入 LinkedList 用 O(n^2) 时间，总的时间复杂度是：O(n^2)
 空间复杂度：O(n)
 */
public class LCTest406
{
    public int[][] reconstructQueue(int[][] people)
    {
        //对于高度不同的数组，按高度降序，对于高度相同的数组，按k升序
        Arrays.sort(people, new Comparator<int[]>()
        {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                return o1[0]==o2[0] ? o1[1]-o2[1] : o2[0]-o1[0];
            }
        });

        /*
        将按高度排序好的 people (h,k)，从数组h由大到小，插入 k 位置
        我们使用 LinkedList 来进行插入，不使用数组，这样就不需要再插入的时候手动移动数组后面的元素
         */
        List<int[]> res = new LinkedList<>();
        for (int[] p : people)
        {
            res.add(p[1] , p);
        }

        return res.toArray(new int[people.length][2]);
    }
}
