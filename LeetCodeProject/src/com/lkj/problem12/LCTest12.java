package com.lkj.problem12;

/** 贪心算法
我们可以使用13个符号来组成罗马数字，这13个符号各不相同。我们将这13个符号从大到小排列，
若要找到一个数字的罗马数字表示，表示应该使用尽可能大的符号，因此我们从左侧开始寻找工作。
 贪心算法是一种在当前时间做出最佳可能决策的算法；在这种情况下，它会取出最大可能的符号。

 具体过程：为了表示一个给定的整数，我们寻找适合它的最大符号。我们减去它，然后寻找适合余数的最大符号，依此类推，直到余数为0。
 我们取出的每个符号都附加到输出的罗马数字字符串上。
 */
public class LCTest12
{
    public String intToRoman(int num)
    {
        //1、先建立2个表用于对照
        String[] symbols = {"M" , "CM" , "D" , "CD" , "C" , "XC" , "L" , "XL" , "X" , "IX" , "V" , "IV" ,"I"};
        int[] number = {1000 , 900 , 500 , 400 , 100 , 90 , 50 , 40 , 10 , 9 , 5 , 4 , 1};

        StringBuilder sb = new StringBuilder();
        //2、对nums进行贪心操作
        for (int i = 0; i < number.length && num>0 ; i++)//当 num 等于0的时候，就不需要再比较
        {
            //当找到一个 number[i] 值小于等于 num的时候，用num减去 number[i]，并取得余数。
            //由于一个 number[i] 可能被 num 使用多次（如 3 会使用 I 3次），因此我们这里要使用while循环，知道 number[i]大于num就停止
            while(num >= number[i])
            {
                num -= number[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
