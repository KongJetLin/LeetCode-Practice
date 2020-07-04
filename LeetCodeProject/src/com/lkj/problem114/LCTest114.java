package com.lkj.problem114;

import java.util.Stack;

public class LCTest114
{
    public class TreeNode
    {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right)
      {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    /** 解法1
     1、将左子树插入到右子树的地方
     2、将原来的右子树接到左子树的最右边节点
     3、考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     */
    public void flatten1(TreeNode root)
    {
        //当结点root不为null的时候，我们持续按上面的步骤设置
        while(root != null)
        {
            //若干当前root结点的左子树为null，我们不需要转换，直接将root指向右孩子
            if(root.left == null)
            {
                root = root.right;
            }
            else
            {
                //否则，我们执行上面的三步
                TreeNode cur = root.left;//记录root的左子树
                //遍历到cur最右侧的结点
                while(cur.right != null)
                {
                    cur = cur.right;
                }
                //将原来root的右子树接到此处cur的右边
                cur.right = root.right;
                //root的右子树变成root的左子树
                root.right = root.left;
                //记得将root此时的左子树设置为null
                root.left = null;

                //最后，转换完成后，将root指向其右孩子
                root = root.right;
            }
        }
    }

    /**
    方法2：利用非递归解法的前序遍历
     */
    public void flatten(TreeNode root)
    {
        if(root == null)
            return;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        TreeNode cur = null;
        while(!stack.isEmpty())
        {
            TreeNode temp = stack.pop();
            //注意，此处是先右后左，否则无法实现前序
            if(temp.right != null)
                stack.push(temp.right);
            if(temp.left != null)
                stack.push(temp.left);

            if(cur != null)
            {
                //后面，cur不为null，将出栈的元素作为cur的右孩子，同时，设置cur左孩子为left
                //最后，记得将cur向右移动
                cur.right = temp;
                cur.left = null;
                cur = cur.right;
            }
            else
            {
                //第一次进来，cur=null，此时将第一个出栈的元素赋予它
                cur = temp;
            }
        }
    }

}
