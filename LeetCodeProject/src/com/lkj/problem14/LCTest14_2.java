package com.lkj.problem14;

public class LCTest14_2
{
    /** 纵向比较
    我们以 strs[0] 为标志，比较所有字符串的第一个字符，第二个字符....
     当所有到达某一个字符串的结尾，或者有一个字符串的字符不等于str[0]在该位置的字符，就退出！
     */
    public String longestCommonPrefix(String[] strs)
    {
        if (strs == null || strs.length == 0) return "";


        //以strs[0]为比较的标志
        for (int i = 0; i < strs[0].length() ; i++)
        {
            char ch = strs[0].charAt(i);
            /**
            对数组的其他元素进行遍历比较
             1）当这一轮所有其他字符串i位置的字符都等于ch，则什么都不操作，进行下一轮的比较；
             2）当 某个字符的长度 == i，此时不应该继续比较（因为已经有字符串到达末尾），退出；（这部分先判断，否则可能出现字符串索引越界）
             或者在字符串索引没有越界的时候，某个字符在i位置的字符不等于 ch，也应该退出比较，此时返回前面比较相同的字符段。
             */
            for (int j = 1; j < strs.length ; j++)
            {
                if(strs[j].length() == i || strs[j].charAt(i) != ch)
                    return strs[0].substring(0 , i);
            }
        }
        //当 strs[0] 的全部字符比较通过，那么strs[0]就是最长的前缀
        return strs[0];
    }
}
