package com.lkj.problem76;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 参考：https://leetcode-cn.com/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
 * 滑动窗口法，注意，不仅仅要考虑t的字符，还需要考虑每一个字符的数量
 * 时间复杂度：
 * 空间复杂度：
 */
public class LCTest76
{
    //定义2个HashMap，need用于存储T的所有字符与数目，window用于存储窗口中存在与need中的字符与字符的数目
    // 注意，s中只有存在与need中的字符，才会记录到window
    HashMap<Character , Integer> need = new HashMap();
    HashMap<Character , Integer> window = new HashMap();

    public String minWindow(String s, String t)
    {
        if(s==null || s.length()==0 || t==null || t.length()==0 || t.length()>s.length())
            return "";

        //首先将 t 的字符与数目存储到need
        for (int i = 0; i < t.length() ; i++)
        {
            //若need原来有这个字符，则将原来的数目+1，否则取0+1
            need.put(t.charAt(i) , need.getOrDefault(t.charAt(i) , 0)+1);
        }

        //定义窗口左指针右指针
        int leftPoint = 0;
        int rightPoint = 0;
        //定义s中满足条件的最小字符串长度，以及该字符串的起始位置
        int minLength = Integer.MAX_VALUE;
        int begin = -1;//初始化为-1.表示暂时没有找到满足条件的字符串

        //外层while循环，用于在 s 字符串上移动右指针，找到满足条件的字符串
        while(rightPoint<s.length())
        {
            char ch = s.charAt(rightPoint);
            //查看当前字符串是否在 need 中，是的话，将 window中该字符的数量加1
            if(need.containsKey(ch))
            {
                window.put(ch , window.getOrDefault(ch , 0)+1);
            }

            /**
              二层循环，在 window 中存储的字符串满足要求后，计算当前字符串的长度，更新满足条件的最小字符串，
              随后移动左指针，直到 window 中的字符又不满足要求（这个过程同样记录满足条件的字符串长度并更新最小字符串），
              当 window 不满足条件的时候，跳出内层循环，继续移动右指针，直到满足条件
             */
            while (cheak() && leftPoint<=rightPoint)//注意，左指针最多移动到与右指针相等，不能超过右指针
            {
                int tempLength = rightPoint-leftPoint+1;//计算当前满足条件的字符串长度
                //当当前满足添加字符串长度小于minLength的时候，更新字符串最短长度minLength，字符串起始位置begin
                if(tempLength < minLength)
                {
                    minLength = tempLength;
                    begin = leftPoint;
                }

                //检查当前满足条件字符串的左指针的字符是不是包含在need中，是的话，我们需要将windows中该字符数量减一，同时右移左指针
                // 否则，仅仅右移左指针即可（当前字符不在need中，那么window中也不会记录）
                char leftPointCh = s.charAt(leftPoint);
                if(need.containsKey(leftPointCh))
                {
                    window.put(leftPointCh , window.get(leftPointCh)-1);
                }
                leftPoint++;
                //当 window 中的字符类型或者数量不满足need需求，或者 leftPoint>rightPoint 的时候，则跳出循环，移动右指针使得window满足need
            }

            //外循环在遍历下一个之前，将右指针+1
            rightPoint++;
        }
        //结束之前，若begin=-1，说明整个过程没有找到满足条件的字符串，直接返回""，否则，返回相应的字符串
        return begin==-1? "" : s.substring(begin , begin+minLength);
    }

    //遍历need，检查window中的字符类型与数目是否满足need
    private boolean cheak()
    {
        Set<Character> set = need.keySet();
        Iterator<Character> it = set.iterator();
        while (it.hasNext())
        {
            Character key = it.next();
            Integer value = need.get(key);//need中该字符的数量
            //如果window中该字符的数量仍然小于need中该字符的数量，说明还没有满足，返回false
            //注意，这里要用 getOrDefault，因为有可能 window 中不存在 key 这个键，这时就会出现空指针异常！
            if(window.getOrDefault(key , 0) < value)
                return false;
        }
        //全部字符检查通过，返回true
        return true;
    }
}
