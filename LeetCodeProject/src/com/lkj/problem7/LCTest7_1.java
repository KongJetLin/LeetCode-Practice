package com.lkj.problem7;

/** 字符串方法（性能较差）
 */
public class LCTest7_1
{
    public static int reverse(int x)
    {
        /*
        1、这里必须用 long 类型的变量接受，因为对于 -2147483648=-2^31 ，变为正数后，2147483648=2^31 比int的最大值 2^31-1，此时变为正数失败！
         */
        long temp = Math.abs((long)x);
        StringBuilder sb = new StringBuilder(temp+"");//2、StringBuilder构造器里面参数必须是String类型，int类型的数据无法通过StringBuilder为String
        String retStr = sb.reverse().toString();


        if(x>=0)
        {
            long cur = Long.parseLong(retStr);
            //3、判断转换后的值是否大于int的最大值或者小于int最小值，是的话返回0
            if(cur>Integer.MAX_VALUE)
                return 0;
            else
                return (int)cur;
        }
        else
        {
            long cur = -Long.parseLong(retStr);
            if(cur<Integer.MIN_VALUE)
                return 0;
            else
                return (int)cur;
        }
    }
}
