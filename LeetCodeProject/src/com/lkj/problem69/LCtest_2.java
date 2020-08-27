package com.lkj.problem69;

/** 牛顿法 求 x 的平方根
将 long a初始化为x，当 a^2>x的时候，令 a = 1/2(a+x/a)，循环到 a^2第一次小于或等于 x，此时a就是解！
 注意，必须将a设置为long类型，否则若a是int类型，当 a*a 结果大于int的取值范围的时候，则会值保留int的部分，导致计算出错！

 公式：当x^2>a的时候，求 x=(x+a/x)/2，求 a 的平方根，x可以初始化为a
 */
public class LCtest_2
{
    public int mySqrt(int x)
    {
        long a = x;//a初始化为x
        while(a * a > x)
        {
            a = (a + x/a)/2;
        }
        return (int)a;
    }
}
