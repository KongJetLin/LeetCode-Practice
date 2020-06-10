package com.lkj.problem5;

/** 中心扩展
 状态转移链：  P(i,j) ← P(i+1,j−1) ← P(i+2,j−2)←⋯←某一边界情况
 可以发现，所有的状态在转移的时候的可能性都是唯一的。也就是说，我们可以从每一种边界情况开始「扩展」，也可以得出所有的状态对应的答案。

 边界情况即为子串长度为 11 或 22 的情况。（为什么不仅仅是1，因为如果仅仅是1向2遍扩展，那么所有子串长度为偶数的情况就会被忽略）
 我们枚举每一种边界情况，并从对应的子串开始不断地向两边扩展。如果两边的字母相同，我们就可以继续扩展，直到不满足为止。

 时间复杂度：O(N^2)
 空间复杂度：O(1)
 */
public class LCTest5_2
{
    public static String longestPalindrome(String s)
    {
        if(s.length() <= 1)
            return s;

        //设置子串初始首尾
        int start = 0;
        int end = 0;

        //从i=0开始，向外扩展，注意不仅仅是i向两边扩展。为了考虑子串长度为偶数的情况，还需i+1向两边扩展
        /*
        这里为了避免i+1越界，i < s.length()-1，即对于字符串最后一个字符，我们不进行扩展，反正这个数也无法扩展，
        最后也只能得到这个字符本身，就是最后最长子串是一个字符，我们也只需要取第一个字符即可，不需要取到最后一个。
        且这里 s.length()>=2，那么不会出现一个字符的情况，不会产生影响。
         */
        for (int i = 0; i < s.length()-1 ; i++)
        {
            int len1 = expandAroundCenter(s , i , i);//从i向两边扩展，奇数
            int len2 = expandAroundCenter(s , i , i+1);//从i+1向两边扩展，偶数
            int len = Math.max(len1 , len2);

            //注意，必须当前获取的子串长度大于原来子串的长度，才对最大子串的首尾进行替换
            if(len > end-start+1)
            {
                if(len == len1)
                {
                    //奇数情况
                    start = i - len/2;
                    end = i + len/2;
                }
                else
                {
                    //偶数情况
                    start = i - (len/2-1);
                    end = i + len/2;
                }
            }

        }

        return s.substring(start , end+1);
    }

    //扩展方法：返回扩展后子串的长度
    private static int expandAroundCenter(String s , int left , int right)
    {
        int L = left;
        int R = right;
        //当满足 L，R不越界，且 L、R位置上的字符相等的时候，继续向外扩展
        while((L>=0 && R<s.length()) && (s.charAt(L) == s.charAt(R)))
        {
            L--;
            R++;
        }

        /*
        这里为什么是 R-L-1，而不是R-L+1，因为再最后一对满足 L>=0 && R<s.length() 之后，L-1,R+1，此时不满足则退出循环.
        那么实际上满足的是 R-1，L+1，因此最后子串长度应该是 (R-1)-(L+1)+1 = R-L-1
         */
        return R-L-1;//最后返回子串的长度
    }

    public static void main(String[] args)
    {
        System.out.println(longestPalindrome("sabac"));
    }
}
