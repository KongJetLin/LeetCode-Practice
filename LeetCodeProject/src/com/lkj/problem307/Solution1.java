package com.lkj.problem307;

public class Solution1
{
    //我们同样使用 求和数组的方法，对比303题，不过这里多了个update，更新数组元素的方法

    private int[] sum;
    private int[] data;//定义一个数组存储区间的副本数组，方便数组区间数据的更新

    /** O(n) */
    public Solution1(int[] nums)
    {
        sum = new int[nums.length+1];
        sum[0] = 0;
        //初始化的时候是 O(n) 的操作
        for (int i = 1; i <= nums.length ; i++)
        {
            sum[i] = sum[i-1] + nums[i-1];
        }

        data = new int[nums.length];
        for (int i = 0; i < nums.length ; i++)
        {
            data[i] = nums[i];
        }
    }
    /** O(n) */
    public void update(int index, int val)
    {
        //更新区间数据的时候，更新的是副本数组 data的数据
        data[index] = val;
        //此时，我们也必须更新sum数组的数据，我们可以从区间更新位置index， 开始更新sum数组的数据，因为前面 sum数组的数据不变！
        //sum[index]存储data[0 - index-1]（或者说是 nums[0 - index-1]），这部分数据没变，从sum[index+1]开始更新即可
        for (int i = index; i < data.length; i++)
        {
            sum[i+1] = sum[i] + data[i];
        }
        /**  注意，虽然 sumRange 方法依然是O(1)，但是每一次更新都会花费O(n)的时间 */
    }

    /** O(1) */
    public int sumRange(int i, int j)
    {
        return sum[j+1] - sum[i];
    }
}
