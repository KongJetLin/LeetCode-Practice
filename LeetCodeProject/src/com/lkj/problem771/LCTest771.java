package com.lkj.problem771;

import java.util.HashMap;

//时间复杂度 ：O(J.length() + S.length())
public class LCTest771
{
    public static int numJewelsInStones(String J, String S)
    {
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length() ; i++)
        {
            char ch = S.charAt(i);
            if(map.containsKey(ch))
                map.put(ch , map.get(ch)+1);
            else
                map.put(ch , 1);
        }

        for (int i = 0; i < J.length() ; i++)
        {
            char ch = J.charAt(i);//注意，这里获取到的ch应该是char类型，而不是int类型，IDE自动给我转换成为int类型
            if(map.containsKey(ch))
                res += map.get(ch);
        }

        return res;
    }

    public static void main(String[] args)
    {
        System.out.println(numJewelsInStones("aA" , "aAAbbbb"));
    }
}
