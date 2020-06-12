package com.lkj.problem1;

import java.util.HashMap;

/** 哈希表
这题可以用2个循环来做，使用2个循环的时间复杂度是O(n^2)，因为内部循环要来查找另一半是否存在，复杂度是O(n)。

 我们需要一种更有效的方法来检查数组中是否存在目标元素。如果存在，我们需要找出它的索引。保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
 通过以空间换取速度的方式，我们可以将查找时间从 O(n) 降低到 O(1)。
 哈希表在不冲突的情况下，查找某个元素的用时是O(1)。
 一旦出现冲突，查找用时可能会退化到 O(n)。但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)。

 这一题的关键是：
 1）利用哈希表查询的时间复杂度为O(1)的特性，查询另一半的数是否存在！！ 当然，消耗的空间是O(n)
 2）HashMap存储的时候，若后面的键相同，会将前面键的值覆盖。但是这并不影响正确性，因为数组先找到前面的键...具体分析如下：
 */
public class LCTest1
{
    public int[] twoSum(int[] nums, int target)
    {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        //将nums的数以及数在数组中的下标存储到哈希表中
        for (int i = 0; i < nums.length ; i++)
        {
            hashMap.put(nums[i] , i);
        }

        //用另一个循环检查某个数的另一半是否在哈希表中存在，哈希表查找某个数是否存在的时间复杂度是O(1)
        for (int i = 0; i < nums.length ; i++)
        {
            int anotherHalf = target-nums[i];//另一半的数
            /*
            当数组中包含另一半 anotherHalf ，且当另一半 anotherHalf = nums[i]的时候，anotherHalf 的下标不能是 i（数组中的同一个元素不能被使用2次），
            即数组中可能包含多个 anotherHalf 的值，但是我们不能使用同一个 anotherHalf，而应该使用下标不同的 anotherHalf。

            解释为什么这种方法可以。假设我们输入的数组的 [3,3]，HashMap在存储的时候，第一个存储（3,0），第二次又遇到3,3为键，
            因此会覆盖前面的键，存储(3,1)。
            这样我们在这里遍历数组，找到第一个数 i=0,nums[i]=3.如果输入的 target=6，另一半就是3，hashMap.get(3)=1，
            那么最后就可以输出 （0,1），这样是不会冲突的！！即数组查找前面的重复数，而HashMap存储的是后面重复数的下标！
             */
            if(hashMap.containsKey(anotherHalf) && hashMap.get(anotherHalf)!= i)
            {
                return new int[]{i , hashMap.get(anotherHalf)};//将2个数的下标存储到int数组
            }
        }

        //没有找到就抛出一个非法参数异常
        throw new IllegalArgumentException("No two sum solution!");
    }
}
