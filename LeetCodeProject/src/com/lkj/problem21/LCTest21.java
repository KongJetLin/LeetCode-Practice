package com.lkj.problem21;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** 回溯算法（参考46题解法）+ 剪枝
这一题同样是要查找所有可能，我们同样使用回溯算法解答。
 同样包括3部分：选择的路径，选择列表、结束条件。
 */
public class LCTest21
{
    List<String> res = new ArrayList<>();
    int len;
    public List<String> generateParenthesis(int n)
    {
        len = 2*n;//表示最后可以得到的字符串的长度
        backtrack(new StringBuilder() , n , n);

        return res;
    }

    /**
    1、已经选择的路径：使用sb来保存选择过的括号。
    2、可以选择的集合：使用open表示 “(” 的数量，使用close表示 “)” 的数量

     剪枝：当剩余的 “(” 的数量大于 “)” 的数量，说明前面 “)” 放多了，这是错误的情况，可以直接结束这次回溯
     “(” 的数量大于 “)” 的数量就没事！
     */
    private void backtrack(StringBuilder sb , int open , int close)
    {
        //3、结束条件
        if(sb.length() == len)
        {
            res.add(sb.toString());
            return;
        }

        //剪枝
        if(open > close)
            return;

        /** 选择：每一次递归的选择列表有2种情况：“(”或者“)”，但是这必须是在左右括号还有的情况下！ */
        //当还有 “(” 括号的时候
        if(open > 0)
        {
            sb.append("(");
            backtrack(sb , open-1 , close);
            sb.deleteCharAt(sb.length()-1);//将sb的最后一个字符删除，即将这一轮选择的“(”删除，才可以选择“)”
        }

        if(close > 0)
        {
            sb.append(")");
            backtrack(sb , open , close-1);
            sb.deleteCharAt(sb.length()-1);
        }
    }

//    public static void main(String[] args)
//    {
//        List<String> strings = generateParenthesis(3);
//        for (String string : strings)
//        {
//            System.out.println(string);
//        }
//    }
}
