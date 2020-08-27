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

        //链表
        list.add(124);list.add(110);

        //动态规划
        list.add(300);list.add(674);

        //字符串
        list.add(5);list.add(3);

        //深度优先遍历
        list.add(547);

        //哈希表
        list.add(169);

//        System.out.println(list.contains());
//        System.out.println(list.size());
    }
}
