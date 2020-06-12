package com.lkj.problem15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 双指针+二层循环
1）如果单单使用三层循环，当遇到最坏情况：[0, 0,..., 0]，如果我们直接使用三重循环枚举三元组，会得到 O(n^3) 个满足题目要求的三元组，
 时间复杂度至少为 O(n^3) ，而且题目要求三元组不重复，最后还需要使用哈希表进行去重操作，又消耗大量空间；

2）「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证：
    i）第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
    ii）第三重循环枚举到的元素不小于当前第二重循环枚举到的元素；
 也就是说，我们枚举的三元组 (a, b, c)满足 a≤b≤c，保证了只有 (a, b, c)这个顺序会被枚举到，而 (b, a, c)、(c, b, a) 等等这些不会，
 这样就减少了重复。要实现这一点，我们可以将数组中的元素从小到大进行排序，随后使用普通的三重循环就可以满足上面的要求。

 同时，对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。因此，如果遇到相同的元素，则需要跳到下一个不同的元素。
 这种方法的时间复杂度是 O(n^3)。排序是 O(nlogn)。

 3）使用双指针
 如果我们固定了前两重循环枚举到的元素 a 和 b，那么只有唯一的 c 满足 a+b+c=0。
 当第二重循环往后枚举一个元素 b'，对于 a+b'+c'=0，由于排序后 b'>b，那么满足条件的组 c'<c。
 也就是说，我们可以从小到大枚举 b，同时从大到小枚举 c，即第二重循环和第三重循环实际上是并列的关系。

 内部2层循环的时间复杂度从O(n^2)降低到O(n).因为移动的过程中，二层循环左指针向右移动，右指针向左移动，
 当他们移动到 2个指针的下标相同的时候，不再移动，因为再移动就会出现重复的三元组，这样均摊下来，内部2层循环的复杂度为O(n)。

 总结：这个方法就是我们常说的「双指针」，当我们需要枚举数组中的两个元素时，
 如果我们发现随着第一个元素的递增，第二个元素是递减的，那么就可以使用双指针的方法
 */
public class LCTest15
{
    public List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> res = new ArrayList<>();
        //将nums排序
        Arrays.sort(nums);

        //第一次循环，找到三元组第一个数
        for (int first = 0; first < nums.length ; first++)
        {
            /** 确定第一个数 */
            //将重复的数跳过。注意，第一个数不需要跳过，first>0。
            //不加first>0，非遗馆first=0的时候，first-1<0，数组角标越界
            if(first>0 && nums[first] == nums[first-1])
                continue;//直接跳过这次循环，查找下一个first，也可以使用下面的方法
            /**
            while (first>0 && nums[first] == nums[first-1])//跳过的数可能有多个，用while
                first += 1;
            */

            /** 确定第二三个数 */
            //先将二三个数的和的负数求出
            int target = -nums[first];//即第二三个数的和加第一个数为0，那么二三数和为-nums[first]
            int third = nums.length - 1;
            //第三个数从末尾开始枚举（third放在二层循环外面，避免每次对于新的second，third都重新从最右边开始，其实third在上一次的位置开始即可）

            //这里second必须大于first，因为first前面的情况考虑了；而且不能等于，因为不能使用同一个位置的数
            for (int second = first+1; second < nums.length ; second++)
            {
                //同样，跳过重复的second（第一位除外）
                if(second > first+1 && nums[second] == nums[second-1])
                    continue;

                /*
                当 second<third（说明还可以查找），且 nums[second]+nums[third]>target（说明third还可以向左移动变小），
                持续向左移动third
                 */
                while(second<third && (nums[second]+nums[third]>target))
                    third--;

                /** 出循环的时候，有3种情况
                1）second=third，直接break循环。数组是这样的 [first , 小second  , 大second ,.. second=third .., 小third , 大third]
                 每次的组合都是： 小second+大third=target，大second+小third=target；
                 当second=third，因为再循环下去，就third就会变成原来second的值，而second就会变成原来third的值，这样重复了，因此没必要继续。

                2）nums[second]+nums[third]=target ，满足，添加到结果

                3）nums[second]+nums[third]<target，对于这个second，找不到满足的third，那么继续循环查找下一个second
                 */
                if(second == third)
                    break;
                if(nums[second]+nums[third] == target)
                {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    res.add(list);
                }
            }
        }
        return res;
    }
}
