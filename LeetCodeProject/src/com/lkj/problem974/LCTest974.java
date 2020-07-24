package com.lkj.problem974;

import java.util.HashMap;

/**
方法1：纯暴力解法，时间复杂度 O(n^3)，空间复杂度 O(1);
 方法2：前缀和+暴力法，时间复杂度 O(n^2)，空间复杂度 O(n)

 ！通常，涉及连续子数组问题的时候，我们使用前缀和来解决。
 方法3：我们令 P[i] = A[0] + A[1] + ... + A[i]。那么每个连续子数组的和 sum(i,j) 就可以写成 P[j] - P[i-1]（注意，0<i<=j），
 此时，判断子数组的和能否被 K 整除就等价于判断 (P[j] - P[i-1]) mod K ==0，
 根据 同余定理，只要 P[j] mod K == P[i-1] mod K，就可以保证上面的等式成立。

 那么我们使用散列表存储： P[j] mod K 为键，它前面的 P[i] mod K 出现的次数为值。
 （对于一个 P[j] mod K 的值，若它前面有 x 个 p[i] 满足 P[i] mod K = P[j] mod K，那么以 A[j] 元素为结尾的满足条件的子数组个数为 x 个）
注意：
 1）开始必须设置 map(0 , 1)，这是为了处理 P[j] mod K = 0 的情况，如果 P[j] mod K = 0，那么 [0,j]这个数组段就满足条件（可以被K整除），
    也就是说，即使此时 map 中 P[j] mod K 键处值为 0，那么此处总的 ans也应该+1.

    我们看下面的代码，对于 P[j] mod K ！= 0 的情况，对于当前的 P[j] mod K，需要计算前面与 P[j] mod K 相等的个数，
    才能组成 P[i] mod K = P[j] mod K 对，这才算一个满足条件的子数组。
    但是对于 P[j] mod K = 0，它本身就满足可以被K整除的条件，当第一个 P[j] mod K = 0 时，ans 就应该+1，因此最初设置 map.put(0,1)，
    如果不 map.put(0,1)，此时 same=0，便不会加1. 此时same=1，ans+1，随后我们设置 键 0处的值+1=2.
    第二个出现 的 P[j] mod K = 0，此时 map.get(0)=2=same，那么 ans+2,并且设置 (0,3)....
 其实，将 map.put(0,1)，理解为当 P[j] mod K = 0 的时候，我们在最前面加了一个虚拟的 P[dummy] mod K = 0，
 那么后面每一个真实存在的 P[j] mod K = 0，就会与这个虚拟的 P[dummy] mod K = 0 组成一对！这样设置是正确的！

 总结：对于 P[j] mod K = 0 的键，初始值设置为1；对于 P[j] mod K ！= 0 的键，初始值设置为0；

2）对于java来说，负数取余正数，得到的余数结果是负数，必须将该余数结果转换为正数。
 如 -10%7 = -3，4%7 = 4，但是 4-(-10) = 14,14%7 = 0，满足，因此，应该将 -10%7 的负余数修改为正数。
公式： ( 负数 % 正数 + 正数) % 正数 = ( 负数 % 正数 )的正余数

 */
public class LCTest974
{
    public int subarraysDivByK(int[] A, int K)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0 , 1);
        /**
        我们可以先求出（前缀和%K）数组，然后比较某个 （前缀和%K） 的键在散列表中有多少个值，
         但是需要注意，P[i] mod K = P[j] mod K，前提条件是 i 在 j 前面，如果我们统计后再判断，
         无法识别哪一个 （前缀和%K）在前，这样很麻烦。
         因此，我们干脆从头到尾遍历数组，直接求每一个元素的 （前缀和%K），然后查看此时散列表中
         与其值相同的 （前缀和%K）个数， 这些 （前缀和%K）就是都在当前元素前面的 （前缀和%K）！
         */
        int ans = 0;//用于统计满足条件的子数组个数
        int sum = 0;

        for (int element : A)
        {
            sum += element;//计算当前元素的前缀和
            int modules = (sum%K + K)%K;//计算 P[i] mod K 的值，注意转换为正余数
            /*
            取出之前散列表中存储的相同的 P[i] mod K 的个数，如果前面没有 P[i] mod K 这个值，默认为0，
            那么此时满足条件 P[i] mod K = P[j] mod K 的对数为 map.getOrDefault(modules , 0)，将其加到ans
             */
            int same = map.getOrDefault(modules , 0);
            ans += same;
            //获取到前面相同的 P[i] mod K 个数并加到 ans后，再设置新的 P[i] mod K 键出的个数+1
            map.put(modules , same+1);
        }
        return ans;
    }
}
