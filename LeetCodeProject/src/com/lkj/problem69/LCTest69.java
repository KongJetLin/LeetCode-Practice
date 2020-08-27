package com.lkj.problem69;

/**
 * 二分法，时间复杂度：O(logn)
 */
public class LCTest69
{
    /** 注意，这一题虽然简单，但是有3点需要特别注意：
     （1）当 平方 square > x 的时候，说明 mid肯定比x的平方根大，则右边界减一；当 square<x 的时候，说明mid肯定比x的平方根小，
        但是此时不能简单地将左边界 left=mid+1，因为有可能x的平方根是小数，省略小数后，可能为mid，如 对于x=5，取（0,5）区间，
     这时mid=2，mid^2=4<5，如果简单将left=mid+1，区间变为（3,5），而5的平方根是2点几，最后结果去除小数取整数部分为 2！
     因此，当 mid^2=square<x 的时候，应该将mid也包含进来！即left=mid。
     这样，当  mid^2=square=x 的时候，此时left=mid，也被包含进来。当然，也可以直接在  square=x 的时候返回mid！！！

     （2）注意 int mid = (left+right+1)/2，而不能是 int mid = (left+right)/2，即中间数取右边，否则可能出现死循环。
     如对于8，第一个区间是(0,8)，第二个区间是(0,3)，第三个区间是(1,3)，第四个区间是(2,3)，随后，mid=2，发现mid^2<8，
     则left=mid，区间仍然是(2,3)，这样就会陷入死循环！！！
     因此，我们中位数必须取右边的中位数！！！

     （3）由于mid的平方可能超过int的范围，因此square必须设置为龙，但是两个int类型的mid相乘，结果如果设置为long类型，
        最后其实也只会保留 int 类型的值，所以当测试用例为 2147395599 这样大数的时候，我们发现mid=1073697800，但是
      mid*mid的结果变为int类型，而不是long类型，导致square<x，其实应该是square>x的，因此结果是错误的！
     那么我们干脆将mid、left、right全部设置为long兼容大数，最后再换为小数即可！！！
     */
    public int mySqrt(int x)
    {
        long left = 0;
        long right = x;

        while (left<right)
        {
            long mid = (left+right+1) >>> 1;
            //注意将 square 设置为long类型，int类型的平方可能超过int的取值范围
            long square = mid * mid;
            if(square > x)
                right = mid-1;
            else
                left = mid;
        }
        //当left=right的时候，找到x平方根的整数部分，直接返回！
        return (int)left;
    }

//    public static void main(String[] args)
//    {
//        System.out.println(mySqrt(2147395599));
//    }

}
