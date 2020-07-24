package com.lkj.problem14;

public class LCTest14
{
    /** 横向比较
    我们从头到尾两两比较字符串，找出前面2个字符串相同的前缀，然后将这个前缀与后面的字符串再进行比较，
     直到比较完所有的字符串或者前缀为0，则退出比较。

     时间复杂度：O(mn)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。
     最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
     空间复杂度：O(1)。使用的额外空间复杂度为常数。
     */
    public String longestCommonPrefix(String[] strs)
    {
        if(strs == null || strs.length == 0)
            return "";
        /**
         注意，这里必须将 prefix 赋予 strs[0]，然后循环中用 prefix与str[i] 比较，不能死的 prefix=""，
         因为有可能数组只有一个元素，不进入循环，此时返回prefix=""，事实上应该返回数组的这个元素！
         */
        String prefix = strs[0];
        for (int i = 1; i < strs.length ; i++)
        {
            prefix = getCommonPrefix(prefix , strs[i-1]);
            //当前缀已经为0的时候，不再需要进行循环，直接退出
            if(prefix.length() == 0)
                break;
        }
        return prefix;
    }

    private String getCommonPrefix(String str1 , String str2)
    {
        int length = Math.min(str1.length() , str2.length());
        int index = 0;
        //注意，index必须小于length
        while(index < length && str1.charAt(index) == str2.charAt(index))
        {
            index++;
        }
        return str1.substring(0 , index);
    }
}
