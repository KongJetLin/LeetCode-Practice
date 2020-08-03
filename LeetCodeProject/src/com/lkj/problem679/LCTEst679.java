package com.lkj.problem679;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
对于4个数的加减乘除以及括号运算，首先，由于减法与除法不满足交换律，那么各个数的位置不同，结果也看不一样，
 因此，这4个数的排放方式有：4*3*2*1=24 种；另外，中间的3个符号位，可能有4*4*4=64种可能；
 另外加括号也有可能使得结果不同（先后运算结果可能不同），加括号有6种可能。
 那么最多一共有：24*64*6=9216种可能，那么我们考虑使用回溯法来解决。
 */
public class LCTEst679
{
    public boolean judgePoint24(int[] nums)
    {
        //1、由于我们的除法计算是实数除法而不是整数除法，因此需要将int类型的数组转换为double类型，这样才有小数参与运算
        List<Double> numbers = new ArrayList<>();
        for (int num : nums)
        {
            numbers.add((double) num);
        }
        //2、调用solve方法进行递归处理
        return solve(numbers);
    }

    private boolean solve(List<Double> numbers)
    {
        //1、当最后剩下一个数的时候，不需要再计算，看其是否等于24
        if(numbers.size() == 1)
        {
            //由于是小数运算，最后的数与24之间的差距小于 1e-6（1乘10的负6次方），就说明这个数等于24
            return Math.abs(numbers.get(0) - 24) < 1e-6;
        }

        //2、下面取数组的数进行计算，由于数取的顺序会影响结果，即取 numbers[i]和numbers[j]，和取得numbers[i]和numbers[j]
        // 的结果是不同的，因此我们2个for循环必须从0开始，这样才能取得所有可能的 i、j 的组合
        for (int i = 0; i < numbers.size() ; i++)
        {
            for (int j = 0; j < numbers.size() ; j++)
            {
                /** 不在原来的集合 numbers 上进行操作
                我们这里取出2个数进行计算，得到所有可能的计算结果的集合，然后把计算结果和剩下的数一起加入集合进行递归，进行下一轮的计算。
                 最后我们需要将取出的2个数又放回集合，继续取数进行计算，而且放回集合的位置必须与原来的位置相同，否则会错过一些情况。
                 既然这样，干脆我们就创建一个新的集合来存放剩下的数和计算结果，进行下一轮的递归，由于不在原来的集合numbers上进行操作，
                 这样便不需要考虑将数放回原来集合的复杂情况。
                 */
                //取到2个不同的数，进行计算
                if(i != j)
                {
                    //2.1 先创建一个新的数组，将剩下的数放入数组
                    List<Double> list = new ArrayList<>();
                    for (int k = 0; k < numbers.size() ; k++)
                    {
                        if(k!=i && k!=j)
                            list.add(numbers.get(k));//将剩下的数放入list
                    }

                    //2.2 得到计算结果的集合，用Set是为了避免出现重复的结果
                    Set<Double> calculate = calculate(numbers.get(i), numbers.get(j));

                    //2.3 将每一个计算结果放入list，进入下一轮的递归
                    for (Double num : calculate)
                    {
                        list.add(num);
                        //如果递归的结果是true，直接返回true
                        if(solve(list))
                            return true;
                        //如果递归的结果是false，需要将添加进来的计算结果清除，随后进行下一轮的循环，添加新的计算结果
                        list.remove(list.size()-1);
                    }
                }
            }
        }
        return false;//如果所有的递归子结构都返回false，那么这里也要返回false
    }

    private Set<Double> calculate(double a , double b)
    {
        Set<Double> set = new HashSet<>();
        //不满足交换律的减法与除法需要计算2种情况
        set.add(a - b);
        set.add(b - a);

        set.add(a + b);
        set.add(a * b);

        if(a != 0)
            set.add(b / a);
        if(b != 0)
            set.add(a / b);
        return set;
    }

}
