package com.lkj.problem9;

/**
 * 取x翻转后的数与x进行比较！
 */
public class LCTest9_2
{
    public static boolean isPalindrome(int x)
    {
        //x<0则必然不是回文数
        if(x<0)
            return false;
        //0<=x<10则必然是回文数
        if(x<10)
            return true;

        //获取x后的数与x进行比较
        int com = 0;
        int cur = x;
        while (cur!=0)
        {
            int temp = cur%10;
            com = com*10+temp;
            cur /= 10;
        }
        return com==x;
    }
}
