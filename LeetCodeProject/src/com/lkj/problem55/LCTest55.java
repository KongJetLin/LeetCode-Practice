package com.lkj.problem55;

//对比45题，跳跃游戏2
public class LCTest55
{
    /** 思路：贪心算法，每次遍历一个结点，尝试更新最远可达的距离！
     首先，用过硬遍历 longst_jump 记录能跳到的最远的位置。
    从头到尾遍历每一个结点，并根据这个结点的值，不管更新 longest_jump 的值，若出现 longest_jump<i（i为数组下标），
     说明我们遍历到i时，前面结点能跳到的最远距离都到不了i，这时就应该返回false。
     若我们遍历到数组末尾，都是 longest_jump >= i，说明可以跳到数组末尾，返回true

     这种方法的思路是：只要我们能跳到某一个结点，那么这个结点左侧的结点我们都可以跳到！
     这样，只要 i<=longest_jump ，那么我们就可以持续遍历i并更新 longest_jump

     时间复杂度：O(n)
     空间复杂度：O(1)
     */
    public boolean canJump(int[] nums)
    {
        int longest_jump = 0;
        for (int i = 0; i < nums.length ; i++)
        {
            //当 最远可到达的点已经大于数组长度，且longest_jump还大于等于i的时候，已经可以跳到末尾，不需要再遍历
            if(longest_jump >= nums.length-1)
                break;

            //当最远可到达的点都比i小，而且i还没有到数组末尾，那么说明跳不到数组尾部
            if(longest_jump < i)
                return false;

            //当 longest_jump >= i 的时候，longest_jump左侧的结点都可以到达，即此时i可以继续遍历
            longest_jump = Math.max(longest_jump , i+nums[i]);//更新要在最后
        }
        //遍历完nums，都没有出现 i>longest_jump，此时i=nums.length，说明longest_jump>=nums.length，可以到达数组末尾
        return true;
    }
}
