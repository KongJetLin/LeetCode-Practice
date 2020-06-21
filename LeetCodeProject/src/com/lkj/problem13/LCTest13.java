package com.lkj.problem13;

/**
 罗马数字由 I,V,X,L,C,D,M 构成，我们知道：
1） 当小值在大值的左边，则减小值，如 IV=5-1=4；
2） 当小值在大值的右边，则加小值，如 VI=5+1=6；
3） 由上可知，右值永远为正，因此最后一位必然为正。
 一言蔽之，把一个小值放在大值的左边，就是做减法，否则为加法。

 方案：在代码实现上，可以往后看多一位，对比当前位与后一位的大小关系，从而确定当前位是加还是减法。

 */
public class LCTest13
{
    public int romanToInt(String s)
    {
        int preNum = getValue(s.charAt(0));//取得第一个数作为前一个数的值
        int sum= 0;
        //从下标1开始遍历（第二个数开始），与前一个数对比，看看使用加法还是减法
        for (int i = 1; i < s.length() ; i++)
        {
            if(getValue(s.charAt(i)) > preNum)//当前字符代表的数大于前面字符代表的数
            {
                sum = sum - preNum;
            }
            else//当前字符代表的数小于等于前面字符代表的数
            {
                sum = sum + preNum;
            }
            preNum = getValue(s.charAt(i));//注意更新前一个数
        }
        //遍历完毕后，我们发现每次都是加或者减前一个数，最后一个数还没有加（最后一个数在右边，肯定是加）
        sum += preNum;//此时最后一个值赋予了preNum

        return sum;
    }

    private int getValue(char ch)
    {
        switch (ch)
        {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default : return 0;
        }
    }
}
