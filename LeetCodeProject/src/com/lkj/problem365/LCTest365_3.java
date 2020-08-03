package com.lkj.problem365;

/** 数学解法
 目标可以改写成：找到一对整数 a, b，使得：ax+by=z
 而只要满足 z ≤ x+y，且这样的 a, b 存在，那么我们的目标就是可以达成的。这是因为
 1）若 a≥0,b≥0，由于 z ≤ x+y，那么要么 a+b=z，或者a\b 中有一个为0
 2）若 a<0，那么可以进行以下操作
    1）往 y 壶倒水；
    2） 把 y 壶的水倒入 x 壶；
    3） 如果 y 壶不为空，那么 x 壶肯定是满的，把 x 壶倒空，然后再把 y 壶的水倒入 x 壶。
 重复以上操作直至某一步时 x 壶进行了 a 次倒空操作，y 壶进行了 b 次倒水操作。
3）b<0类似上面。

 贝祖定理告诉我们，ax+by=z 有解，当且仅当 z 是 x, y的最大公约数的倍数。
 因此我们只需要找到 x, y 的最大公约数并判断 z 是否是它的倍数即可。（辗转相除法）

 时间复杂度：O(log(min(x,y)))，取决于计算最大公约数所使用的辗转相除法。
 */
public class LCTest365_3
{
    public boolean canMeasureWater(int x, int y, int z)
    {
        //1、特判
        if(x+y < z)
            return false;
        if(z == 0)
            return true;//把z=0抽出来，不然觉得怪怪的

        //要求x、y的最大公约数，x、y首先不能为0，将x或者y为0的情况先排除
        if(x==0 || y==0)
            return x+y==z;//当x或者y为0的时候，x+y=z的时候，才满足（即一个位0.另一个等于z）

        return z%gcd(x,y) == 0;
    }

    //辗转相除法求最大公约数（循环法）
    private int gcd(int a , int b)
    {
        while (b != 0)
        {
            int temp = a%b;
            a = b;
            b = temp;
        }
        return a;
    }

    //辗转相除法求最大公约数（递归法）
    private int gcd1 (int a , int b)
    {
        return b==0 ? a : gcd1(b , a%b);
    }
}
