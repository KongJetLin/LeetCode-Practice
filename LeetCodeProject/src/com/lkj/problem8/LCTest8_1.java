package com.lkj.problem8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式的方法
 参考：https://leetcode-cn.com/problems/string-to-integer-atoi/solution/python-1xing-zheng-ze-biao-da-shi-by-knifezhu/
 */
public class LCTest8_1
{
    public int myAtoi(String s)
    {
        if(s==null || s.length()==0)
            return 0;

        String str = s.trim();

        /*
        1、这里多加一个 \，是为了将字符串中“\”的转义功能去除，即使得后一个“\”不具备转义功能，不会将“+”、“-”符号转义；
        2、注意，由于对于“12sss”这样的字符也是正确的，因此正则表达式不能用“$”结尾！否则只能匹配纯数字的字符串；
        3、Integer.parseInt()转换的时候，如果String的值不在int范围之内，也会抛出异常！
        */
        Pattern p = Pattern.compile("^[\\-\\+]?\\d+");
        Matcher matcher = p.matcher(str);

        int value = 0;
        if(matcher.find())
        {
            try
            {
                value = Integer.parseInt(str.substring(matcher.start() , matcher.end()));
            }
            catch (Exception e)
            {
                value = (str.charAt(0)=='-') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        return value;
    }
}
