package com.lkj.problem4;

/**
 时间复杂度分析：我们每次递归都删除 k/2 个元素，时间复杂度是 logk，因为 k=(m+n)/2
 */
public class LCTest4_3
{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int target1 = (len1+len2+1)/2;
        int target2 = (len1+len2+2)/2;

        //计算合并后数组从左向右第 (len1+len2+1)/2 及 (len1+len2+2)/2 个数的值，相加乘以0.5即可
        return (getKth(nums1 , 0 , len1-1 , nums2 , 0 , len2-1 , target1) +
                getKth(nums1 , 0 , len1-1 , nums2 , 0 , len2-1 , target2)) * 0.5;

    }

    //计算nums1与nums2合并排序后的数组中，从左向右数第k个数的值
    private int getKth(int[] nums1 , int start1 , int end1  , int[] nums2 , int start2 , int end2 , int k)
    {
        int len1 = end1-start1+1;
        int len2 = end2-start2+1;

        //1、有一个数组长度小于等于0的时候（加小于是为了保险起见，一般不会出现小于的情况）
        if(len1<=0)
            return nums2[start2+k-1];
        if(len2<=0)
            return nums1[start1+k-1];

        //2、当k=1的情况
        if(k==1)
            return Math.min(nums1[start1] , nums2[start2]);

        //3、当前面2类情况都没有出现，需要删除某个数组 k/2 的数据
        //由于 k/2 有可能大于当前数组的长度，因此需要取 len1、len2 与 k/2 的最小值，这样就可以避免多删除而出现数组角标越界
        int index1 = start1 + Math.min(k/2 , len1) - 1;
        int index2 = start2 + Math.min(k/2 , len2) - 1;

        //nums1[index1] >= nums2[index2]的时候删除 nums2数组index2及当前位置前面位置的数；否则删除nums1的index1及该位置前面的数
        if(nums1[index1] >= nums2[index2])
            return getKth(nums1 , start1 , end1 , nums2 , index2+1 , end2 , k-(index2-start2+1));
        else
            return getKth(nums1 , index1+1 , end1 , nums2 , start2 , end2 , k-(index1-start1+1));

    }
}
