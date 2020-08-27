package com.lkj.problem103;

import com.lkj.Problem203.ListNode;

import java.util.*;

public class LCTest103
{
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root)
    {
        List<List<Integer>> list = new ArrayList<>();
        /**
         * 注意，这里必须判断root是否为null，当其为null的时候，queue会添加一个null，
         * 在循环中就会把null赋予tempNode，这样在 tempNode.val 的时候就是空指针异常
         */
        if(root == null)
            return list;

        Queue<TreeNode> queue = new LinkedList<>();//注意队列使用LinkedList
        queue.offer(root);
        boolean reverseFlag = false;

        while(!queue.isEmpty())
        {
            List<Integer> tempList = new ArrayList<>();
            int size = queue.size();
            while (size!=0)
            {
                TreeNode tempNode = queue.poll();
                tempList.add(tempNode.val);
                if(tempNode.left!=null)
                    queue.offer(tempNode.left);
                if(tempNode.right!=null)
                    queue.offer(tempNode.right);
                size--;
            }
            if(reverseFlag)
                Collections.reverse(tempList);
            //注意将反转标志反过来！
            reverseFlag = !reverseFlag;

            list.add(tempList);
        }
        return list;
    }
}
