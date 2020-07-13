package com.lkj.problem4;

/**
 将求2个正序数组nums1、nums2中位数，转为求2个数组合并后（并不需要真的合并）的数组中第k小的数的问题。
 假设 nums1 长度为n，nums2长度为m
 1）若 n+m 为奇数，此时合并后的数组中位数的下标为：(n+m)/2+1 = (n+m+2)/2 ，也可以表示为 (n+m+1)/2，
    那么我们可以找到 合并后数组第 (n+m+2)/2 小的元素 与 (n+m+1)/2 小的元素，相加后除以二得到中位数。
    其实n+m为奇数的时候，(n+m+2)/2 = (n+m+1)/2，实际上我们计算了2次！（这样计算是为了配合下面偶数的情况）

 2）若 n+m 为偶数，我们需要if(len1 == 0)//nums1在上一轮走完了
 return nums2[start2+k-1];，其中 (n+m)/2 = (n+m+1)/2，(n+m)/2+1 = (n+m)/2+1，
 那么我们同样可以找到 合并后数组第 (n+m+2)/2 小的元素 与 (n+m+1)/2 小的元素，相加后除以二得到中位数。
 这里 (n+m+2)/2 != (n+m+1)/2，实际上就是找到中间的2个数，求和然后求平均值得到中位数！

时间复杂度分析：我们每次递归都删除 k/2 个元素，时间复杂度是 logk，因为 k=(m+n)/2，
 那么总体时间复杂度为：O(logn)
 */
public class LCTest4
{
    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        int length1 = nums1.length;
        int length2 = nums2.length;

        //接下来我们分别求 (n+m+2)/2 ， (n+m+1)/2
        int left = (length1+length2+1)/2;
        int right = (length1+length2+2)/2;

        //我们利用上面的分析，求合并后数组第 (n+m+2)/2 小的元素 与 (n+m+1)/2 小的元素。取和并除以二得到中位数
        return (getKth(nums1 , 0 , length1-1 , nums2 , 0 , length2-1 , left) +
                getKth(nums1 , 0 , length1-1 , nums2 , 0 , length2-1 , right))*0.5;
    }

    //这个方法用于删除 nums1从start1到end1区间内 k/2 个元素，或者删除 nums2从start2到end2区间内 k/2 个元素
    // 具体删除哪一个，由 nums1[k/2] 与 nums2[k/2] 位置数的大小决定！
    private int getKth(int[] nums1 , int start1 , int end1 , int[] nums2 , int start2 , int end2 , int k)
    {
        //先求此时2个数组内剩下的还没有“删除”的元素的个数
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        /** 终结条件
        递归最后有2个终结条件：
         1）2个数组都没有出现“删除”到末尾的情况，那么我们每次都将k/2，最终k=1，此时我们只需比较 nums1与nums2第一个元素那个小即可！
         2）nums1 或者 nums2 走完，此时我们只需要在另一个数组上走k步即可找到第k小的数
         */
        //先判断2个数组是否有一个走完
        if(len1 == 0)//nums1在上一轮走完了
            return nums2[start2+k-1];
        if(len2 == 0)//nums2在上一轮走完了
            return nums1[start1+k-1];
        //注意，num1与nums2肯定不会同时走完

        //数组都没有走完的情况下，判断k是否为1
        if(k == 1)
            return Math.min(nums1[start1] , nums2[start2]);

        //如果没有终结，我们需要继续删除当前数组元素
        /**
        此时我们要计算删除nums1或者nums2时，指针从start开始要走的长度。
         1）若nums1或者nums2没有走到末尾，我们应该使得指针从start1或者start2开始，走k/2个位置，
            然后比较这两个位置nums1与nums2那个元素小，再决定这一轮删除那一边
         2）nums1或者nums2有一个走到末尾，此时他们能走的最大长度小于 k/2，是 length-start+1
         */
        //注意，nums1与nums2不会同时到达末尾
        int index1 = start1 + Math.min(k/2 , len1) - 1;
        int index2 = start2 + Math.min(k/2 , len2) - 1;

        //比较此时nums1[index1] 与 nums2[index2] 位置的大小，决定谁进行“走”/“删除”
        if(nums1[index1] > nums2[index2])
            //此时nums2走，注意，此时数组2“删除”的元素个数不是k/2，因为有可能数组2已经走到底，
            // 我们用 k-(index2-start2+1) 替代，这里 (index2-start2+1) 表示删除的元素个数（注意，index2位置也会删除）
            return getKth(nums1 , start1 , end1 , nums2 , index2+1 , end2 , k-(index2-start2+1));
        else //如果最后有2个元素相等，其实可以随便删除一个，留下另一个元素与其他元素比较
            return getKth(nums1 , index1+1 , end1 , nums2 , start2 , end2 , k-(index1-start1+1));
    }
}
