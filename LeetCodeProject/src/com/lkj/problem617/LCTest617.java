package com.lkj.problem617;

public class LCTest617
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2)
    {

        TreeNode node = null;
        if(t1!=null && t2!=null)
        {
            node = new TreeNode(t1.val+t2.val);
            node.left = mergeTrees1(t1.left , t2.left);
            node.right = mergeTrees1(t1.right , t2.right);
        }
        else if(t1!=null && t2==null)
        {
            node = new TreeNode(t1.val);
            node.left = mergeTrees1(t1.left , null);
            node.right = mergeTrees1(t1.right , null);
        }
        else if(t1==null && t2!=null)
        {
            node = new TreeNode(t2.val);
            node.left = mergeTrees1(null , t2.left);
            node.right = mergeTrees1(null , t2.right);
        }
        //t1与t2都为null的情况不需要处理，因为这里只需要返回null，而node原本就设置为null

        return node;
    }

//---------------------------------------------------------------------
    //递归，简便解法
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2)
    {
        /**
        当t1或t2有一个位null的时候，合并树剩下的部分都是t2或t1的部分，因此我们只需要直接返回不为null的树即可！
         如果t1、t2都是null，返回对方为null，这也是正确的！
         当2个都不为null，返回合并的结点，并继续遍历左右结点！
         */
        if(t1 == null)
            return t2;
        if(t2 == null)
            return t1;
        TreeNode mergeNode = new TreeNode(t1.val+t2.val);
        mergeNode.left = mergeTrees(t1.left , t2.left);
        mergeNode.right = mergeTrees(t1.right , t2.right);

        return mergeNode;
    }
}
