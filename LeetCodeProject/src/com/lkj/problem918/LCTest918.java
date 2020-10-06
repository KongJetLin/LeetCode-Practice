package com.lkj.problem918;

/** 2次 Kadane 算法（Kadane算法其实就是我们前面说的，以A[i]结尾的子数组的最大和，就是动态规划方法）
 *  我们可以使用动态规划，也可以直接使用一个变量记录子数组的最大和，这样便不需要创建动态规划数组
 *
 *  在这个题的条件下最大值结果无非是以下两种情况的一种：
 * （1）最大值max在原数组中。求和最大的子序列即可
 * （2）最大值max在环形数组中。求和最小的子序列，然后用整个数组和减去最小子序列的和，就是这种情况的结果。
 *  比如最小子序列从 i到j （i>=0,j<A.length），那么我们求得第2种情况的子序列就是 0到i-1 与 j+1到A.length-1
 */
public class LCTest918
{
    public static int maxSubarraySumCircular(int[] A)
    {
        if(A==null || A.length==0)
            return 0;
        if(A.length == 1)
            return A[0];

        /** 先求子数组最大和与数组总和sum*/
        int sum = A[0];
        int maxValue = A[0];
        int maxLastValue = A[0];//保存以上一个元素结尾的子数组集合的最大和
        for (int i = 1; i < A.length; i++)
        {
            //这里与动态规划数组相似，只不过这里使用一个变量记录
            if(maxLastValue>0)
                maxLastValue += A[i];
            else
                maxLastValue = A[i];
            maxValue = Math.max(maxLastValue , maxValue);
            sum += A[i];
        }

        /** 求子数组最小和 */
        /**
        特别注意，对于第二种情况，最大子数组必须包含第一个元素与最后一个元素，因此求最小子数组和必须从1开始到 A.length-2，将第一个元素与最后一个元素排除。
         这是因为，如果仅仅包含头或尾部，那么 sum-minValue 就包含在求最大和子数组的情况。
         如果都不包含，可能出现全是负数的数组，此时最小子数组和也是sum，sum-minValue=0，是不符合题意的！
         */
        int minValue = 0;//最小子数组和不包含A[0]，那么minValue初始值设置为0
        int minLastValue = 0;//保存以上一个元素结尾的子数组集合的最小和（同样不包含A[0]）
        for (int i = 1; i < A.length-1; i++)
        {
            //这里与动态规划数组相似，只不过这里使用一个变量记录
            if(minLastValue<0)
                minLastValue += A[i];
            else
                minLastValue = A[i];
            minValue = Math.min(minLastValue , minValue);
        }

        //对比2种情况，将和最大的情况返回
        return Math.max(maxValue , sum-minValue);
    }

    public static void main(String[] args)
    {
        int[] arr = {-2,-3,-1};
        System.out.println(maxSubarraySumCircular(arr));
    }
}
