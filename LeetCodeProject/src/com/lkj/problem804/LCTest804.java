package com.lkj.problem804;

import java.util.TreeSet;

public class LCTest804
{
    public int uniqueMorseRepresentations(String[] words)
    {
        //先定义一个数组用于保存 摩尔斯密码 表
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",
            ".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        //使用java自带的 Set：TreeSet，它的底层就是树实现，不过使用的是红黑树实现（平衡二叉树）
        TreeSet<String> tr = new TreeSet<>();
        //对 words 数组的每一个单词进行遍历
        for (String word : words)
        {
            StringBuilder sb = new StringBuilder();
            /*
            获取每一个单词的每一个字母，我们需要找到每一个字母在 codes 表中对应的 摩尔斯符，那么就要找到每一个字母在 codes 数组中的下表，
            我们先找出字母 word.charAt(i)，随后 word.charAt(i)-'a' ，就可以得到这个字母在数组中对应的 摩尔斯符 的下标。
            就可以在数组中找出这个字母对应的摩尔斯符：codes[word.charAt(i)-'a'])
             */
            for (int i = 0; i < word.length() ; i++)
            {
                sb.append(codes[word.charAt(i)-'a']);
            }
            //将这个单词的对应的摩尔斯符 添加到Set集合中
            tr.add(sb.toString());
        }
        return tr.size();//返回Set集合的大小
    }
}
