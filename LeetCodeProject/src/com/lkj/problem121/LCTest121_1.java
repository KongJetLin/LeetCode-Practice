package com.lkj.problem121;

/**
 暴力法，时间复杂度是：O(n^2)，不推荐！
 */
public class LCTest121_1
{
    public int maxProfit(int[] prices)
    {
        int maxProfit = 0;

        //最后一天买入股票，无论怎么样利润都为0，不需要计算
        for (int i = 0; i < prices.length - 1; i++)
        {
            for (int j = i+1; j < prices.length ; j++)
            {
                if(prices[j] - prices[i] > maxProfit)
                    maxProfit = prices[j] - prices[i];
            }
        }
        return maxProfit;
    }
}
