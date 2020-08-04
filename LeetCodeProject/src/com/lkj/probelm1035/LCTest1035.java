package com.lkj.probelm1035;
/** 动态规划，参考自己《两个数组/字符串的最长公共子序列》的套路题总结 （对比718题）
分析：
 这一题其实就是《1143.2个字符串的最长公共子序列》的换皮题。
 2字符串的最长公共子序列中，有相同的字符，而且这些字符的相对位置都是一样的，这样才能组合成为一个公共子序列。
 比如字符串 abcde 与 字符串 fafbfc ，他们的各个子序列是：
 {a b c}
 {a b c}
 可以发现所有字符的相对位置是一样的，这样我们就可以将这些相同的字符连接起来，这就是这一题《不相交的线》的最大值。
 为什么是最大值？因为求出的子序列已经是2个字符的最长的子序列，找不到其他更长的子序列，也就是说，找不到其他
 更长的序列，使得序列字符的相对位置相同！

 对于《不相交的线》，其实就是把字符换为数字而已，就是求 2个数组的最长公共子序列（取的数字序列不一定要在数组中连续！）
 （子序列的数字/字符 不一定要在数组/字符串 中连续，但是子数组就必须要在数组中是连续的《718》）
 求出最长公共子序列的长度，就是可以连接的不相交的线的条数！

 思路：求最多的不相交的线--求数组的最长公共子序列--A[i]=B[j]，dp[i][j] = dp[i-1][j-1]+1;--A[i]！=B[j]，dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
 */
public class LCTest1035
{
    public int maxUncrossedLines(int[] A, int[] B)
    {
        int maxLength = 0;

        int[][] dp = new int[A.length+1][B.length+1];

        for (int i = 1; i <= A.length ; i++)
        {
            for (int j = 1; j <= B.length ; j++)
            {
                if(A[i-1] == B[j-1])
                    dp[i][j] = dp[i-1][j-1]+1;
                else

                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                maxLength = Math.max(maxLength , dp[i][j]);
            }
        }
        return maxLength;
    }
}
