package com.lkj.a_review;

import java.util.ArrayList;
/** 这个类用于复习时对题的统计！
 */
public class Statistic
{
    public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<>();
        /*数组*/
        list.add(1);list.add(4);list.add(27);list.add(121);

        /*链表*/
        list.add(2);list.add(206);list.add(21);

        /*动态规划*/


        /*字符串*/
        /**
         (1) 注意 《无重复字符的最长子串》、《最长公共前缀》、《最长回文子串》、《最小覆盖子串》

         */
        list.add(3);list.add(5);list.add(6);

        /*深度优先遍历*/


        /*哈希表*/


        /*动态规划*/
        /**
         ---后三题是动态规划套路题《最长公共子序列》、《最长重复子数组》、《不相交的线》
         */


        /*设计题*/
        list.add(146);


        /*树*/


        /*数学*/
        list.add(7);list.add(9);list.add(13);list.add(12);list.add(54);list.add(11);

        /*正则表达式*/
        list.add(8);

        System.out.println(list.contains(206));
        System.out.println(list.size());
    }
}
