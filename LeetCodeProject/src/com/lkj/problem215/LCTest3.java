package com.lkj.problem215;

/** 模仿快排
 * 我们需要找到排序后数组下标为 nums.length-k 的元素，我们这里是边排序边找。
 * 每次找到中轴点 pivot后，排序后这个元素叶子这里，即pivot已经排序好，看看 pivot是不是nums.length-k，
 * 是的话直接返回，不是继续寻找！
 * 时间复杂度：O(N)，这里比较难，参考原题解答案。
 */
public class LCTest3
{
    int k;
    public int findKthLargest(int[] nums, int k)
    {
        this.k = k;
        return find(nums , 0 , nums.length-1);
    }

    private int find(int[] nums, int left, int right)
    {
        //中轴点 pivot 就是数组中已经固定位置的下标，即排序后pivot位置的元素就是放在pivot
        int pivot = partition(nums , left , right);
        //我们看看 pivot 是不是我们要找的排序后的 nums.length-k，是的话直接返回，不是继续寻找
        if(pivot == nums.length-k)
            return nums[pivot];

        if(pivot < nums.length-k)
            return find(nums , pivot+1 , right);//大于向右寻找
        else
            return find(nums , left , pivot-1);//小于向左寻找
    }

    private int partition(int[] nums, int left, int right)
    {
        int pivotNum = nums[left];

        int start = left+1;//注意start从left+1开始
        int end = right;

        while (true)
        {
            while (start <= end && nums[start] < pivotNum) start++;

            while (start <= end && nums[end] >= pivotNum) end--;

            if (start > end) break;
            swap(nums , start , end);
        }
        swap(nums , left , end);
        return end;
    }

    private void swap(int[] arr , int i , int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
