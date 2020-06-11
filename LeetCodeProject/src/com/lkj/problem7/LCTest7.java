package com.lkj.problem7;

/** 数学方法
1）这个题目可以将x变为字符串，然后拆开为字符来解答，但是这样涉及多个字符的操作，复杂度较高；

 2）使用数学方法，类似于入栈出栈操作，将x的最后一个数字取出，同时将x/10，用于取出笑一个数字。
 取出的数字pop，加上之前已经获取的翻转的数字：rev = rev*10+pop
 //出栈操作:
 pop = x % 10;
 x /= 10;

 //入栈操作:
 temp = rev * 10 + pop;
 rev = temp;

但是，这里必须考虑到溢出问题，int的最大值：MAX_VALUE 2147483647，最小值：MIN_VALUE -2147483648
 即使一个数字不溢出，它翻转后可能溢出。

1） 若 rev*10+pop > MAX_VALUE
    若 rev> MAX_VALUE / 10，那么一定溢出
    若 rev=MAX_VALUE / 10，但是最后加的 pop>7，因为MAX_VALUE最后一位是7，也必然溢出

2）若 rev*10+pop < MIN_VALUE （当x是负数的时候，pop是负数，那么rev也是负数）
    若 rev < MIN_VALUE/10，那么一定溢出
    若 rev = MIN_VALUE/10，但是 pop < -8，因为 MIN_VALUE最后一位是8且其为负数，那么最后也会溢出。
 */
public class LCTest7
{
    public int reverse(int x)
    {
        int rev = 0;//用于存储翻转的数，初始值为0
        while(x!=0)
        {
            int pop = x%10;//x>0,pop>0;x<0,pop<0
            //判断 rev是否满足相应溢出条件，满足返回0，不满足则可以运算
            if((rev > Integer.MAX_VALUE/10) || (rev == Integer.MAX_VALUE/10 && pop > 7))
                return 0;
            if((rev < Integer.MIN_VALUE/10) || (rev == Integer.MIN_VALUE/10 && pop < -8))
                return 0;
            x /= 10;//注意将x变化
            rev = rev*10 + pop;
        }
        return rev;
    }
}
