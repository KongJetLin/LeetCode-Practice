package com.lkj.problem162;

/** 从头到尾遍历。时间复杂度O(n)
我们从头到尾遍历数组，只需要判断 nums[i]>nums[i+1] 是否成立即可，不需要判断 nums[i]>nums[i-1]，这是因为：
 （1）首先，数组相邻的元素不相等，即说明nums[i]要么大于nums[i+1]、nums[i-1]，要么小于，不会等于；
 （2）我们从头到尾遍历，会有3种情况：
    (i) 数组是上升序列：那么我们判断第一个元素大于第二个元素：nums[0]>nums[1]，且nums[-1] = nums[n] = -∞，第一个元素就是峰值，返回i=0即可。
    (ii) 数组是下降序列，那么 nums[i]>nums[i+1] 一直不成立，遍历完最后一个元素，说明最后一个元素是峰值，返回 nums.length-1 最后一个元素下标即可
    (iii) 数组有上升也有下降：如果数组一开始就下降，那么数组开头段与(i)相同，直接找到第一个元素是峰值；如果数组先上升后下降，
        那么 nums[i]>nums[i+1] 一直不成立，直到某个下标 K，我们知道K左边的元素全部小于nums[K]，因为左边上升，又有 nums[k]>nums[k+1]，
        那么 K 位置的元素就是峰值。
 总结：从头到尾遍历数组，判断 nums[i]>nums[i+1] 是否成立，如果某个K满足，则K就是峰值元素下标；如果数组没有满足 nums[i]>nums[i+1]，
        说明数组是上升数组，最后一个元素是峰值，返回最后一个元素
 */
public class LCTest162
{
    public int findPeakElement(int[] nums)
    {
        for (int i = 0; i < nums.length-1 ; i++)
        {
            if(nums[i]>nums[i+1])
                return i;
        }
        return nums.length-1;
    }
}
