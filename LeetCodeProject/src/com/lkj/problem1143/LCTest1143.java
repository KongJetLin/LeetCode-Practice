package com.lkj.problem1143;
/** 动态规划，参考自己《两个数组/字符串的最长公共子序列》的套路题总结 （对比718题）
 * 理解较难，参考文章：https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/

 首先，定义 2个字符串中最长公共子序列为lcs（注意，lcs的字符在字符串中不一定要连续，这点很重要！）
 dp[i][j]的定义：以A[i]、B[j]结尾的2个字符串序列中lcs的长度。

 状态方程为：
 1）如果 A[i] == B[j]，dp[i][j] = dp[i - 1][j - 1] + 1
 2）否则，dp[i][j] = Max(dp[i-1][j] , dp[i][j-1])

 分析：
 （1）由于2个字符串lcs的字符不一定要连续，因此A[i]、B[j]不一定在（以A[i]、B[j]结尾的2个字符串序列的lcs）lcs中，
    有可能都在：A[i]=B[j]；当 A[i]!=B[j] 的时候，有可能 A[i]、B[j]中只有一个在lcs，也有可能都不在！

（2）当 A[i]=B[j] 的时候，A[i]、B[j]都在lcs（以A[i]、B[j]结尾的2个字符串序列的lcs）中，那么此时要求lcs，
 我们只需要求以A[i-1]、B[j-1]结尾的2个字符串序列的lcs，然后我们将 A[i]、B[j]接到lcs的后面，
 这样 以A[i]、B[j]结尾的2个字符串序列的lcs =  以A[i-1]、B[j-1]结尾的2个字符串序列的lcs+1；

（3）当 A[i]!=B[j] 的时候，有可能 A[i]、B[j]中只有一个在lcs（以A[i]、B[j]结尾的2个字符串序列的lcs），也有可能都不在！
     3.1 若A[i]在而B[i]不在，那么 以A[i]、B[j]结尾的2个字符串序列的lcs，其实就是 以A[i]、B[j-1]结尾的2个字符串序列的lcs；
     3.2 若A[i]不在而B[i]在，那么 以A[i]、B[j]结尾的2个字符串序列的lcs，其实就是 以A[i-1]、B[j]结尾的2个字符串序列的lcs；
     3.3 若A[i]、B[i]都不在，那么 以A[i]、B[j]结尾的2个字符串序列的lcs，其实就是 以A[i-1]、B[j-1]结尾的2个字符串序列的lcs；

 其实我们只需要对比 dp[i][j-1]、dp[i-1][j]、dp[i-1][j-1]，哪个比较大即可。
 另外由于 dp[i-1][j-1] 肯定小于或等于 dp[i][j-1]、dp[i-1][j]，因此它可以不用比较，我们寻找最大的即可！
（这一段比较晦涩，结合上面的文章，想象一下整个图！）

 如：A[i]在而B[i]不在，A[i]=d,B[j]=e，此时求dp[3][2]，只需要求dp[3][1]（dp[3][1]=2>dp[2][2]=1，即dp[i][j-1] > dp[i-1][j]）
 A:{a , b , c , d}
 B:{a , d , e}

 A[i]不在而B[i]在的情况也是一样的！

 复杂度分析：
 时间复杂度：O(m * n)，其中 m 和 n 分别为 A 和 B 的 长度。
 空间复杂度：O(m * n)，其中 m 和 n 分别为 A 和 B 的 长度。
 */
public class LCTest1143
{
    //代码参考718的代码分析
    public int longestCommonSubsequence(String text1, String text2)
    {
        int maxLength = 0;

        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for (int i = 1; i <= text1.length() ; i++)
        {
            for (int j = 1; j <= text2.length() ; j++)
            {
                if(text1.charAt(i-1) == text2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                /**
                 要是是在记不住上面的思想，就记住：
                 由于 A[i]!=B[j]，且lcs的字符不一定连续，此时 （以 A[i]、B[j]结尾的字符串组合）的lcs存在，且与前面 （所有长度更短的字符串组合）的最大的lcs相等。
                 那么我们要寻找比 （以 A[i]、B[j]结尾的字符串组合） 更短的字符串组合，找出这些字符串组合的lcs中的最大值，
                 并将这些字符串组合的lcs的最大值赋予（以 A[i]、B[j]结尾的字符串）的lcs。
                 由于这些更短的字符串组合的lcs的最大值，应该出现在 （以 A[i-1]、B[j]结尾的字符串组合）或者 （以 A[i]、B[j-1]结尾的字符串组合），
                 其他的字符串组合的lcs必然必然比这两个组合短，因此我们只需要求这两个组合的lcs最大值即可！
                 （想象一下这个过程）
                 */
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                maxLength = Math.max(maxLength , dp[i][j]);
            }
        }
        return maxLength;
    }
}
