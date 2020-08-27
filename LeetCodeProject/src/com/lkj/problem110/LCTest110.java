package com.lkj.problem110;

public class LCTest110
{
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }

    public boolean isBalanced(TreeNode root)
    {
        return findHeight(root) != -1;
    }

    private int findHeight(TreeNode node)
    {
        if(node == null)
            return 0;

        int leftHeight = findHeight(node.left);
        if(leftHeight == -1)
            return -1;
        int rightHeight = findHeight(node.right);
        if(rightHeight == -1)
            return -1;

        int nodeHeight = Math.abs(leftHeight - rightHeight);
        if(nodeHeight>1)
            return -1;
        return Math.max(leftHeight,rightHeight)+1;
    }
}
