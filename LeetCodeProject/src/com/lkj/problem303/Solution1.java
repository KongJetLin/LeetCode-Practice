package com.lkj.problem303;

import com.lkj.problem303.SegmentTree;

public class Solution1
{
    /**303题解答*/

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(i,j);
     */

    //先创建一个SegmentTree引用
    private SegmentTree<Integer> segmentTree;

    //构造方法，我们可以在创建这个类的对象的时候，初始化一个线段树对象，用于下面区间的统计
    public Solution1(int[] nums)
    {
        //首先判断传入的数组合法
        if(nums != null && nums.length>0)
        {
            //由于 int类型的数组不能直接转变成为Integer类型的数组，我们必须创建一个新的Integer的数组，将int数组的值赋予它
            //int类型的变量会可以直接转变成为Integer类型的变量
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length ; i++)
            {
                data[i] = nums[i];
            }
            /**  使用data数组构造线段树，线段树每个结点都代表data数组的一个区间。同时，定义线段树区间融合的方式  */
            segmentTree = new SegmentTree<Integer>(data , (o1 , o2) -> o1+o2 );//同样，定义线段树数组tree结点的值是 data区间结点值和
        }
    }

    public int sumRange(int i, int j)
    {
        //有可能nums数组不合法，初始化的时候没有初始化 SegmentTree，判断一下 SegmentTree是否合法
        if(segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");

        return segmentTree.query(i , j);//返回查询结果即可！结果就是区间和。每一次查询都是 O(n) 复杂度
    }
}
