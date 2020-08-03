package com.lkj.problem300;

/** 动态规划（参考自己的总结：《最长上升子序列套路》）
 * dp[i] 为以nums[i]结尾的最长上升子序列的长度，注意 nums[i] 必须被选取。
 * 状态转移方程：dp[i] = Max(dp[j])+1，其中 0<=j<i,nums[j]<nums[i]

 时间复杂度：O(0+1+2+...+n-1)=O(n^2)，这里表示每次遍历到的元素与前面的数进行比较的次数
 空间复杂度：O(n)
 */
public class LCTest300
{
    public int lengthOfLIS(int[] nums)
    {
        //特判
        if(nums == null || nums.length == 0)
            return 0;

        //dp[i] 为以第 i 个数字结尾的最长上升子序列的长度
        int[] dp = new int[nums.length];
        dp[0] = 1;//第一位元素的最长上升子序列长度为1

        /**
         * 特别注意，整个数组的最长上升子序列，不一定是包含最后一个元素的最长上升子序列，即 dp[nums.length-1] 不一定最大，
         * 这时因为是否是上升子序列，还与 nums[i] 的值有关，如果 nums[nums.length-1] 值较小，那么以它结尾的最长上升子序列
         * 的长度就很小。典型例子：[1,2,3,4,5,1]，最长上升子序列是5，但是dp[nums.length-1]=1
         * 因此需要一个变量来记录更新整个数组的最长上升子序列的值。
         */
        int maxLength = 1;//记录更新整个数组的最长上升子序列的值

        //循环计算dp数组的每一个值
        for (int i = 1; i < nums.length ; i++)
        {
            //maxLen 每一轮初始值必须为0，因为有可能对于 i，没有满足 nums[i] > nums[j]，此时其 dp[0...i-1]的最大值为0
            //用于记录 0-(i-1)中满足条件的 dp[]的最大值
            int tempMaxLength = 0;
            //遍历从0到i-1位置的元素，找到所有满足 nums[j]<nums[i]（一定满足0<=j<i）的元素j，更新dp[j]的最大值
            for (int j = 0; j < i ; j++)
            {
                if(nums[i] > nums[j])
                    tempMaxLength = Math.max(tempMaxLength , dp[j]);
            }
            dp[i] = tempMaxLength + 1;//更新当前i的dp[]值

            //计算得出 dp[i]后，更新记录更新整个数组的最长上升子序列的值
            maxLength = Math.max(maxLength , dp[i]);
        }
        return maxLength;
    }
}
