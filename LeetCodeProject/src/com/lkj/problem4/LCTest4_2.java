package com.lkj.problem4;

/** 解法2：
 与解法1类似，但是不创建新的数组，而是找到新数组arr 中 arr[arr.length/2] 与 arr[arr.length/2 - 1] 的值，
 然后看一下新数组长度是奇数还是偶数，奇数就直接返回 arr[arr.length/2]，偶数就返回 (arr[arr.length/2] + arr[arr.length/2 - 1])*0.5

 时间复杂度：遍历全部数组 O(n+m)；
 空间复杂度： O(1)
 */
public class LCTest4_2
{
    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        int totalLen = nums1.length + nums2.length;

        int left = -1;
        int right = -1;

        int index1 = 0;
        int index2 = 0;

        //最后 left 保存 arr[arr.length/2 - 1]，而 right 保存 arr[arr.length/2]
        for (int i = 0; i <= totalLen/2; i++)
        {
            left = right;

            if (index1!=nums1.length && index2!=nums2.length)
            {
                if(nums1[index1]<=nums2[index2])
                    right = nums1[index1++];
                else
                    right = nums2[index2++];

                //在for循环中，我们每一次都只计算一次right的值，因此每次赋值一次需要将当前循环结束！
                //下面2类情况是为了覆盖某个两个数组有一个遍历完毕的情况！
                continue;
            }

            if(index1!=nums1.length)
            {
                right = nums1[index1++];
//                continue; 这里不continue也可以，因为下面2类情况只会出现一次！！！
            }
            if(index2!=nums2.length)
                right = nums2[index2++];
        }

        return (totalLen%2==0) ? (left+right)*0.5 : right;
    }
}
