package com.lkj.problem108;

/**
 BST的中序遍历是升序的，因此本题等同于根据中序遍历的序列恢复二叉搜索树。
 因此我们可以以升序序列中的任一个元素作为根节点，以该元素左边的升序序列构建左子树，以该元素右边的升序序列构建右子树，
 这样得到的树就是一棵二叉搜索树啦.
 又因为本题要求高度平衡，因此我们需要选择升序序列的中间元素作为根节点
 */
//对比109题
public class LCTest108
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums)
    {
        return buildAVL(nums , 0 , nums.length-1);
    }

    //这里相当于递归构造一颗AVL树：从数组 start 到 end 位置构建一颗AVL子树
    private TreeNode buildAVL(int[] nums , int start , int end)
    {
        //当 start>end ，说明前面一次递归已经找到叶子结点，这里直接返回null即可
        if(start > end)
            return null;
        //加这一行可以减少不必要的null
        if(start == end)
            return new TreeNode(nums[start]);

        //先找到中间结点的数组下标
        int mid = start + (end - start)/2;//这种写法是为了避免整型溢出
        TreeNode cur = new TreeNode(nums[mid]);
        //使用mid的左区间构建左子树，右区间构建右子树
        cur.left = buildAVL(nums , start , mid-1);
        cur.right = buildAVL(nums , mid+1 , end);

        return cur;
    }
}
