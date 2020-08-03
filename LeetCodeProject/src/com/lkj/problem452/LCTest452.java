package com.lkj.problem452;

import java.util.Arrays;
import java.util.Comparator;

/** 动态规划（参考自己的总结：《最长上升子序列套路》）,这题其实就是435《无重叠区间》的换皮题，唯一的区别这里又变成了严格增加。
 参考：435解法

 定义状态：dp[i]-以第i+1个区间结尾的最长严格上升区间链的长度

 分析：
 如果2个或者多个气球的区间有重叠，那么一箭就可以射爆这些气球。那么对于给出的区间，我们就是要找到最长的严格上升的区间的长度，
 这些严格上升的区间是不相交的，其他区间必然与严格上升区间集合中的某一个相交。假设其他区间有一个不与严格上升区间集合中的某一个相交，
 那么这个区间也可以加入到严格上升区间的序列中来！
 最后，需要的箭数就是严格上升区间集合中区间的个数，只要射向这些区间，其他区间必然引爆！

 证明与严格上升区间中某一个区间相交的所有区间都可以一箭射爆：
         (DD) (EE)
 (A  A)---(B  B)---(C  C)
 如上的情况是不会出现的，如果D、E区间与B区间相交，但是D、E区间不想交，此时无法一箭射爆B、D、E。
 但是如果是这样，计算出来的最长严格上升区间应该是：(A A)---(D D)---(E E)---(C C)，而不是上面那个。
 因此与严格上升区间中某一个区间相交的所有区间都可以一箭射爆，否则这个严格上升区间还可以拆分！

 时间复杂度 : O(n^2)，需要两重循环。
 空间复杂度 : O(n)，dp 数组占用的空间。
 */
public class LCTest452
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
        if(arr1[1]<arr2[0])//严格上升
            return false;
        return true;
    }

    public int findMinArrowShots(int[][] points)
    {
        if(points == null || points.length == 0)
            return 0;

        int[] dp = new int[points.length];
        dp[0] = 1;

        //先将points按每个区间的左边界大小排序
        Arrays.sort(points , new myComparator());

        //遍历整个区间数组
        for(int i=1 ; i<points.length ; i++)
        {
            int tempMaxLength = 0;
            //寻找小于i的区间，找到以这个区间结尾的最长上升子区间的长度
            for(int j=0 ; j<i ; j++)
            {
                //先判断 i+1 个区间（下标为i的区间）是否与第 j+1 个区间重叠（下标为j的区间）
                if(!isOverlapping(points[j] , points[i]))//注意，比较的时候，较小的区间在前面
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

        return maxLength;
    }
}
