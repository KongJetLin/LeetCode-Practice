package com.lkj.problem674;

/** 动态规划
 * 时间复杂度：O(n)
 */
public class LCTest674
{
    public int findLengthOfLCIS(int[] nums)
    {
        if(nums==null || nums.length==0)
            return 0;

        int[] dp = new int[nums.length];
        dp[0] = 1;

        int maxLen = 1;
        for (int i = 1; i < nums.length; i++)
        {
            if(nums[i]>nums[i-1])
                dp[i] = 1 + dp[i-1];
            else
                dp[i] = 1;
            maxLen = Math.max(maxLen , dp[i]);
        }
        return maxLen;
    }
}
