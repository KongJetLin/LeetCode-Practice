package com.lkj.problem5;

/** 动态规划
 第 1 步：定义状态：dp[i][j] 表示子串 s[i..j] 是否为回文子串，这里子串 s[i..j] 定义为左闭右闭区间，可以取到 s[i] 和 s[j]

 第 2 步：思考状态转移方程
    dp[i][j] = S[i]=S[j] and dp[i+1][j-1]
 即要判断 S[i,j] 是否是回文子串，那么首先它的首尾字符必须相等，如果首尾字符不相等，必然不是回文子串；其次它的内部子串 S[i+1,j-1]必须也是回文子串。
 此时我们需要考虑临界条件，即S[i+1,j-1]必须存在，那么S[i+1,j-1]最小的时候，他就是一个字符，即 i+1>=j-1，即 j>=i+2.
 当 j=i+1 的时候，即 S[i,j]只有2个字符，只需要判断首尾字符是否相等，既可以判断S[i,j]是不是回文子串
 当 j=i 的时候，S[i,j] 必然是回文子串。

 第 3 步：考虑初始化
 初始化的时候，单个字符一定是回文串，即当 j=i 的时候，S[i,j] 必然是回文子串，因此把对角线先初始化为 true，即 dp[i][i] = true

 第 4 步：考虑输出
 只要一得到 dp[i][j] = true，就记录子串的长度和起始位置，没有必要截取，这是因为截取字符串也要消耗性能，
 记录此时的回文子串的「起始位置」和「回文长度」即可。

 第 5 步：考虑优化空间
 不优化

 注意事项：总是先得到小子串的回文判定，然后大子串才能参考小子串的判断结果，即填表顺序很重要。
 也就是说，dp[i+1][j-1] 必须先判断，这样才能判断dp[i][j]。具体分析如下代码

 时间复杂度：O(N^2)
 空间复杂度：O(N^2)
 */
public class LCTest5
{
    public static String longestPalindrome(String s)
    {
        //s的长度为1，只有一个字符，必然是回文子串，最长回文子串就是这个字符。如果s没有字符，也返回s
        if(s.length() <= 1)
            return s;

        int len = s.length();
        //用于存储 dp[i][j]是不是回文子串，是就存储true，否则存储false
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();//将s转换为char数组，用于后面判断 chars[i]是否等于chars[j]

        //1、首先，当i=j的时候，必然是回文子串，先给所有i=j的初始条件赋值
        for (int i = 0; i < len ; i++)
        {
            dp[i][i] = true;
        }

        int maxLen = 1;//当前最大回文子串长度为1
        int begin = 0;

        /**2
        下面，我们从小到大查找所有子串。
         这里，j 被设定为右端，i被设定为左端起点。外循环用于设置右端j的值，让j从1开始；内循环用于使得i逐渐增加到j-1.具体如下：
         [0,1]  j=1
         [0,2] , [1,2]  j=2
         [0,3] , [1,3] , [2,3]  j=3
         [0,4] , [1,4] , [2,4] , [ 3,4]  j=4
         ....
         我们可以看到，对于每一个 [i,j]，由于j是在不断增大的，即以 j-1为右端的所有 i 不同取值的情况前面必然已经判断过，
         那么 [i-1 , j-1]必然已经判断过，这就满足先判断小子串，再判断大子串的原则。

         另外，上面是在 j>=i+2 的情况下进行说明，对于 j=i 的情况，全是回文子串，上面已经判断；
         对于 j=i+1 的情况，我们只需要判断 chars[i] = chars[j] 即可
         */

        for (int j = 1; j < len; j++)
        {
            for (int i = 0; i < j; i++)
            {
                /* j<i+2 时， dp[i+1][j-1]不存在，需要另外判断
                1）这里i必然小于j，因为i=j的情况上面判断了
                2）这里要判断 j>=i+2 不成立的情况，即 j<i+2 的情况，由于j必然大于i，那么这里只需要判断 j=i+1的情况，即2个字符的情况即可
                 */
                if(j == i+1)
                    dp[i][j] = (chars[i] == chars[j]);//当2个字符相等的时候，2个字符的子串就是回文子串
                else
                {
                /*
                j>=i+2 的情况，dp[i+1][j+2] 必然存在，那么需要先判断 chars[i] == chars[j] 是否成立，
                成立时，dp[i][j] 就取决于 dp[i+1][j-1]；不成立，则 S[i,j]不是回文子串， dp[i][j]=false
                */
                    if(chars[i] != chars[j])
                        dp[i][j] = false;
                    else
                        dp[i][j] = dp[i+1][j-1];
                }

                //每次获得一个 dp[i][j]=true，就判断这个 S[i,j]的长度是否比原来的最大回文子串长度达，是的话就替换最大长度为S[i,j]长度
                if(dp[i][j] && j-i+1>maxLen)//注意，S[i,j]子串长度是j-i+1
                {
                    begin = i;//重新设置子串开始值为i
                    maxLen = j-i+1;//重新设置子串长度
                }
            }
        }
        //注意 substring 方法最后一个字符不会截取
        return s.substring(begin , begin+maxLen);
    }

    public static void main(String[] args)
    {
        System.out.println(longestPalindrome("sdcbaabcfr"));
    }
}

