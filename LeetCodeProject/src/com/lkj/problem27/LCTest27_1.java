package com.lkj.problem27;

/**
 * 双指针复制法：遇到一个val值，直接将后面的值覆盖到val的位置即可。
 * 时间复杂度：O(n^2)
 */
public class LCTest27_1
{
    public static int removeElement(int[] nums, int val)
    {
        if(nums==null || nums.length==0)
            return 0;

        int operateIndex = 0;//记录操作的位置

        //最多只需要判断 nums.length 次，用一个for循环来限制判断的次数
        for (int i = 0; i < nums.length; i++)
        {
            //遇到val值则操作的下标不移动
            if(nums[operateIndex]==val)
            {
                for (int j = operateIndex+1; j < nums.length ; j++)
                {
                    nums[j-1] = nums[j];
                }
            }
            else
                operateIndex++;//遇到不是val，则index+1
        }
        //operateIndex记录的就是数组中不是val的元素的个数，因为每次遇到不是val，operateIndex就会+1！
        return operateIndex;
    }
}
