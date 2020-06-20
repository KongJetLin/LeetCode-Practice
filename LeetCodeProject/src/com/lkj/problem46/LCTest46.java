package com.lkj.problem46;

import java.util.ArrayList;
import java.util.List;

/** 对比47题
根据文章：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-xiang-jie-by-labuladong-2/
 的分析，回溯算法主要有3要素：选择过的路径、当前可以选择的列表、结束的条件

回溯算法具体操作的框架如下：
 result = []
 def backtrack(路径, 选择列表):
 if 满足结束条件:
    result.add(路径)
    return

 for 选择 in 选择列表:
     做选择
     backtrack(路径, 选择列表)
     撤销选择

 这种方法的时间复杂度是 O(n*n!)，因为我们需要取得所有的全排列，那么就有遍历每一个可能出现的数，
 第一个位置可能有 n种情况，第一个位置固定后，第二个位置有(n-1)种情况.....最后一个位置有1种情况，
 那么最后有n*(n-1)*...*1=n!种情况。另外，我们将结果添加到ArrayList中的时候，会消耗O(n)的时间，
 即时间复杂度是 O(n*n!)
 */
public class LCTest46
{
    List<List<Integer>> res = new ArrayList<>();//用于保存结果

    public  List<List<Integer>> permute(int[] nums)
    {
        //1、用于保存选择过的路径
        List<Integer> path = new ArrayList<>();
        backtracK(nums , path);
        return res;
    }


    private  void backtracK(int[] nums , List<Integer> path)
    {
        //2、结束条件，当选择过的路径track的长度与nums长度相同，已经完成一个全排列，结束
        if(path.size() == nums.length)
        {
            /**
             * res.add(path)
              注意，这里我们添加的都是同一个 path，也就是说即使我们添加多个ArrayList到res，这些ArrayList都是指向path所在的地址，
             而不管怎么样，循环结束后path都会被清空，因此结果是：[[], [], [], [], [], []]
             我们在获取到一条完整路径的时候，就将创建一个新的ArrayList，将path的内容复制到新的ArrayList中，这样就不会有问题，
             因为所有的路径都是指向不同的ArrayList！
             */
            res.add(new ArrayList<>(path));//将这个全排列添加到res
            return;//结束递归
        }

        //使用for循环遍历所有可能的选择
        for (int i = 0; i < nums.length ; i++)
        {
            //如果循环中track包含这个选择，说明这个数被选择过，已经在选择过的路径中，不应该出现在选择列表，将其略过
            if(path.contains(nums[i]))
                continue;//3、利用for循环以及剔除的方式，找到当前的选择列表

            //做选择，将当前选择列表的一个数添加到选择路径
            path.add(nums[i]);
            //递归进入下一个数的选择
            backtracK(nums , path);
            //撤销当前选择，以使得这一个递归中，可以选择当前选择列表的其他数
            //递归会从最底层开始异步一步撤销添加到路径的数，到这里我们只需要撤销这一层选择的数即可，因为后面选择的数在递归中也撤销了！
            path.remove(path.size()-1);
            /*
            这里注意，ArrayList添加的元素是有顺序的，ArrayList是数组实现，添加元素的时候，会在前面元素的后面添加。
            比如添加了1、3、2,ArrayList中元素的顺序是 1、3、2.再添加一个0，也会排在2后面：1、3、2、0.
            因此我们想把这一轮前面选择的数字删除，需要删除数组中的最后一个数字，即 path.size()-1 位置的数字。
             */
        }
    }
}
