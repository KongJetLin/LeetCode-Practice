package com.lkj.problem169;

import java.util.HashMap;

public class LCTest169
{
    public int majorityElement(int[] nums)
    {
        int target = nums.length/2;
        int ret = nums[0];//将 ret设置为nums[0]，可以避免nums.length=1时的特判，直接返回ret=nums[0]即可
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length ; i++)
        {
            if(map.containsKey(nums[i]))
            {
                map.put(nums[i], map.get(nums[i]) + 1);
                if(map.get(nums[i]) > target)
                {
                    ret = nums[i];
                    break;
                }
            }
            else
            {
                map.put(nums[i] , 1);
            }
        }
        return ret;
    }
}
