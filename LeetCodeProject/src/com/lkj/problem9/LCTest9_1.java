package com.lkj.problem9;

/**
 * 字符串方法
 */
public class LCTest9_1
{
    public boolean isPalindrome(int x)
    {
        String str = x+"";
        StringBuilder sb = new StringBuilder(str);
        return str.equals(sb.reverse().toString());
    }
}
