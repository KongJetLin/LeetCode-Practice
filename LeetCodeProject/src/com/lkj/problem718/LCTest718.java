package com.lkj.problem718;

/** 动态规划，参考自己《两个数组/字符串的最长公共子序列》的套路题总结
 dp[i][j]的定义：以A[i]结尾的数组集合 与 以B[j]结尾的数组集合中 的最长的公共数组的长度（注意，这些公共数组必然也是以 A[i]，B[j]结尾！）。
 状态方程为：
 1）如果 A[i] == B[j]，dp[i][j] = dp[i - 1][j - 1] + 1
 2）否则，dp[i][j] = 0

 我们遍历2个数组，对于A[i]、B[j]，如果A[i] == B[j]，说明以A[i]结尾的数组与以B[j]结尾的数组有公共子数组，
 由于这里公共子数组必须是连续的.
 （1）当 A[i]=B[j] 的时候，我们想求以A[i]、B[j]结尾的2个数组的最长的公共子数组，我们需要知道
 以以A[i-1]、B[j-1]结尾的2个数组的最长的公共子数组，然后将 A[i]、B[j] 分别连在 A[i-1]、B[j-1] 后面，
 这样以A[i]、B[j]结尾的2个数组的最长的公共子数组的长度就是 以A[i-1]、B[j-1]结尾的2个数组的最长的公共子数组长度+1；

 （2）当A[i]!=B[j] 的时候，那么以A[i]、B[j]结尾的2个数组的所有子数组都不相等（注意，这些子数组必然也是以 A[i]，B[j]结尾！），
    此时，dp[i][j]=0

 复杂度分析：
 时间复杂度：O(m * n)，其中 m 和 n 分别为 A 和 B 的 长度。
 空间复杂度：O(m * n)，其中 m 和 n 分别为 A 和 B 的 长度。
 */
public class LCTest718
{
    public int findLength(int[] A, int[] B)
    {
        /*
        技巧：由于对于每一个 dp[i][j]，我们需要知道 dp[i-1][j-1]，当 i=0，j=0的时候，就会出现数组越界。
        因此我们必须使得i、j从1开始，但是这样我们就必须要额外初始化 dp[0][0]、dp[0][1]、dp[1][0]。
        我们干脆将dp数组横纵的长度都加1，使得i,j从1开始，这样就不会越界，而且对于 dp[1][1]，dp[0][0]默认初始化为0，
        如果 A[0]=B[0]，那么dp[1][1]=dp[0][0]+1=1，这样也是满足的，而且不需要初始化！
         */
        int maxLength = 0;
        int[][] dp = new int[A.length+1][B.length+1];

        for(int i=1 ; i<=A.length ; i++)
        {
            for (int j = 1; j <=B.length ; j++)
            {
                //注意，为了方便初始化，dp[i][j]表示的是原来的dp[i-1][j-1]，那么此时对于i,j，应该比较的是A[i-1],B[j-1]，
                // 否则i,j最后会越界！
                if(A[i-1] == B[j-1])
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = 0;
                //计算出 dp[i][j] 后，我们再求所有 dp数组的最大值
                maxLength = Math.max(maxLength , dp[i][j]);
            }
        }
        return maxLength;
    }
}
