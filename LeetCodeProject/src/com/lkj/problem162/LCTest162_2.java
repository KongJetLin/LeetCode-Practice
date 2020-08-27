package com.lkj.problem162;

/** 二分搜索，时间复杂度：O(logN)
对于区间 0-nums.length-1，我们进行二分查找，每次找到区间 [start,end]的中点 mid=(start+end)/2，将 nums[mid]与nums[mid+1]比较，
 若 nums[mid]< nums[mid+1]，说明峰值在右边区间，将区间缩小为 （mid+1 , end）；
 若 nums[mid]>nums[mid+1]，说明峰值在左边区间（包括mid，因为mid可能为峰值），则将区间缩小为 (start , mid)。

 为什么 nums[mid]与nums[mid+1]比较，而不是与 nums[mid-1]比较？
 因为我们是要缩小区间，必须保证比较位置 mid+1 在区间（start，end）之间，如果是 mid-1，这个位置可能不在 （start，end）区间。
 比如对于 （2,3），mid=2，如果与 mid+1 比较，则最后缩小后的区间为 （3 mid+1,3 end）或者 （2 start,2 mid），
 如果与 mid-1 = 1比较，当 nums[mid-1]>nums[mid]的时候，峰值在左边，那么区间缩小为 （2 start ， 1 mid-1），这时区间错误，并无法找到峰值。
 这里与 mid=(start+end)/2 即mid 的取法相关。

 当 mid=(start+end+1)/2，对于 （2,3），最后 mid=3，那么mid+1不在区间内，而mid-1在区间内，因此此时 nums[mid]与nums[mid-1]比较！
 当 nums[mid-1]>nums[mid]的时候，峰值在左边，那么区间缩小为 （2 start ， 2 mid-1）；
 当 nums[mid-1]<nums[mid]的时候，峰值在右边（包含mid，mid处可能为峰值），那么区间缩小为 （3  mid ， 3 end）。

 总结：
 nums[mid]与左边还是右边比较，与 mid 的取法相关，若 mid=(start+end+1)/2，应当使得 mid与mid-1比较，当区间只有2个元素的时候，如（2,3），mid=3，
  此时 mid-1=2 在(2,3)之间，缩小后的区间才不会超出原来 （start，end）的范围；

 若 mid=(start+end)/2，应当使得 mid与mid+1比较，当区间只有2个元素的时候，如（2,3），mid=2，
 此时 mid+1=3 在(2,3)之间，缩小后的区间才不会超出原来 （start，end）的范围；
 如果此时 mid与mid-1比较，mid=2，mid-1=1，若nums[mid]>nums[mid-1]，区间为（mid 2，end 3），继续二分的结果相同，此时就会无限循环下去。
 若 nums[mid]<nums[mid-1]，区间为（start 2， mid-1 1），则跳出循环，返回start=2，但是2位置并不是峰值，因为1位置的数大于2！！
 */
public class LCTest162_2
{
    public int findPeakElement(int[] nums)
    {
        if(nums == null || nums.length == 0)
            return -1;
        return search(nums , 0 , nums.length-1);
    }

    private int search(int[] nums , int start , int end)
    {
        while(start<end)
        {
            int mid = (start+end)>>>1;
            //mid与mid+1比较，缩小区间
            if(nums[mid] > nums[mid+1])
                end = mid;
            else
                start = mid+1;
        }
        return start;
    }
}
