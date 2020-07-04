package com.lkj.problem655;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//时间复杂度：我们需要填充h行，2^h-1列的数组，因此时间复杂度是 O(h*2^h-1) = O(h*2^h)
//空间复杂度：数组 resres 的长度为 h*2^h-1，空间复杂度为：O(h*2^h)
public class LCTest655
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { this.val = x; }
    }

    public List<List<String>> printTree(TreeNode root)
    {
        //1、首先，求出二叉树的高度h，创建的二维数组，行数为h，列数为 2^h-1
        int height = getHeight(root);

        //2、创建二维数组
        String[][] arr = new String[height][(int)Math.pow(2,height)-1];
        //3、把二维数组的每一个元素设置为“”
        for (String[] str : arr)
        {
            Arrays.fill(str , "");//使用Arrays的fill()方法设置！
        }

        List<List<String>> ret = new ArrayList<>();
        fill(arr , root , 0 , 0 , arr[0].length-1);

        //下面将二维数组arr的每一个一位数组转换为ArrayList，添加到ret
        for (String[] str : arr)
        {
            ret.add(Arrays.asList(str));
        }

        return ret;
    }

    //在数组arr的 row 行的 colBegin到colEnd 的中间位置添加 node的相应元素
    private void fill(String[][] arr , TreeNode node , int row , int colBegin , int colEnd)
    {
        if(node == null)
            return;

        int mid = (colBegin + colEnd)/2;
        //将node的值设置到row行的相应位置
        arr[row][mid] = node.val+"";

        //下面继续将node的左右子树元素添加尽量
        fill(arr , node.left , row+1 , colBegin , mid-1);
        fill(arr , node.right , row+1 , mid+1 , colEnd);
    }

    //获取二叉树的最大深度
    private int getHeight(TreeNode node)
    {
        if(node == null)
            return 0;
        return 1 + Math.max(getHeight(node.left) , getHeight(node.right));
    }
}
