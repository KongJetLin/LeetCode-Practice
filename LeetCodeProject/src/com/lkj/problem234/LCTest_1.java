package com.lkj.problem234;

import java.util.ArrayList;
import java.util.List;

/** 将值复制到数组中后用双指针法
时间复杂度：O(n)
 空间复杂度：O(n)
 */
public class LCTest_1
{
    public class ListNode
    {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public boolean isPalindrome(ListNode head)
    {
        List<Integer> list = new ArrayList<>();

        ListNode cur = head;
        while (cur != null)
        {
            //1、注意，我们比较的是ListNode的值，不是比较结点！要添加值！（不要犯低级错误！）
            list.add(cur.val);
            cur = cur.next;
        }

        int front = 0;
        int back = list.size() - 1;

        while (front < back)
        {
            //2、注意我们List中放的是Integer，要用equals比较大小，== 或者 != 只能比较地址！
            // （这种错误很容易犯！不要犯低级错误，比较之前想一想比较的是基本数据类型还是引用数据类型！）
            if(!list.get(front).equals(list.get(back)))
                return false;
            front++;
            back--;
        }
        return true;        
    }
}
