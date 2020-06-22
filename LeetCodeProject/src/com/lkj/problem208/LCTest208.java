package com.lkj.problem208;

import java.util.TreeMap;

/**
对比211题
 */
public class LCTest208
{
    private class Node
    {
        public boolean isWord;//用于标注当前结点是不是一个字母的末尾
        //用于根据下一个字符，来找到存储下一个字符的Node结点（我们必须事先知道下一个字符是什么，才能找到下一个字符存储的Node结点）
        public TreeMap<Character , Node> next;

        public Node(boolean isWord)
        {
            this.isWord = isWord;
            next = new TreeMap<>();//注意，这里一定要实例化TreeMap，否则next没法使用！！！这是容易忽略的点
        }
        //默认设置当前结点不是单词结尾
        public Node()
        {
            this(false);
        }
    }

//------------------------------------------------------------------

    private Node root;//Trie的根结点
    private int size;//Trie中存储的单词数量

    //构造方法
    public LCTest208()
    {
        root = new Node();
        size = 0;
    }

    //查询Trie中有多少个单词
    public int getSize()
    {
        return size;
    }

//---------------------------------------------------------------向Trie添加一个字符串

    //我们是将字符串拆分成为一个个的字符并将每一个字符存储到每一个结点中（我们这里使用非递归的写法）
    public void add(String word)
    {
        //首先，将指针指向Trie的根结点（Trie根结点不存储字符）
        Node cur = root;
        //遍历word，将每一个字符存储到Trie
        for (int i = 0; i < word.length() ; i++)
        {
            char c = word.charAt(i);//取出字符
            //判断这个字符对应的存储结点是否存在，即判断当前结点 cur 是否有一个 Map结点 存储的是 c 对应的Node。
            // 如果不存在，我们创建一个新的Map结点，设置这个Map结点的 key 为c，value为存储c的new Node，并将这个Map结点设置为cur的next（next就是一个Map集合）
            if(cur.next.get(c) == null)
                cur.next.put(c , new Node());
            //如果存储c的结点存在，或者不存在但是我们添加了该结点，我们都要将cur指向存储 c 的结点，以便存储下一个结点
            cur = cur.next.get(c);
        }

        //遍历完后，cur指向单词word的末尾，我们设置cur.isWord=true，但是有可能前面这个单词已经存储，cur.isWord已经是true，需要判断
        if(!cur.isWord)
        {
            cur.isWord = true;
            size++;//维护 Trie 存储的单词数量
        }
    }

//----------------------------------------------------------------------------------在Trie中查询一个单词是否存在

    public boolean contains(String word)
    {
        Node cur = root;
        for (int i = 0; i < word.length() ; i++)
        {
            char c = word.charAt(i);
            //如果字符对应的结点不存在，直接返回false（不存在这个单词）
            if(cur.next.get(c) == null)
                return false;
            //如果字符对应的结点存在，将cur指向下一个结点，继续向下查找
            cur= cur.next.get(c);
        }
        //查找到单词最后一个字符存在，但是有可能这个字符的isWord()是false，即我们之前没有出场过这个单词，那么这个单词也是不存在的
        return cur.isWord;//根据isWord是否为false，就可以判断这个单词是否存在
    }

//--------------------------------------------------------------------------------- 查询是否在Trie中有单词以prefix为前缀

    //这个方法与前面查询是否包含的方法很类似
    public boolean isPrefix(String prefix)
    {
        Node cur = root;
        for (int i = 0; i < prefix.length() ; i++)
        {
            char c = prefix.charAt(i);
            //如果不存在 c 对于的Node结点（即当前结点的Map集合中不存在存储 key=c，value=Node 的结点），直接返回false
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;//如果能跳出循环，说明prefix的所有字母都能在Trie中找到，返回true
    }

    /**
     contains()方法与isPrefix()方法的不同之处在于，如果查找到单词对应的最后一个字母，
     contains()方法需要判断这最后一个字母对应的Node结点的isWord()是不是true，如果是false说明这段只是某个字母的一部分，而不是这个字母。
     而isPrefix()是判断有没有包含这个前缀的字母，只要最后一个字母存在即可返回true。
     */
}
