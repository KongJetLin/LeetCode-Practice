package com.lkj.problem9;

public class LCTest9_3
{
    /*
    将数后半段对折的方法（需要注意末尾为0的数需要提前排除！）
    这样相比第二种方法，少计算一半的数！
     */
    public static boolean isPalindrome(int x)
    {
        //x<0则必然不是回文数
        if(x<0)
            return false;
        //0<=x<10则必然是回文数
        if(x<10)
            return true;

        /**
         * 注意，指针方法必须先将末尾有0的数排除，否则会出现异常。
         * 如 ：10010，最后x=10,reverseNum=10，相等，但是事实上他们不应该相等
         * 注意，0已结在上面排除
         */
        if(x%10 == 0)
            return false;

        //如果x是回文数，那么最后得到的x必然等于reverseNum或者是reverseNum/10
        int reverseNum = 0;
        while (x>reverseNum)
        {
            reverseNum = reverseNum*10 + x%10;
            x /= 10;
        }
        return x==reverseNum || x==reverseNum/10;
    }
}
