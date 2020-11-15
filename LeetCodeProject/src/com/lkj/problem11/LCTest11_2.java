package com.lkj.problem11;
/** 双指针
 参考：https://leetcode-cn.com/problems/container-with-most-water/solution/on-shuang-zhi-zhen-jie-fa-li-jie-zheng-que-xing-tu/
 主要看自己的理解！

 对于 height 数组，领首部指针为 i=0，尾部指针为 j=height.length-1. 此时计算当前区域的面积 curArea = (j-i) * Math.min(height[i] , height[j]).
 我们将此处 (j-i) 记为横向长度，将 Math.min(height[i] , height[j]) 记为纵向长度。
 假设此时 i 位置的柱子比较矮，即 height[j]>height[i]，即 Math.min(height[i] , height[j])=height[i]。
 那么我们知道，从 i 到 j-1 位置，横向长度最长为 j-1-i<j-i；纵向长度上，要求 Math.min(height[i] , height[i+1 到 j-1 的任意位置])，此时最高也只能取到 height[i]，
 此时height[i+1 到 j-1 的任意位置]都大于等于 height[i]； 如果 height[i+1 到 j-1 的任意位置]存在小于 height[i] 的值，
 那么 Math.min(height[i] , height[i+1 到 j-1 的任意位置]) < height[i]。

 即 Math.min(height[i] , height[i+1 到 j-1 的任意位置])<=height[i]，那么 (j-1-i)*Math.min(height[i] , height[0 到 j-1]) < (j-i) * Math.min(height[i] , height[j]),
 因此，对于 i到i+1，i到i+2，...，i到j-1所有的状态，他们求出来的值都小于当前的 curArea，都可以排除！因此，我们可以将 i,j 中较小的柱子移除！

 双指针的时间复杂度：O(n)
 */
public class LCTest11_2
{
    public int maxArea(int[] height)
    {
        int i = 0;
        int j = height.length-1;
        int maxArea = 0;

        //只有 i<j 的时候，才有计算面积的必要！
        while (i<j)
        {
            int curArea = (j-i) * Math.min(height[i] , height[j]);
            maxArea = Math.max(maxArea , curArea);
            if(height[i]<height[j])
                i++;//i较小，将 i 移除
            else
                j--;//j较小，将j移除
        }
        return maxArea;
    }
}
