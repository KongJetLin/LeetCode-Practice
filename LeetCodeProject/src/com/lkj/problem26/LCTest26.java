package com.lkj.problem26;

public class LCTest26
{
    /**
     * 你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 快慢指针法：慢指针i用于记录不重复的数，快指针j用于遍历整个数组
     */
    public int removeDuplicates(int[] nums)
    {
        if(nums.length == 0)
            return 0;

        int i = 0;
        /**
        使用for循环遍历整个数组，当nums[i]=nums[j]的时候，我们不操作，跳过当前的j，
         当 num[i]!=num[j]的时候，我们将i+1，同时将 nums[i]设置为nums[j]（必须在原数组上修改）
         */
        for (int j = 1; j < nums.length ; j++)
        {
            if(nums[i] != nums[j])
            {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
