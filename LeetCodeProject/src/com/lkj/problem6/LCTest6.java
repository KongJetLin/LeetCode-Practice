package com.lkj.problem6;

import java.util.ArrayList;
import java.util.List;

public class LCTest6
{
    /**
     * 方法1：从头到尾遍历整个字符串，并将相应的字符串添加到相应的行
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows)
    {
        //这一个判断是必须的，因为下面的代码没办法覆盖只有一行的情况，如 “AB，1”情况
        if(numRows == 1)
            return s;

        //每一个都用 StringBuild 表示
        List<StringBuilder> rowList = new ArrayList<>();
        //初始化rowList 中的每一个元素，注意rowList的数量，最大值是 numRows，当 numRows>s.length 的时候，最大值是 s.length，即最多的行数只能是字符串的长度
        for (int i = 0; i < Math.min(numRows , s.length()) ; i++)
            rowList.add(new StringBuilder());

        /** 注意，我们别把排列看做 Z 字形，而是看做 W 字形
                L     C    I
                 E  T  O  E
                  E     D
         我们从 行数 row=0 开始向下走，直到 row=numRows-1的时候，转弯，再向上走到 row=0 ，转弯，直到遍历完整个字符串。
         注意：我们必须先添加转弯处的元素，再进行转弯
         */
        //初始化向下为false，因为我们添加了第一个元素后，row=0，会进行转弯，此时false变为true（向下）
        boolean goingDown = false;
        int row = 0;
        for (int i = 0; i < s.length() ; i++)
        {
            rowList.get(row).append(s.charAt(i));
            if(row==0 || row==numRows-1)
                goingDown = !goingDown;//到转弯的地方，先将转弯处元素添加进行，随后转弯

            row += goingDown?1:-1;
        }

        //我们使用 StringBuilder 就可以组装 StringBuilder集合
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < rowList.size() ; i++)
        {
            ret.append(rowList.get(i));
        }
        return ret.toString();
    }

    //解法2：将每一行添加完之后，再添加其他行，参考：https://leetcode-cn.com/problems/zigzag-conversion/solution/6-z-zi-xing-bian-huan-c-c-by-bian-bian-xiong/
    /** 令 step=2*rowNums-2
     1）第0层和第numRows-1层的下标间距总是step 。
     2）中间层的下标间距总是step-2*行数，2*行数交替。
     3）下标不能超过len(s)-1
     */
    public String convert1(String s, int numRows)
    {
        if(numRows == 1 || numRows>=s.length())
            return s;//不需要变化

        //先定义必要参数
        int step = numRows*2-2;
        int index = 0;//用于保存元素的实际下标

        StringBuilder ret = new StringBuilder();
        //遍历每一行
        for (int i = 0; i < numRows ; i++)
        {
            index = i;//为当前行设置下标初始值（第一个元素的下标）
            //定义中间元素的2个间隔
            int firstAdd = step - 2*i;
            int secondAdd = 2*i;
            boolean convert = true;//中间元素间隔转换标志

            //当index<s.length 的时候，我们持续添加这一行的元素
            while(index < s.length())
            {
                //无论下标如何变化，都要将当前元素先添加进来
                ret.append(s.charAt(index));
                if(i == 0 || i == numRows-1)
                {
                    index += step;//情况（1）
                }
                else
                {//情况2，注意将间隔标志转换
                    index += convert?firstAdd:secondAdd;
                    convert = !convert;
                }
            }
        }
        return ret.toString();
    }
}
