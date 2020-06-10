package com.lkj.problem53;

/** 动态规划
暴力遍历所有子序列，可以得到最大值，复杂度是O(n^2)

 动态规划 O(n)
 对于数组 arr = {6,-3,-2,7,-15,1,2,2}
 1）dp[0] 就是以下标0为结尾的子序列的最大和，显然只有一个，我们记为 dp[0] = arr[0] = 6，此时maxSum = dp[0]；
 2）dp[1] 就是以下标1为结尾的子序列的最大和，那么 dp[1] = Max{dp[0]+arr[1] , arr[1]}，既如果 dp[0]<0 ，dp[1]=arr[1]，否则dp[1]=dp[0]+arr[1]；
 3）dp[n] 就是以下标n为结尾的子序列的最大和，那么 dp[n] = Max{dp[n-1]+arr[n] , arr[n]}

 这里 ，dp[m-1]就是遍历所有以 m-1 个数为结尾的子序列，找出最大值。
 此时所有以m为结尾的子序列的最大和，要么是以m-1为结尾的子序列加 nums[m]，此时dp[m-1]>0；要么就是 nums[m]，dp[m-1]<=0。

 */

public class LCTest53
{
    public int maxSubArray(int[] nums)
    {
        //1、定义状态：定义数组，用于存储以下标为 0到n-1 为结尾的子序列的最大和
        int[] dp = new int[nums.length];

        //2、状态方程：dp[n] = Max{dp[n-1]+arr[n] , arr[n]}

        //3、初始化
        dp[0] = nums[0];

        //通过遍历，找到每一个dp[n]
        int max = dp[0];
        for (int i = 1; i < nums.length ; i++)
        {
            if(dp[i-1]>0)
                dp[i] = dp[i-1]+nums[i];
            else
                dp[i] = nums[i];

            if(dp[i]>max)
                max = dp[i];
        }

        //4、最终条件
        return max;
    }
}
