package com.lkj.problem124;

/**
 * 参考：
 * （1）https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/er-cha-shu-zhong-de-zui-da-lu-jing-he-by-leetcode-/
 * （2）https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/er-cha-shu-zhong-de-zui-da-lu-jing-he-by-ikaruga/
 * 计算二叉树中的一个节点的最大贡献值，具体而言，就是在以该节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大。
 *
 *                      a
 * 对于结点a，如       /  \
 *                子树b   子树c
 * 包含结点a的路径有2类，分别是：与a的父节点连接的路径、不与a的父节点；连接的路径。
 * （1）包含a且与a的父节点连接的路径：如 子树b+a+a父节点 、子树b+a+a父节点 、 a+a父节点；
 * （2）包含a但是不与a的父节点连接的路径：如 子树b+a、子树c+a、子树b+a+子树c、a（一个结点也算一个路径）
 *
 * 因此，对于每一个结点，如a，我们计算 包含该结点的 “不与父结点连接的所有路径” 的最大路径和，包含 a+子树b路径贡献值（以子树b为根的路径和最大值）、
 * a+子树c贡献值、a本身，随后计算这3个值的最大值，更新到全局变量max。
 * 随后，将  a+子树b路径贡献值、 a+子树c路径贡献值 a、3条路径的最大值返回给 a 父节点，包含a结点且与a父节点连接的路径的贡献值。
 *
 * 时间复杂度：O(n)，遍历每一个结点
 */
public class LCTest124
{
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }}

    int maxSum = Integer.MIN_VALUE;

    private int getMax(TreeNode node)
    {
        //空结点共享值为0
        if(node == null)
            return 0;

        //先计算node左右孩子的贡献值（即以node左右孩子为更结点的路径的最大和），
        int leftSum = getMax(node.left);
        int rightSum = getMax(node.right);

        /**
         计算 包含a但是不与a的父节点连接的路径（包含a并与a的父节点连接的路径在其父节点处计算） 的最大贡献值，更新到 maxSum，
         即找到 node.val , node.val+leftSum , node.val+rightSum , node.val+leftSum+rightSum , maxSum 最大值，更新maxSum。
         此处如果子树为贡献值负数，直接设置为0，表示抛弃这一子树的路径
         */
        int noConnSum = node.val + (leftSum<0?0:leftSum) + (rightSum<0?0:rightSum);
        //更新maxSum
        maxSum = Math.max(maxSum , noConnSum);

        /**
         将要与当前结点 a 父节点连接的段返回给a的父节点，有可能返回  a、a+b、a+c，返回这3者中贡献值最大的一个
         */
        return node.val + Math.max(0 , (leftSum>rightSum?leftSum:rightSum));
    }

    public int maxPathSum(TreeNode root)
    {
        getMax(root);
        return maxSum;
    }
}
