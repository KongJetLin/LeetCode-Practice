package com.lkj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test
{
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ret= new ArrayList();
            if(nums == null || nums.length<3)
                return ret;
            Arrays.sort(nums);

            for (int first = 0; first < nums.length-2 ; first++)
            {
                if(first>0 && nums[first]==nums[first-1])
                    continue;
                int target = -nums[first];
                int third = nums.length-1;
                for (int second = first+1; second < nums.length - 1; second++)
                {
                    if(second>first+1 && nums[second]==nums[second-1])
                        continue;

                    while (second<third && nums[second]+nums[third]>target)
                        third--;
                    if(second==third)
                        break;
                    List<Integer> temp = new ArrayList();
                    if(nums[second]+nums[third]==target)
                    {
                        temp.add(nums[first]);
                        temp.add(nums[second]);
                        temp.add(nums[third]);
                        ret.add(temp);
                    }
                }
            }
            return ret;
        }
}
