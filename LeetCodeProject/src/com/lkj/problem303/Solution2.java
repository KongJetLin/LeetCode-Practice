package com.lkj.problem303;

public class Solution2
{
    /**
     * sum[i]存储前i个元素和, sum[0] = 0，即sum[i]存储nums[0...i-1]的和，
     * 那么 sum[nums.length] 存储前 nums.length 个元素的和，因此，在定义sum数组的时候，它的长度是： nums.length+1
     * sum(i, j) = sum[j + 1] - sum[i]
     */
    private int[] sum;

    public Solution2(int[] nums)
    {
        sum = new int[nums.length+1];
        sum[0] = 0;
        //初始化的时候是 O(n) 的操作
        for (int i = 1; i <= nums.length ; i++)
        {
            sum[i] = sum[i-1] + nums[i-1];
        }
    }

    public int sumRange(int i, int j)
    {
        //sum[i]存储 nums[0] - nums[i-1]区间和，sum[j+1] 存储 nums[0] - nums[j]区间和，相减就是 nums[i] - nums[j] 区间和
        return sum[j+1] - sum[i]; //每次查询区间复杂度都是O(1)
    }
}
