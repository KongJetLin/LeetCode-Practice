package com.lkj.problem387;

/**
提示：你可以假定该字符串只包含小写字母。
 只需要声明一个长度为 26 的数组即可！
 */
public class LCTest387
{
    public int firstUniqChar(String s)
    {
        int[] freq = new int[26];
        //先将字符串中各个字母出现的频率存储到freq数组（数组下标对应字母在ASCII表中26个小写字母中的位置）
        for (int i = 0; i < s.length() ; i++)
        {
            freq[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length() ; i++)
        {
            if(freq[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
