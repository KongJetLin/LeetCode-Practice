package com.lkj.problem54;

import java.util.ArrayList;
import java.util.List;
/**
 使用上下左右四个坐标标注，每次遍历完就判断 up>dowm，或者right<left（注意，最后一行等于是满足的！必须是小于才停止！）
 参考：https://leetcode-cn.com/problems/spiral-matrix/solution/cxiang-xi-ti-jie-by-youlookdeliciousc-3/
 */
public class LCTest54_1
{
    public List<Integer> spiralOrder(int[][] matrix)
    {
        List<Integer> list = new ArrayList<>();
        if(matrix==null || matrix.length==0)
            return list;

        int up = 0 , down = matrix.length-1 , left = 0 , right = matrix[0].length-1;
        while (true)
        {
            for (int col = left; col <= right ; col++)
                list.add(matrix[up][col]);
            if(++up>down)
                break;

            for (int row = up; row <= down ; row++)
                list.add(matrix[row][right]);
            if(--right<left)
                break;

            for (int col = right; col >= left ; col--)
                list.add(matrix[down][col]);
            if(--down<up)
                break;

            for (int row = down; row >= up ; row--)
                list.add(matrix[row][left]);
            if(++left>right)
                break;
        }
        return list;
    }
}
