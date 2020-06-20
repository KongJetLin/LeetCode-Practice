package com.lkj.problem47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 回溯算法+剪枝（对比46题）
 * 参考文章：https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/

 同样使用回溯算法，包含3部分：选择的路径、选择列表、结束条件

 注意：
 1）这里的剪枝是有2种情况的，具体参考下面的解析！

 复杂度：遍历所有的数+添加到res，时间复杂度是 O(n*n!)
 */
public class LCTest47
{
    static List<List<Integer>> res;
    static int len;

    public static List<List<Integer>> permuteUnique(int[] nums)
    {
        if(nums==null || nums.length==0)
            return res;
        //初始化一些参数
        res = new ArrayList<>();
        len = nums.length;
        boolean[] used = new boolean[len];

        //1、用于保存路径
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);//将数组进行排序，这时剪枝的前提

        backtrack(nums , path , used);

        return res;
    }

    private static void backtrack(int[] nums , List<Integer> path , boolean[] used)
    {
        //2、结束条件：path.size() == nums.length，此时路径中已经添加了nums长度的数，已经是一条完整的路径，可以存入res
        if(path.size() == len)
        {
            res.add(new ArrayList<>(path));//这里参考46解析
            return;
        }

        /**
        3、选择列表：由于可能出现重复的元素，我们不能像46题一样，单单判断某个数是否在path就就将其在选择列表中剔除。
         我们使用一个 used 数组，记录那一个数被使用过。

         剪枝注意点：
         由于有相同的元素出现，我们需要比较nums数组前后2个数是否相同，这里有2种情况：
         1）在同一级递归中，如果当前数 nums[i] 与前一个被选中的数 nums[i-1] 相同，那么这个数就是重复的选项，不可再使用。
            而且由于 nums[i] 选中后被撤销，那么对于同一级来说，此时 used[i-1] 肯定为false；

         2）在不同级的递归中，以数组 [1,1',1'',2]为例，第一层选中的是 nums[0]=1，此时 used[0]=true，进入下一层的递归。
            对于第二层递归选择第一个数，nums[1]=1' ，虽然等于 nums[0]=1=nums[1]，但是这个 1' 在这一层是可以使用的，此时 used[i-1]=used[0]=true;

            对于第二层递归选择第二个数的时候，nums[2]=1''=nums[1]，此时也是（1）中同一级的选择，nums[2]不可用，
            此时 used[i-1] = used[1] = false（nums[1]使用后被撤销）。

         终上所述，为了可以在同一级中不重复选择相同元素，设置 nums[i]=nums[i-1] 就 continue 忽略这一个数；
         为了使得在不同级选择第一个元素若与上一个元素相同的时候不被忽略，在 nums[i]=nums[i-1] 的同时，需要加 used[i-1]=false。
         另外，如果，我们应该先将使用的数剔除后，再来考虑这一个数是否与前一个数相同
         */
        for (int i = 0; i < len ; i++)
        {
            //首先，如果当前数被使用，则从选择列表中剔除
            if(used[i])
                continue;
            //其次，比较当前数与前一个数是否相同（这里i大于0是为了使得i-1合法）
            if(i>0 && nums[i]==nums[i-1] && !used[i-1])
                continue;

            /**选择过程*/
            //选择并将这个数设置为使用
            path.add(nums[i]);
            used[i] = true;

            //进入递归
            backtrack(nums , path , used);

            //撤销选择
            path.remove(path.size()-1);//将这一轮添加到末尾的数删除
            used[i] = false;//撤销使用
        }
    }

//    public static void main(String[] args)
//    {
//        int[] nums = {1,1,2};
//        List<List<Integer>> lists = permuteUnique(nums);
//        for (List<Integer> list : lists)
//        {
//            for (Integer s : list)
//            {
//                System.out.print(s);
//            }
//            System.out.println();
//        }
//    }
}
