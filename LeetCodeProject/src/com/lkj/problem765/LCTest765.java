package com.lkj.problem765;

public class LCTest765
{
    /** 贪心算法
    时间复杂度：O(n^2)
     空间复杂度：O(1)
     */
    public int minSwapsCouples(int[] row)
    {
        int ret = 0;//记录交换位置的次数
        /**
        从第一个偶数位置的元素开始，查看其对应的奇数位置的元素是不是其 “情侣数”，
         1）如果是，则跳过这次循环，进入下一个偶数（i+2）；
         2）如果不是，这遍历数组后面的元素（前面的“情侣数对”已经排列完毕），找到其对应的情侣数的位置，与i+1位置的数进行交换
         */
        for (int i = 0; i < row.length ; i = i+2)
        {
            /**
            1）若 row[i] 为偶数，则其二进制可以表示为 XXX0，XXX0^1 = XXX0^0001 = XXX1，刚刚好是row[i]对应的奇数；
            2）若 row[i] 为奇数，则其二进制可以表示为 XXX1，XXX1^1 = XXX1^0001 = XXX0，刚刚好是row[i]对应的偶数；
             （注意，这里偶数从0开始，因此“情侣数对”的偶数肯定小于奇数）
             那么对于数 X，X^1 就是其对应的情侣数
             */
            //若 row[i+1] 处的元素与 row[i] 处的元素是“情侣数”，则不需要交换，跳过 这次循环
            if(row[i+1] == (row[i]^1))
                continue;

            //若 row[i+1] 处的元素与 row[i] 处的元素不是“情侣数”，则找到 row[i] 元素的情侣数，进行交换
            for (int j = i+2; j < row.length ; j++)
            {
                if(row[j] == (row[i]^1))
                {
                    int temp = row[j];
                    row[j] = row[i+1];
                    row[i+1] = temp;
                    break;//注意，找到后就跳出循环，下面不需要继续循环
                }
            }
            ret++;//记得记录交换次数
        }
        return ret;
    }
}
