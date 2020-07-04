package com.lkj.problem101;

import java.util.LinkedList;
import java.util.Queue;

public class LCTest101
{

    public static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { this.val = x; }
    }

    public static boolean isSymmetric1(TreeNode root)
    {
        if(root == null)
            return true;
        //利用队列，先实现广度优先遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int arrLen = 1;
        while(!queue.isEmpty())
        {
            int len = queue.size();
            if(len != arrLen)
                return false;
            int[] comArr = new int[len];
            for (int i = 0; i < len ; i++)
            {
                TreeNode cur = queue.remove();
                comArr[i] = cur.val;
                //一旦某一行的元素不是镜像对称，直接返回false
                if(cur.left != null)
                    queue.add(cur.left);
                if(cur.right != null)
                    queue.add(cur.right);
            }
            if(!compare(comArr))
                return false;
            arrLen *= 2;
        }
        return true;
    }

    //比较数组内的元素是否镜像对称
    private static boolean compare(int[] comArr)
    {
        for (int begin = 0 , end = comArr.length-1 ; begin <= end ; begin++ , end--)
        {
            if(comArr[begin] != comArr[end])
                return false;
        }
        return true;
    }
    /**
    自己的方法还是差一点，无法通过所有的测试用例！
     */
//-----------------------------------------------------------------
    //递归，时间复杂度O(n)
    public boolean isSymmetric2(TreeNode root)
    {
        //没必要从根结点开始比较，从左右孩子结点开始利用p，q指针左移右移就可以，这样就不会多遍历一次n
        if(root == null)
            return true;
        else
            return check2(root.left , root.right);
    }

    //使用2个指针 p，q，分别使得q左移右移，p左移右移，查看相应位置的元素是否相等
    private boolean check2(TreeNode p , TreeNode q)
    {
        if(p==null && q==null)
            return true;
        if(p==null || q==null)
            return false;//p，q只有一个位null，直接返回false
        return p.val==q.val && check2(p.left , q.right) && check2(p.right , q.left);
    }


    //迭代（其实类似于递归，只不过这里使用队列来实现）
    public boolean isSymmetric(TreeNode root)
    {
        if(root == null)
            return true;
        else
            //从root的左右孩子结点开始比较，否则多花费O(n)时间
            return check(root.left , root.right);
    }


    private boolean check(TreeNode p , TreeNode q)
    {
        Queue<TreeNode> queue = new LinkedList<>();
        //先将2个结点入队
        queue.offer(p);
        queue.offer(q);

        //比较过程中，队列中相邻的2个结点的值要相等
        while (!queue.isEmpty())
        {
            p = queue.remove();
            q = queue.remove();

            //2个结点都为null，满足对称，进入下一次比较
            if(p==null && q==null)
                continue;
            //2个结点只有一个位null，或者2个结点的值不同，直接返回false
            if((p==null || q==null) || (p.val != q.val))
                return false;

            //随后，按顺序将p的左孩子，q的右孩子，p的右孩子，q的左孩子添加到队列，进行下一轮比较
            queue.offer(p.left);
            queue.offer(q.right);
            queue.offer(p.right);
            queue.offer(q.left);
        }
        return true;
    }






//-------------------------------------------------------------
//    public static void main(String[] args)
//    {
//        TreeNode root = new TreeNode(1);
//        TreeNode cur = root;
//        cur.left = new TreeNode(2);
//        cur.right = new TreeNode(2);
//        cur.left.left = new TreeNode(3);
//        cur.left.right = new TreeNode(4);
//        cur.right.left = new TreeNode(4);
//        cur.right.right = new TreeNode(3);
//
//        System.out.println(isSymmetric(root));
//
//    }
}
