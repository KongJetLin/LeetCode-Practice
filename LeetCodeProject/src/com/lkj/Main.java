package com.lkj;

import java.util.*;

public class Main {

    public int[] constructArr(int[] a)
    {
        int[] mul = new int[a.length];

        if(a==null || a.length==0)
            return mul;

        int left = 1;
        for (int i = 0; i < a.length ; i++)
        {
            mul[i] = left;
            left = left*a[i];
        }

        int right = 1;
        for (int i = a.length-1; i >= 0 ; i--)
        {
            mul[i] = mul[i]*right;
            right = right*a[i];
        }
        return mul;
    }
}

