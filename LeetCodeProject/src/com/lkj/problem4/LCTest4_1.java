package com.lkj.problem4;

/** 方法1.（最简单的做法）
 * 创建新数组，并将原来2个数组的值放入新的数组中，然后返回新数组的中位数。
 * 对于长度为奇数的数组arr，arr.length/2 = (arr.length-1)/2，即这2个值都是中位数的位置。
 * 对于长度为偶数的数组，中间2个数的角标就是 arr.length/2 与 (arr.length-1)/2。
 * 因此不管数组的长度是奇数还是偶数，我们求中间值只需要求：(arr[(arr.length-1)/2] + arr[arr.length/2])*0.5即可
 *
 * 时间复杂度：遍历全部数组 O(n+m)；
 * 空间复杂度：开辟了一个数组，保存合并后的两个数组 O(n+m)
 *
 参考：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
 */
public class LCTest4_1
{
    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int i = 0;
        int j = 0;
        int k = 0;
        int[] newArr = new int[len1+len2];

        while (i!=len1 && j!=len2)
        {
            if(nums1[i]<=nums2[j])
                newArr[k++] = nums1[i++];
            else
                newArr[k++] = nums2[j++];
        }

        while (i!=len1)
            newArr[k++] = nums1[i++];
        while (j!=len2)
            newArr[k++] = nums2[j++];

        return (newArr[newArr.length/2] + newArr[(newArr.length-1)/2])*0.5;

    }
}
