package com.lkj.problem95;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class LCTest95
{
     public class TreeNode
     {
         int val = 0;
         TreeNode left = null;
         TreeNode right = null;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right)
         {
          this.val = val;
          this.left = left;
          this.right = right;
         }
     }

    public ArrayList<TreeNode> generateTrees (int n)
    {
        ArrayList<TreeNode> res = new ArrayList<>();

        /*
        当 n=0 的时候，不返回null，而是直接返回空的ArrayList<TreeNode>。
        因为题目要求返回类型是ArrayList<TreeNode>，返回null会出错
         */
        if(n == 0)
            return res;
        //查找 1 - n的数字所组成的所有可能结构的二叉树
        return generateTrees(1, n);
    }

    //查找 start到end 数字所组成的所有可能结构的二叉树，并将这些二叉树的根放入ArrayList<TreeNode>
    private ArrayList<TreeNode> generateTrees(int start , int end)
    {
        //这个集合用于存储所有可能结构的二叉树的根（因为可能的二叉树结构有多个，因此要用ArrayList保存这些二叉树的根结点）
        ArrayList<TreeNode> res = new ArrayList<>();

        /*
        可能遇到 start>end 的情况，如上一层递归以start为根，其左子树为generateTrees(start, start - 1)，
        即左子树没有结点，为null，那么所有可能的二叉树只有一个：null；
        可能遇到 start=end 的情况，即已经找到最大树的叶子结点，叶子结点没有左右子树，
        那么所有可能的二叉树只有一个：start/end 为根。

        上述情况分别对应1结点的左子树（null）以及右子树（2）的情况。
                    1
                  /  \
               null   2
         */
        if(start > end)
        {
            res.add(null);
            return res;//注意返回集合，因为上一层的递归遍历的是所有可能的二叉子树的根结点的集合
        }
        if(start == end)
        {
            res.add(new TreeNode(start));
            return res;
        }


        //第一个for循环用于遍历 start到end 之间所有的数字，求以这些数字为根的二叉树
        for (int i = start; i <= end ; i++)
        {
            //求 start到i-1 数字组成的所有可能的二叉树，将这些二叉树根的集合返回，这些二叉树是以i为根的树的左子树
            ArrayList<TreeNode> leftTrees = generateTrees(start, i - 1);
            ArrayList<TreeNode> rightTrees = generateTrees(i + 1, end);//类似于求左子树的操作

            /**
            leftTrees 与 rightTrees分别存储所有可能的左子树与右子树的根结点。
             我们需要将这些左子树与右子树两两组合，并将i作为当前树的根，连接这些左右子树，最后将这个根i添加到 res。

             以i为根的树可能有多种结构，而我们通过最外层的循环，找到以 i=start-end 为根的所有可能树的结构，
             将这些结构的根添加到 res 集合中，返回给上一层的递归即可。
             */
            for (TreeNode leftTree : leftTrees)
            {
                for (TreeNode rightTree : rightTrees)
                {
                    TreeNode root = new TreeNode(i);//以i作为根结点
                    //根结点连接所有可能的左右子树，i根结点若连接不同的左右子树，最后测试的时候代表其是不同的二叉树
                    root.left = leftTree;
                    root.right = rightTree;
                    //将这个结构的二叉搜索树的根添加到 res
                    res.add(root);
                }
            }
        }
        //出循环的时候，将以 i=start-end 为根的所有可能树的结构的根结点添加到 res，将res集合返回给上一层递归
        return res;

        /**
        注意，这里的返回值是ArrayList<TreeNode>，而不是 ArrayList<ArrayList<TreeNode>>，
         因此我们需要查找每一个i(i=1,2,...,n)为根的所有可能的二叉树结构，并将这些根（i）结点添加到ArrayList<TreeNode>。

         如果返回 ArrayList<ArrayList<TreeNode>>，就要找到所有可能的二叉树的所有结点的集合，将这些ArrayList<TreeNode>
         再次封装到 ArrayList 中。
         */
    }

}
