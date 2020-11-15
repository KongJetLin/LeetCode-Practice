package com.lkj.problem11;

/**
 暴力法：时间复杂度是 O(n^2)，不推荐
 */
public class LCTest11_1
{
    public int maxArea(int[] height)
    {
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++)
        {
            for (int j = i+1; j < height.length ; j++)
            {
                int curArea = (j-i) * Math.min(height[i] , height[j]);
                if( curArea > maxArea)
                    maxArea = curArea;
            }
        }
        return maxArea;
    }
}
