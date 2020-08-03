package com.lkj.problem435;

import java.util.Arrays;
import java.util.Comparator;

/** 动态规划（参考自己的总结：《最长上升子序列套路》）
参考：1、https://lucifer.ren/blog/2020/06/20/LIS/；2、https://leetcode-cn.com/problems/non-overlapping-intervals/solution/wu-zhong-die-qu-jian-by-leetcode/

 如果我们将前面区间的结束和后面区间的开始结合起来看，其就是一个非严格递增序列。而我们的目标就是删除若干区间，从而剩下最长的非严格递增子序列。
 这不就是寻找《最长上升子序列》的题吗？只不过上面是严格递增，这不重要，就是改个符号的事情。
 《最长上升子序列》这题，可以看成是删除了若干数字，然后剩下最长的严格递增子序列。 这就是抽象的力量，这就是套路。

 解法：当成寻找最长上升子区间来做！
 首先，定义 dp[i] 为 以第(i+1)个区间结尾的最长上升子区间的长度（非严格递增）。
 现在，当计算 dp[i+1] 时，我们不能只考虑 dp[i] 的值，因为前面的 i+1 个区间都可能与 第 i+2 个区间发生重叠。
 因此，我们需要考虑能够使得 j≤i 且与第 i+2 个区间不发生重叠的所有区间中 dp[j] 中的最大值。状态转移方程如下：
                         dp[i+1]=max(dp[j])+1 （套路）

当然，为了寻找第1到i+1个区间中，与 i+2 个区间不重叠的区间，我们先按区间的开头，将所有的区间排序。


 时间复杂度 : O(n^2)，需要两重循环。
 空间复杂度 : O(n)，dp 数组占用的空间。
 还有一种贪心算法：O(nlogn)，这里不算了
 */
public class LCTest435
{
    //这个比较器，比较的是二维数组内的一维数组元素，这些一维数组元素那一个第一个元素比较大，就排在前面
    class myComparator implements Comparator<int[]>//注意，比较的是二维数组中的元素（一维数组）
    {
        @Override
        public int compare(int[] o1, int[] o2)
        {
            return o1[0] - o2[0];
        }
    }

    //判断2个区间是否重叠的方法
    //排序是为了使得后面方便判断2个区间的范围是否重叠，并且，后面的区间的范围一定排在前面的区间范围的后面，即不重叠的话，后面的区间对前面的区间一定是上升的！。
    private boolean isOverlapping(int[] arr1 , int[] arr2)
    {
        if(arr1[1]<=arr2[0])
            return false;
        return true;
    }

    public int eraseOverlapIntervals(int[][] intervals)
    {
        if(intervals == null || intervals.length == 0)
            return 0;

        int[] dp = new int[intervals.length];
        dp[0] = 1;

        //先将intervals按每个区间的左边界大小排序
        Arrays.sort(intervals , new myComparator());

        //遍历整个区间数组
        for(int i=1 ; i<intervals.length ; i++)
        {
            int tempMaxLength = 0;
            //寻找小于i的区间，找到以这个区间结尾的最长上升子区间的长度
            for(int j=0 ; j<i ; j++)
            {
                //先判断 i+1 个区间（下标为i的区间）是否与第 j+1 个区间重叠（下标为j的区间）
                if(!isOverlapping(intervals[j] , intervals[i]))//注意，比较的时候，较小的区间在前面
                {
                    //找到一个区间不与i重叠的j，我们更新 0-j 区间中最长上升子区间的长度
                    tempMaxLength = Math.max(tempMaxLength , dp[j]);
                }
            }
            //已经找到 0-j 区间中最长上升子区间的长度，且这些上升子区间的末尾区间不与i区间重写。此时就可以计算得到 以i区间结尾的最长上升子区间 的长度
            dp[i] = tempMaxLength+1;
        }

        int maxLength = dp[0];
        //遍历dp[i]，找到其中最长上升子区间的长度
        for(int i = 1; i<dp.length ; i++)
        {
            maxLength = Math.max(dp[i] , maxLength);
        }

        return intervals.length - maxLength;//删除个数等于 intervals 区间个数减去最长上升子区间的长度
    }
}
