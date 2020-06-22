package com.lkj.problem211;

import java.util.TreeMap;

/**
 这个题目与 208 实现Trie类似，但是出现一个“.”可以匹配所有字母，因此在搜索的时候实现不太一样
 */
public class LCTest211
{
    //----------------------Node 结点定义
    private class Node
    {
        public boolean isWord;
        public TreeMap<Character , Node> next;

        public Node(boolean isWord)
        {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node()
        {
            this(false);
        }
    }

    //-----------------------------

    private Node root;

    public LCTest211()
    {
        root = new Node();
    }

    public void addWord(String word)
    {
        Node cur = root;
        for (int i = 0; i < word.length() ; i++)
        {
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c , new Node());
            cur = cur.next.get(c);
        }

        if(!cur.isWord)
            cur.isWord = true;
    }


    public boolean search(String word)
    {
        return match(root , word , 0);
    }

    //字符串word中index位置的字母为c，这个方法就是查看 node结点下面是否有存储字母c的子结点
    //由于“.”要查询所有情况，因此使用递归，最差情况下word全部是“.”，需要遍历Trie所有结点。
    private boolean match(Node node , String word , int index)
    {
        /**
        我们注意到，root结点不存储任何字符，那么match方法从头开始就是：
         查询root结点（index=0时）是否存在存储 word的index=0处字符的子结点，存在则向下继续寻找。
         这一层递归中，当前结点node是root结点；
         查询index=1时，当前结点是否存在存储 word的index=1处字符的子结点，存在则向下继续寻找。
         这一层递归中，当前结点node是 word的index=0处字符的结点；
         ...
         查询index=word.length()-1时，当前结点是否存在存储 word的index=word.length()-1处字符的 的子结点，存在则向下继续寻找。
         这一层递归中，当前结点node是 word的index=word.length()-2处字符的结点；
         下一个循环中，index=word.length()，当前结点node是 word的index=word.length()-1处字符的结点，前面已经判断这个结点存在，
         才会查找到这里，那么我们只需要判断 node 结点的isWord()是否为true即可！！！
         */

        //终止条件
        if(index == word.length())
            return node.isWord;

        char c = word.charAt(index);
        if(c != '.')
        {
            if(node.next.get(c) == null)
                return false;
            //如果当前结点存在存储字符c的子结点，则向下查看这个子结点是否包含下一个字符
            return match(node.next.get(c) , word , index+1);
        }
        else
        {
            //如果c是“.”，我们需要取出当前结点的所有子结点，查询左右子结点是否包含下一个字符
            for (Character ch : node.next.keySet())
            {//查询node的所有子Node结点，查询所有这些子Node结点是否包含word的下一个字母
                // 这里相当于略过当前结点Node的比较，因为其被“.”抵消，
                if(match(node.next.get(ch) , word , index+1))
                    return true;//当有一个子路径满足返回true，说明查找的字母存在，直接返回true
            }
            return false;//查询完node所有的子路径，全部不满足，返回false
        }
    }
}
