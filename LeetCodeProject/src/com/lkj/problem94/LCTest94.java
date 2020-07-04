package com.lkj.problem94;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LCTest94
{

     public class TreeNode
     {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }

    List<Integer> ret;
    //递归实现
    public List<Integer> inorderTraversal1(TreeNode root)
    {
        ret = new ArrayList<>();
        inOrder(root);

        return ret;
    }

    private void inOrder(TreeNode node)
    {
        if(node == null)
            return;
        inOrder(node.left);
        ret.add(node.val);
        inOrder(node.right);
    }

//----------------------------------------------------------

    //基于栈的迭代实现
    public List<Integer> inorderTraversal(TreeNode root)
    {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        /**
        为什么设置 cur不为空 以及 stack不为空 2个条件？
         stack不为null，说明还有元素没有遍历，需要继续遍历；
         而cur不为空，是因为初始状态我们的stack为null，cur不为null，这是为了避免在初始状态程序就不进入循环；
         其实在后面，直到遍历完整棵树，stack都不为null。
         */
        while(cur!=null || !stack.isEmpty())
        {
            //将当前结点以及当前结点所有的左侧结点添加到栈
            while(cur != null)
            {
                stack.push(cur);
                cur = cur.left;
            }
            //将最后一个左侧结点添加到栈后，将其取出使用，然后将cur指向其右侧，遍历右侧结点。
            //此时这个最左侧的结点的左侧没有结点，对于其他的左侧结点，他们的子左侧结点已经遍历，现在遍历他们自身以及子右侧结点
            cur = stack.pop();
            ret.add(cur.val);
            cur = cur.right;
        }

        return ret;
    }
}
