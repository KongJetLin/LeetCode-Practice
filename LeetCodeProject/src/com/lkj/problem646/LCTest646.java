package com.lkj.problem646;

import java.util.Arrays;
import java.util.Comparator;

/** 动态规划（参考自己的总结：《最长上升子序列套路》），这题其实就是435《无重叠区间》的换皮题，唯一的区别这里又变成了严格增加。
 参考：435题解法

 解法：
 寻找最长的数对链，其实就是435题：删除若干区间，使得区间是连续的 这一题的逆向题，但是这里区间必须是严格递增的！
 同样定义状态： dp[i] 为以第 i+1 个数对结尾的，最长数对链的长度。

 先根据区间左边界的大小，将区间排序。 那么 dp[0到i-1] 我们已经计算出来，此时要计算 dp[i]，我们知道第 i+1 个区间（i下标对应的区间）
 肯定大于等于 0到i-1 区间（排序），那么我们寻找 0到i-1区间中，与i区间不重叠的区间，并找出以这些不重叠的区间结尾的数对链中最长的一个的长度length，
 那么此时，以第i个区间结尾的最长数对链的长度为：length+1.
 计算知道填充满dp[]数组即可！

 时间复杂度 : O(n^2)，需要两重循环。
 空间复杂度 : O(n)，dp 数组占用的空间。
 */
public class LCTest646
{
    //使得区间（数组表示，数组有2个元素）按区间左边界大小排列
    class myComparator implements Comparator<int[]>
    {
        @Override
        public int compare(int[] o1, int[] o2)
        {
            return o1[0] - o2[0];
        }
    }

    //判断2个区间是否相交，我们知道，前一个区间一定小于后一个区间（即前一个区间在后一个区间前面）
    //排序是为了使得后面方便判断2个数对的范围是否重叠，并且，后面的数对的范围一定排在前面的数对范围的后面，即不重叠的话，后面的数对对前面的数对一定是上升的！。
    private boolean isOverlapping(int[] arr1 , int[] arr2)
    {
        if(arr1[1] < arr2[0])//不加=，严格上升
            return false;
        return true;
    }

    public int findLongestChain(int[][] pairs)
    {
        if(pairs == null || pairs.length == 0)
            return 0;
        //1、定义动态数组
        int[] dp = new int[pairs.length];
        dp[0] = 1;//以第0个数对结尾的最长数对链长度为1

        //2、先对pairs中的数对链（一维数组），按数对左边界大小排序
        Arrays.sort(pairs , new myComparator());

        //2、遍历整个二维数组
        for(int i=1 ; i<pairs.length ; i++)
        {
            int tempMaxLength = 0;
            for(int j=0 ; j<i ; j++)
            {
                //如果 j 数对不与 i 数对重叠（i区间必然在j区间后面），那么i满足为j的上升数对
                if(!isOverlapping(pairs[j] , pairs[i]))//注意更后面的区间放在后一个参数
                {
                    //比较当前以 j 数对结尾的数对链的长度与maxLength的大小，为了计算以 0-(i-1)结尾的数对的最大长度
                    tempMaxLength = Math.max(dp[j] , tempMaxLength);
                }
            }
            //计算得到以 0-(i-1)结尾的数对的最大长度
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
