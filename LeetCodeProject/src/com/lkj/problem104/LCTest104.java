package com.lkj.problem104;

public class LCTest104
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int maxDepth = 0;
    public int maxDepth1(TreeNode root)
    {
        int curDepth = 0;
        getMaxDepth(root , curDepth);

        return maxDepth;
    }

    private void getMaxDepth(TreeNode node , int curDepth)
    {
        if(node == null)
        {
            if(curDepth > maxDepth)
                maxDepth = curDepth;
            return;
        }
        curDepth++;
        getMaxDepth(node.left , curDepth);
        getMaxDepth(node.right , curDepth);
    }

//-----------------------------
    //递归代码较少的方法
    public int maxDepth(TreeNode root)
    {
        if(root == null)
        {
            return 0;
        }
        else
        {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);

            return Math.max(leftDepth , rightDepth)+1;
        }
    }
}
