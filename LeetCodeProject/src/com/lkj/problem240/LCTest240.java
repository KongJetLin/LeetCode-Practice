package com.lkj.problem240;

/**
 * 比较容易，从左下角或者右上角开始搜索即可！
 * 时间复杂度：O(n^2)
 */
public class LCTest240
{
    public boolean searchMatrix(int[][] matrix, int target)
    {
        if(matrix == null || matrix.length==0)
            return false;

        int rowIndex = 0;
        int colIndex = matrix[0].length-1;
        while(rowIndex<matrix.length && colIndex>=0)
        {
            if(target == matrix[rowIndex][colIndex])
                return true;
            else if(target>matrix[rowIndex][colIndex])
                rowIndex++;
            else
                colIndex--;
        }
        return false;
    }
}
