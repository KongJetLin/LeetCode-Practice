package com.lkj.problem17;

import java.util.ArrayList;
import java.util.List;

/** 回溯：
解释：回溯是一种通过穷举所有可能情况来找到所有解的算法。如果一个候选解最后被发现并不是可行解，回溯算法会舍弃它，
        并在前面的一些步骤做出一些修改，并重新尝试找到可行解。

 注意：
 如果输入的是“1”，那么输出 ["a","b","c"] 3个，循环一次
 如果输入“23”，那么输出 ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]，9个，循环2次
 如果输入“123”，那么输出["adg","adh","adi"....]，27个，循环3次
 如果输入的字符包含 n个数，那么输出3^n个数，循环n次。

 使用递归的解法，根据输入的字符中数的个数，递归找寻可能的字母组合。
 这个方法就像一颗三/四叉树，一共有n层，找到所有情况的时间复杂度是 O(3^N*4^M)。
其中 N 是输入数字中对应 3 个字母的数目（比方说 2，3，4，5，6，8）， M 是输入数字中对应 4 个字母的数目（比方说 7，9），N+M 是输入数字的总数


 注意，输入的字符中有几个数，输出的结合中组合的字母组合中就有几个字符！
 */
public class LCTest17
{
    static List<String> res = new ArrayList<>();//用于保存结果、
    static String[] letterMap = {"" , "!@#" , "abc" , "def" , "ghi" , "jkl" ,"mno" , "pqrs" , "tuv" , "wxyz"};//字母表

    public static List<String> letterCombinations(String digits)
    {
        //边界判断
        if(digits==null || digits.length()==0)
            return res;

        //初始 index=0，初始letter=""空字符
        findCombinations(digits  , 0 , "");

        return res;
    }

    //根据字符中第 index 个数字字符，将这个数字字符代表的字母添加到letter中，letter包含前面递归添加的字符
    private static void findCombinations(String str , int index , String letter)/**1、这里使用letter保存路径，即保存前面已经选择的字符*/
    {
        /**2、结束条件：index=str.length()的时候，即给定字符的最后一个字符遍历完*/
        if(index==str.length())
        {
            res.add(letter);//已经遍历完str，将前面字符添加到res中
            return;//注意结束，否则还会运行下面的代码
        }

        //取出这一层递归的数字字符
        char c = str.charAt(index);
        int point = c-'0';//找到这个字符对应的字母表的位置
        String numStr = letterMap[point];//找出这个数字对应的字母表的字符

        /**3、选择集：就是当前数字对应的字符*/
        for (int i = 0; i < numStr.length() ; i++)
        {
            /**这里循环会选择到所有字符，直接选择并进入递归就可以，不需要撤销*/
            //将这一层的字符添加到letter中，并递归找寻下一层的字符。同时 index +1
            findCombinations(str , index+1 , letter+numStr.charAt(i));
        }
    }

    public static void main(String[] args)
    {
        List<String> strings = letterCombinations("2345");
        for (String string : strings)
        {
            System.out.print(string+" , ");
        }
    }
}
