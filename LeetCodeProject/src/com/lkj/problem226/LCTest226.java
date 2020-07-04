package com.lkj.problem226;

public class LCTest226
{
    public class TreeNode
    {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
    }

    public TreeNode invertTree(TreeNode root)
    {
        if(root == null)
            return null;
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        root.left = invertTree(rightNode);
        root.right = invertTree(leftNode);

        return root;
    }

}
