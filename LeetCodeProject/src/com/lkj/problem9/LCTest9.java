package com.lkj.problem9;

public class LCTest9
{
    //通过数学的方式取头尾元素进行比较（一位一位比较）
    public boolean isPalindrome(int x)
    {
        //负数肯定不满足
        if(x<0)
            return false;

        //计算取最高位的时候，最大的除数，比如 8787，最大的除数为1000
        int div = 1;//div从1开始，是为了覆盖x为个位数的情况
        while (x/div >= 10)
        {
            //当x除以div还不是个位数的时候，需要将div乘以10
            div *= 10;
        }

        while(x>0)
        {
            int left = x/div;
            int right = x%10;
            if(left != right)
                return false;

            //下面将整个数缩小首尾2位
            x = (x % div)/10;
            div /= 100;//因为首尾少2位，div必须除以10
        }
        return true;
    }


    //将数组后半段对折的方法（组合树比较，需要注意末尾为0的数）
    public boolean isPalindrome1(int x)
    {
        /**
         * 注意，指针方法必须先将末尾有0的数排除，否则会出现异常。
         * 如 ：10010，最后x=10,reverseNum=10，相等，但是事实上他们不应该相等
         */
        //当x小于0，或者x不等于0且x的最后一位为0，此时也必然不是回文数
        if(x<0 || (x % 10 == 0 && x != 0))
            return false;

        int reverseNum = 0;
        /**
        当x大于reverseNum的时候，我们持续计算reverseNum
         */
        while(x > reverseNum)//注意！！！是while不是if，经常犯这种低级错误
        {
            reverseNum = reverseNum * 10 + x % 10;
            x = x/10;
        }
        //结束后，reverseNum有可能与x位数相同，有可能比x大一位（奇数）
        return x == reverseNum || x == reverseNum/10;
    }
}
