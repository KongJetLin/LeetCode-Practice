package com.lkj.problem52;

import java.util.Arrays;

/**
这一题与51的解法是相同的，不过这一题返回的是结果的数量
 */
public class LCTest52
{
    int rowOrColLen;
    int totalSolutions;
    public int totalNQueens(int n)
    {
        if(n<=0)
            return 0;
        rowOrColLen = n;

        //1、同样是定义选择的路径
        char[][] board = new char[n][n];
        //初始化board
        initCharArray(board);

        backtrack(0 , board);
        return totalSolutions;
    }

    private void backtrack(int row , char[][] board)
    {
        //2、结束条件
        if(row == rowOrColLen)
        {
            totalSolutions++;
            return;
        }

        //选择列表：每一行的每一列元素都可以选择
        for (int col = 0; col < rowOrColLen ; col++)
        {
            //如果这一行这一列位置不可放元素，略过
            if(!isValid(board , row , col))
                continue;

            /**进行选择*/
            board[row][col] = 'Q';
            backtrack(row+1 , board);
            board[row][col] = '.';
        }

    }

    private void initCharArray(char[][] board)
    {
        for (char[] chars : board)
        {
            Arrays.fill(chars , '.');
        }
    }

    private boolean isValid(char[][] board , int row , int col)
    {
        for (int i = 0; i < row ; i++)
        {
            if(board[i][col] == 'Q')
                return false;
        }

        for (int i = row-1 , j=col+1; i>=0 && j<rowOrColLen ; i-- ,j++)
        {
            if(board[i][j] == 'Q')
                return false;
        }

        for (int i = row-1 , j=col-1; i>=0 && j>=0 ; i-- ,j--)
        {
            if(board[i][j] == 'Q')
                return false;
        }

        return true;
    }


}
