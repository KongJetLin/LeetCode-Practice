package com.lkj.problem96;

public class LCTest96
{
    public int numTrees(int n)
    {
        //定义一个长度为 n+1 的数组，用于存储 dp[1] - dp[n]
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        //通过2层循环，i=2,...,n 找到每一个dp[i]
        for (int i = 2; i <= n ; i++)
        {
            for (int j = 0; j <= i-1 ; j++)
            {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }

        return dp[n];
    }
}
