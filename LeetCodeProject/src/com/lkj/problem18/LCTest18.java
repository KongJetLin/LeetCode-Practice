package com.lkj.problem18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
参考 15题《三数之和》解法，是一样的，这里时间复杂度是：O(n^3)
 */
public class LCTest18
{
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if(nums == null || nums.length<4)
            return ret;
        Arrays.sort(nums);

        for (int first = 0; first < nums.length-3 ; first++)
        {
            if(first > 0 && nums[first] == nums[first-1])
                continue;

            for (int second = first+1; second < nums.length - 2; second++)
            {
                if(second>first+1 && nums[second] == nums[second-1])
                    continue;

                int target1 = target-nums[first]-nums[second];
                int fourth = nums.length-1;
                for (int third = second+1; third < nums.length-1 ; third++)
                {
                    if(third>second+1 && nums[third] == nums[third-1])
                        continue;

                    while(third < fourth && (nums[third]+nums[fourth])>target1)
                        fourth--;

                    if(third == fourth)
                        break;
                    if(nums[third]+nums[fourth] == target1)
                    {
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(nums[first]);
                        arrayList.add(nums[second]);
                        arrayList.add(nums[third]);
                        arrayList.add(nums[fourth]);
                        ret.add(arrayList);
                    }
                }
            }
        }
        return ret;
    }
}
