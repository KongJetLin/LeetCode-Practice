package com.lkj.a_review;

import java.util.ArrayList;
/** 这个类用于复习时对题的统计！
 */
public class Statistic
{
    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<>();
        //数组
        list.add(1);list.add(15);list.add(18);list.add(26);list.add(162);list.add(215);list.add(240);
        list.add(918);list.add(46);

        //链表
        list.add(124);list.add(110);list.add(206);list.add(92);list.add(2);

        //动态规划
        list.add(300);list.add(674);

        //字符串
        /**
         (1) 注意 《无重复字符的最长子串》、《最长公共前缀》、《最长回文子串》、《最小覆盖子串》

         */
        list.add(5);list.add(3);list.add(14);list.add(76);

        //深度优先遍历
        list.add(547);

        //哈希表
        list.add(169);

        //动态规划
        /**
         ---后三题是动态规划套路题《最长公共子序列》、《最长重复子数组》、《不相交的线》
         */
        list.add(718);list.add(1043);list.add(1035);

        //设计题
        list.add(146);


        System.out.println(list.contains(53));
//        System.out.println(list.size());
    }
}
