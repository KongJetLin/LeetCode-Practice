package com.lkj.problem673;

/** 动态规划（参考自己的总结：《最长上升子序列套路》）
 其实和《300 最长上升子序列》一样，只不过这一题还要求最长递增子序列的个数。

 我们使用动态规划求出最长递增子序列：dp[i]表示以 nums[i] 结尾的递增子序列的最大长度（这一部分参考300题解法）
 此时我们还想知道最长递增子序列的个数，我们创建另一个动态数组：count[i] 表示以 num[i] 结尾的最长递增子序列的个数。

 我们求解 dp[i] 的时候，我们会找到 nums[j]<nums[i] 且0<=j<i 的nums[j]，此时，从 nums[j]到nums[i]也是递增的，
 那么我们求解 dp[0]-dp[i-1] 中最大的值，就可以得到 dp[i]=Max(dp[j])+1,nums[j]<nums[i] 且0<=j<i.
 在找到 dp[0]-dp[i-1] 中最大的值 tempMaxLength 的时候，我们再计算 dp[0]-dp[i-1] 有多少个值等于最大值 tempMaxLength，
 那么以 num[i] 结尾的最长递增子序列就有多少个。

 复杂度分析：
 时间复杂度：O(n^2)
 空间复杂度：O(n)

 这一题使用贪心算法可将时间复杂度降低到 O(nlogn)
 */
public class LCTest673
{
    public static int findNumberOfLIS(int[] nums)
    {
        //1、特判
        if(nums == null || nums.length == 0)
            return 0;
        //2、定义2个数组
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int[] count = new int[nums.length];
        count[0] = 1;

        //3、循环，计算dp[i]与count[i]
        for (int i = 1; i < nums.length ; i++)
        {
            int tempMaxLength = 0;
            for (int j = 0; j < i ; j++)
            {
                if(nums[i]>nums[j])//严格递增
                {
                    tempMaxLength = Math.max(tempMaxLength , dp[j]);//找到以 nums[0到i-1]结尾的最长上升子序列的长度集合，并计算出其中的最大值
                }
            }
            dp[i] = tempMaxLength+1;//计算得到dp[i]

            //计算 dp[0]-dp[i-1] 有多少个值等于最大值 tempMaxLength，求count[i]
            int countDp = 0;
            for (int j = 0; j < i ; j++)
            {
                if(nums[i]>nums[j] && dp[j] == tempMaxLength)//注意必须是满足 nums[i]>nums[j] 的 nums[j] 才能计算进来
                    countDp += count[j];//加上以 nums[j] 结尾的最长上升子序列的个数
            }
            /**
            （1）如果countDp=0，说明 nums[i] 前面并没有满足 nums[i]>nums[j] 的j，那么此时 以nums[i] 结尾的最长递增子序列的长度为1 （tempMaxLength=0），
                 即nums[i]自身，那么此时 以nums[i] 结尾的最长递增子序列的的个数 count[i]=1；
             （2）如果countDp != 0，说明 nums[i] 前面存在满足 nums[i]>nums[j] 的j，那么此时 以nums[i] 结尾的最长递增子序列的长度为 tempMaxLength+1 （tempMaxLength!=0），
                 那么我们找到：nums[j]，以nums[j]结尾的最长递增子序列的长度=tempMaxLength，且要满足 nums[i]>nums[j]，那么以 nums[j] 结尾的最长递增子序列的个数为count[j]，
                那么此时 nums[i]可以跟在nums[j]后面，形成一个更长的子序列， 那么以nums[i] 结尾的最长递增子序列的的个数 count[i] = countDp，即所有满足条件的 nums[j] 的count[j] 相加。
             */
            count[i] = (countDp==0?1:countDp);
        }

        //先找出最长递增子序列的长度
        int maxLength = dp[0];
        for (int i = 1; i < dp.length ; i++)
        {
            if(maxLength<dp[i])
                maxLength = dp[i];
        }

        //找到dp中值为maxLength的dp[i]，此时以 nums[i] 结尾的最长递增子序列就是整个数组中的最长递增子序列，
        // 而 nums[i] 结尾的最长递增子序列有 count[i] 个，那么将 count[i] 加到结果中即可
        int ret= 0;
        for (int i = 0; i < dp.length ; i++)
        {
            if(maxLength == dp[i])
                ret += count[i];
        }

        return ret;
    }

    public static void main(String[] args)
    {
        int arr[] = {5,5,5,5,5};
        findNumberOfLIS(arr);
    }
}
