package com.lkj.problem51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 回溯算法
参考：https://leetcode-cn.com/problems/n-queens/solution/hui-su-suan-fa-xiang-jie-by-labuladong/

 回溯算法框架：
 result = []
 def backtrack(路径, 选择列表):
 if 满足结束条件:
     result.add(路径)
     return

 for 选择 in 选择列表:
     做选择
     backtrack(路径, 选择列表)
     撤销选择

 这里回溯算法我们同样考虑3个部分：选择的路径、当前选择列表、结束条件

 注意点：
 1、某个位置放置皇后，那么它的这一行，这一列不可再放置皇后。同样，它的主对角线和副对角线也不可以再放置皇后；
 2、选择路径保存的是前面行的放置结果，选择列表是当前行的所有列位置，如果这一行的某个列位置可以放皇后，
    就在这个位置放置皇后，那么选择路径就添加这一行，随后进入下一行的选择。最后将满足的二维数组转换为字符串存储到res。

 代码注意：整体的逻辑是没问题的，写代码的过程需要注意几点：
 1、必须等到row=rowOrConLen 才结束，否则如果再rowOrConLen-1结束，最后一行不会设置“Q”
 2、在填充“.”的时候，先foreach取出每一行，随后再使用Arrays.fill()填充，使用2个foreach会填充失败！
 */
public class LCTest51
{
    List<List<String>> res;
    int rowOrConLen;//定义一个变量，用于记录行或者列的长度（所有的行列长度都是相同的，都是n）

    public List<List<String>> solveNQueens(int n)
    {
        if(n<=0)
            return null;
        res = new ArrayList<>();
        rowOrConLen = n;

        //1、路径：我们使用一个二维数组来保存路径。
        char[][] board = new char[n][n];
        //将这个二维数组初始化，即将每一个位置的值都设置为“.”
        initCharArray(board);
        //随后回溯放置皇后
        backtrack(0 , board);

        return res;
    }

    //回溯放置皇后（参数是放置的行号和保存结果的路径，我们在方法参数上换行，在方法中换列）
    private void backtrack(int row , char[][] board)
    {
        //2、结束条件（当前行号=rowOrConLen，即最后一行的“Q也已经设置，进入最后一行的下一个row”，这里不可以设置为rowOrConLen-1，这样最后一行不会设置“Q”）
        if(row == rowOrConLen)
        {
            res.add(charToString(board));
            return;//结束递归
        }

        //3、选择列表的获取：选择列表其实就是这一行的每一列（我们需要将不合法的列排除）
        //对这一行的每一列进行遍历，同时排除不满足的列
        for (int col = 0; col < rowOrConLen ; col++)
        {
            if(!isValid(board , row , col))
                continue;//如果这一行的这一个位置不能放置，则略过

            /**选择过程*/
            //可以放置，将这一行的这一个位置设置为“Q”
            board[row][col] = 'Q';
            //路径中这一行已经放置好，进入下一行的放置
            backtrack(row+1 , board);
            //这一行的这一列选择后，将其撤销，随后才可以循环选择这一行的其他列
            board[row][col] = '.';
        }

    }

    //二维数组初始化
    private void initCharArray(char[][] board)
    {
        for (char[] chars : board)
        {
            Arrays.fill(chars , '.');//注意，初始化的时候先取出每一行的数组，然后使用Arrays.fill()方法对数组进行填充！
        }
    }

    //判断某一行的某一列是否有效
    private boolean isValid(char[][] board , int row , int col)
    {
        //判断前面已经放置皇后的其他行的这一列是否放置皇后
        for (int i = 0; i < row ; i++)
        {
            if(board[i][col] == 'Q')
                return false;
        }

        //判断右上对角线是否放置皇后（右下不需要放置，因为还没有判断到）
        for (int i = row-1 , j = col+1; i>=0 && j<rowOrConLen ; i--,j++)
        {
            if(board[i][j] == 'Q')
                return false;
        }

        //判断左左上角对角线能否放置皇后
        for (int i = row-1 , j = col-1; i>=0 && j>=0 ; i--,j--)
        {
            if(board[i][j] == 'Q')
                return false;
        }

        return true;//全部检测通过，返回true
    }

    //将board转换为字符串并添加到List
    private ArrayList<String> charToString(char[][] board)
    {
        ArrayList<String> strings = new ArrayList<>();
        for (char[] chars : board)
        {
            strings.add(String.valueOf(chars));
        }
        return strings;
    }

//    public static void main(String[] args)
//    {
//        List<List<String>> lists = solveNQueens(4);
//        for (List<String> list : lists)
//        {
//            for (String s : list)
//            {
//                System.out.print(s);
//            }
//            System.out.println();
//        }
//    }
}
