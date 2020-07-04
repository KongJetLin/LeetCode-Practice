package com.lkj.problem654;
//时间复杂度：O(n^2)，每次都要遍历数组找寻最大值，一共找了n次（每次连接一共结点就要找一次！）
public class LCTest654
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { this.val = x; }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums)
    {
        return buildBST(nums , 0 , nums.length-1);
    }

    //以nums数组中 start 到 end 部分的数据构建子树
    private TreeNode buildBST(int[] nums , int start , int end)
    {
        if(start == end)
            return new TreeNode(nums[start]);
        if(start > end)
            return null;

        int maxIndex = getMaxIndex(nums, start, end);
        TreeNode cur = new TreeNode(nums[maxIndex]);
        cur.left = buildBST(nums , start , maxIndex-1);
        cur.right = buildBST(nums , maxIndex+1 , end);

        return cur;
    }

    //获取数组nums 从start 到 end部分的最大值所对应在数组的下标
    private int getMaxIndex(int[] nums , int start , int end)
    {
        int index = start;
        for (int i = start; i <= end ; i++)
        {
            if(nums[i] > nums[index])
                index = i;
        }
        return index;
    }
}
