package com.lkj.problem677;

import com.lkj.problem208.LCTest208;

import java.util.TreeMap;

/** 对比208,211题
因为这题要查找包含某一个前缀prefix的所有字符串的值的和，这种很容易想到用字典树
 */
public class LCTest677
{

    private class Node
    {
        /**
         这里不再需要isWord来判断该字母是不是一个单词的结尾，因为一个单词对应一个值，我们将这个字母对应的值
         存储到其最后一个字母结点中，如果某一个字母结点的值不是0，说明这个字母就是某一个单词的结尾。
         因此这里不再需要isWord，而是需要value，当该字母是单词结尾的时候，value的值就是字母对应的值。
        public boolean isWord;

         即使用户传递当前单词的值为0，我们无法判断这个单词的结尾，但是没有关系，因为我们这里只关心加起来和的值。！
        */

        public TreeMap<Character , Node> next;
        public int value;

        public Node(int value)
        {
            this.value = value;
            next = new TreeMap<>();//注意，这里一定要实例化TreeMap，否则next没法使用！！！这是容易忽略的点
        }
        //默认设置当前结点不是单词结尾
        public Node()
        {
            this(0);//每一个字母的默认值是0
        }
    }
//------------------------------------

    private Node root;

    public LCTest677()
    {
        root = new Node();
    }

    public void insert(String key, int val)
    {
        Node cur = root;
        for (int i = 0; i < key.length() ; i++)
        {
            char c = key.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c , new Node());
            cur = cur.next.get(c);
        }
        //当找到单词的最后一个字母的Node结点，将这个结点的值设置为val
        cur.value = val;
    }

    public int sum(String prefix)
    {
        //首先查找是否有prefix这个前缀
        Node cur = root;
        for (int i = 0; i < prefix.length() ; i++)
        {
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return 0;//没有最高前缀，和为0
            cur = cur.next.get(c);
        }
        //如果找到这个前缀，查找所有包含这个前缀（包括这个前缀本身的单词的和）
        return sum(cur);//cur是prefix的最后一个结点，将以cur结点为根的子多叉树的所有结点的值加起来！
    }

    //计算所有以node结点的字母开头的单词段的和
    private int sum(Node node)
    {
        //不需要下面这一段，因为递归之前，会找到当前结点的所有子结点进行递归，如果当前结点没有子结点，就不会向下递归，因此这里不需要手动结束递归
//        if(node.next.size() == 0)
//            return node.value;//如果当前结点没有子结点，即子结点集合size=0，则知道这个结点是叶子结点，直接将其返回即可！

        int res = 0;
        res += node.value;//先将prefix本身加起来
        //将node所有子结点的值加起来，再递归，将子结点的所有子结点的值加起来，知道到树的尾部
        for (Character ch : node.next.keySet())
        {
            res += sum(node.next.get(ch));
        }
        return res;
    }
}
