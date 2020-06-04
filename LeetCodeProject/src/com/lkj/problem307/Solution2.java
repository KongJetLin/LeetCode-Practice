package com.lkj.problem307;

import com.lkj.problem307.SegmentTree;

public class Solution2
{
    private SegmentTree<Integer> segmentTree;

    /** O(n)：只初始化1次 */
    public Solution2(int[] nums)
    {
        //首先判断传入的数组合法
        if(nums != null && nums.length>0)
        {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length ; i++)
            {
                data[i] = nums[i];
            }
            /**  使用data数组构造线段树，线段树每个结点都代表data数组的一个区间（其实线段树结点存储的是区间按一定规则融合的值）。同时，定义线段树区间融合的方式  */
            segmentTree = new SegmentTree<Integer>(data , (o1 , o2) -> o1+o2 );//同样，定义线段树数组tree结点的值是 data区间结点值和
        }
    }

    /** O(logn) */
    public void update(int i, int val)
    {
        //同样判断线段树是否初始化成功
        if(segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");
        segmentTree.set(i , val);
    }

    /** O(logn) */
    public int sumRange(int i, int j)
    {
        //有可能nums数组不合法，初始化的时候没有初始化 SegmentTree，判断一下 SegmentTree是否合法
        if(segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");

        return segmentTree.query(i , j);//返回查询结果即可！结果就是区间和。每一次查询都是 O(n) 复杂度
    }
}
