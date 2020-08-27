package com.lkj.problem215;

import java.util.Arrays;

/** 数组中的第K个最大元素
排序解法，第 k 个最大元素，即第k大的元素，在数组排序后，它在数组中的位置是 nums.length-k。
 数组 Arrays.sort 排序使用的是快排，时间复杂度是 O(NlogN)
 */
public class LCTest215
{
    public int findKthLargest(int[] nums, int k)
    {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
