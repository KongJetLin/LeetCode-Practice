package com.lkj.problem121;

/**
第二种方法是假设我们在第 i 天卖出股票，然后我们从 i=0 开始，求 0 到 i 天中股票的最小值 j，由于会在第 i 天卖出，
那么利润就是 price[i] - price[j] ， 然后我们不断更新最大的利润。
另外，由于有可能出现从 0 到 i 天中，股票的最小值就是 i 天，那么在第 i 天卖出股票的利润就是0。

 求 0 到 i 天中股票的最小值 j，我们用一个变量 minPrice 来记录对于每一个 i，从 0到i 的数组最小值，
 这样我们就不需要每次都遍历 0 到 i 来求 0 到 i 的最小值。
 因此，总的时间复杂度变为：O(n)
 */
public class LCTest121_2
{
    public int maxProfit(int[] prices)
    {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++)
        {
            /*
            如果当前值 prices[i] 是 0到i 位置的最小值，说明在第 i 天卖出股票的利润最多为0（即 在 0到i 天内不进行交易），
            那么今天不卖出股票。但是，我们必须要更新 0到i 天中股票的最小值！
             */
            if(prices[i]<minPrice)
                minPrice = prices[i];
            /*
            如果当前值 prices[i] 不是 0到i 位置的最小值，0到i 天股票最小值为 minPrice，那么第 i 天卖出股票利润肯定大于0，
            此时就计算第 i 天卖出股票最多能赚的钱，然后看看要不要更新 maxPrice。
            此时，不需要更新 0到i 天的股票最小值 minPrice.
             */
            else
                maxProfit = Math.max(maxProfit , prices[i] - minPrice);
        }
        return maxProfit;
    }
}
