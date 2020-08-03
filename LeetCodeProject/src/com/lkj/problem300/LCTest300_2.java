package com.lkj.problem300;

/** 贪心算法+二分查找
* 定义一个新的数组 tail[],tail[i] 表示长度为 i + 1 的所有上升子序列的结尾的最小值。数组 tail 也是一个严格上升数组。
1、原理
 我们着眼于一个上升子序列的结尾的元素，如果已经得到的上升子序列的结尾的数越小，遍历的时候就有更大的机会在其后面接上一个数，
 就会有更大的可能性构成一个更长的上升子序列。既然结尾越小越好，我们可以记录在长度固定的情况下，结尾最小的那个元素的数值。

2、算法流程
 我们遍历整个数组，这是为了更新每一个长度的上升子序列的结尾元素，使其变得更小，这样我们就有更大的机会可以在其后面接上其他元素。

 （1）在遍历数组 nums 的过程中，每来一个新数 num，如果这个数严格大于有序数组 tail 的最后一个元素，
    这就说明此时可以构成一个更长的上升子序列，就把 num 放在有序数组 tail 的后面。

 （2）若 num 小于等于tail末尾的元素，说明 tail数组中存在小于等于 num 的元素，此时可以更新tail中某一长度的上升子序列的末尾元素，使其变得更小（或不变）
    i：如果有序数组 tail 中存在等于 num 的元素，什么都不做，因为以 num 结尾的最短的「上升子序列」已经存在；（为了方便，直接替换这个相等的元素即可！）
    ii：如果有序数组 tail 中不存在等于 num 的元素，那么找到第一个大于 num 的元素 first，将 first 替换为 num，这样，假设之前 以first结尾的上升子序列
        的长度是length，那么长度为length的上升子序列的末尾元素变小，更新为 num。
    这样，就有机会遍历完 nums 数组后，找到更长的上升子序列。

 这里我们每一步都是为了更新某一个长度的上升子序列末尾元素的值，使得该值变得更小。

 时间复杂度：O(NlogN)，遍历数组使用了 O(N)，二分查找法使用了 O(logN)。
 空间复杂度：O(N)，开辟有序数组 tail 的空间至多和原始数组一样。
 */
public class LCTest300_2
{
    public int lengthOfLIS(int[] nums)
    {
        //特判
        if(nums == null)
            return 0;
        if(nums.length<=1)
            return nums.length;

        int[] tail = new int[nums.length];
        //先将tail的第一个值设置为 nums[0]（我们后面如果找到更小的数，会更新长度为1的上升子序列的末尾元素，使其变得更小）
        tail[0] = nums[0];
        int end = 0;//记录tail数组中有效的长度（即可以得到的最长上升子序列的长度）

        //开始遍历 nums[] 数组
        for (int i = 1; i < nums.length ; i++)
        {
            if(nums[i] > tail[end])
            {//若遍历到的数大于tail的末尾元素，将当前得到的最长上升子序列长度加1，同时将 nums[i]添加到tail数组尾部
                end++;
                tail[end] = nums[i];
            }
            else
            {
                /*
                二分查找法寻找 tail 数组中第一个大于等于 nums[i] 的元素，替换他。
                这个元素必然存在，因为tail严格上升，而且tail[end]>=nums[i]。另外，为了方便，等于也替换
                 */
                //先定义查找区间（即tail中的有效区间）
                int left = 0;
                int right = end;
                while (left<right)
                {
                    int mid = left + (right - left)/2;
                    if(tail[mid] < nums[i]) {
                        //此时说明大于等于 nums[i] 的tail元素在mid右边，更新区间左部
                        left = mid+1;
                    }else{
                        //此时说明，大于等于 nums[i] 的tail元素可能在mid或者mid的左边，更新区间右部
                        right = mid;
                    }
                }
                //跳出循环的时候，left=right，此时一定找到第一个大于等于nums[i]的元素，就是tail[left/right]
                tail[left] = nums[i];//更新该长度的上升子序列末尾的元素，使其不变（找到等于nums[i]的元素）或者变小（找到第一个大于nums[i]的元素）
                //此时tail长度不变
            }
        }
        return ++end;//最后，真正的最长上升子序列的长度，就是tail数组中有效字段的长度，即end+1
    }
}
