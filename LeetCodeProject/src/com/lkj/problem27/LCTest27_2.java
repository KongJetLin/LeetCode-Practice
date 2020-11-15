package com.lkj.problem27;

/**
 双指针：前面的方法是先找到一个val值，然后使得后面的值覆盖这个val位置的值！
 我们可以用一个指针 index 表示非val值的位置，然后每次找到一个非val值，就将这个非val值赋予index，并使得index++，
 最后index值就是返回值。这样，我们就省去将val值覆盖的n个步骤，时间复杂度变为O(n)
 时间复杂度：O(n)
 */
public class LCTest27_2
{
    public int removeElement(int[] nums, int val)
    {
        int index = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if(nums[i]!=val)
            {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
