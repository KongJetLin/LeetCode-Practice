package com.lkj.problem107;

import java.util.*;

public class LCTest107
{
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //使用2个队列实现
    public List<List<Integer>> levelOrderBottom1(TreeNode root)
    {
        /**
        这里使用2个队列，分别存储父行以及父行的子行的元素，这样就可以一行一行地输出！
         当然，最后需要将结果翻转过来！
         */
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();

        if(root == null)
            return ret;

        queue1.add(root);
        while(!queue1.isEmpty())
        {
            List<Integer> temp = new ArrayList<>();
            while (!queue1.isEmpty())
            {
                TreeNode removeNode = queue1.remove();
                if(removeNode.left != null)
                    queue2.add(removeNode.left);
                if(removeNode.right != null)
                    queue2.add(removeNode.right);
                temp.add(removeNode.val);
            }
            ret.add(temp);

            //交换 queue1 与 queue2
            Queue<TreeNode> swap = new LinkedList<>();
            swap = queue1;
            queue1 = queue2;
            queue2 = swap;
        }

        Collections.reverse(ret);
        return ret;
    }
//----------------------------------------------

    //只使用一个队列实现
    public List<List<Integer>> levelOrderBottom(TreeNode root)
    {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();

        if (root == null) return ret;

        queue.add(root);
        while (!queue.isEmpty())
        {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();//注意，队列的size需要实现保存！
            for (int i = 0; i < size ; i++)
            {
                TreeNode removeNode = queue.remove();
                if(removeNode.left != null)
                    queue.add(removeNode.left);
                if(removeNode.right != null)
                    queue.add(removeNode.right);
                temp.add(removeNode.val);
            }
            ret.add(0 , temp);//每次都将新的ArrayList放在头部，这样后面就不需要翻转
        }
        return ret;
    }
}
