package com.lkj.Problem20;

import java.util.Stack;

//有效的括号
/**
题目描述：见原题
 */

/**
思路： 我们使用栈来完成这个功能。
1）对于这个只包含括号的字符串，首先我们利用一个循环，从头开始读取每个字符，如果这个字符是“(”、“[”、“{” 这类左括号，我们将这个字符推入栈；

2）当读取到的字符不是左括号而是右括号的时候，我们就先判断栈是否为空，如果栈为空，而这里有一个右括号，说明字符串无效，返回false；
    （ 这种情况是前面左括号不够而右括号过多，如：()} ）
3）如果栈不为null，我们将栈顶的元素出栈，判断这个右括号与栈顶的左括号是否是同一个类型。如果不是同一个类型，说明这个字符串无效，返回false；
    （ 这种情况是左括号与右括号不匹配，如：([)]**    ），如果2个括号是同一个类型的，继续读取下一个字符， 注意，取出元素的时候栈顶的元素已经出栈。
4）当字符串的字符读取完毕后，我们判断栈里面还有没有元素，既判断栈是否为null，如果还有元素，字符串无效，返回false。（**这种情况是前面左括号过多而右括号不够，
    如：({} ）如果没有元素，说明字符串有效，返回true。

* 注意，需要排除**左括号与右括号不匹配，左括号过多，右括号过多**3种错误情况。
* 我们是一直在排除错误的情况，如果循环后没有返回false，说明字符串有效，所有错误情况被排除，返回true

 */
public class LCTest20
{
    public boolean isValid(String s) {
        //先创建一个栈，注意这个栈存储的是char类型的字符数据
        Stack<Character> stack = new Stack<Character>();
        //遍历字符串
        for (int i = 0; i < s.length() ; i++)
        {
            char ch = s.charAt(i);
            //注意，这里是基本数据类型char的比较，使用“==”
            if(ch == '(' || ch == '{' || ch == '[')
            {
                //读取到左括号，将左括号存储到栈中
                stack.push(ch);
            }else{
                //如果读取到的是右括号，首先判断栈是否为空，为空说明字符串无效（右括号无左括号对应，第一种错误情形）
                if(stack.isEmpty())
                {
                    return false;
                }
                //如果栈不为null，取出栈顶的元素
                char topChar = stack.pop();//这里返回的是Character，为了可以使用“==”比较值，我们将其拆箱为char类型（会自动拆箱）
                //首先排除3种不匹配的情况（左括号与右括号不匹配，第二种错误情形）
                if(ch == ')' && topChar != '(')
                    return false;
                if(ch == ']' && topChar != '[')
                    return false;
                if(ch == '}' && topChar != '{')
                    return false;
                //如果栈不为null，且字符匹配的话，继续循环读取下一个字符（前面栈顶元素取出的时候，栈顶元素已经出栈）
            }
        }
        //当所有的字符读取完之后，还有判断栈中好有没有元素，如果有，说明左括号过多，这是第三种错误情况
        return stack.isEmpty();//stack为空返回true，说明字符串正确，否则字符串错误
    }

    /**
     * 注意：
     * 1）比较基本类型只能用"=="，不能用"equals"，这里的"=="比较的是两个基本类型的值，基本数据类型没有equals()方法；
     * 2）对于包装类，引用数据类型如String等，“==”比较的是内存地址，而equals()比较的是值。
     */
    public static void main(String[] args)
    {
        System.out.println((new LCTest20()).isValid("()[]{}"));//true
        System.out.println((new LCTest20()).isValid("([)]"));//false
    }
}
