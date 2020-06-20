package com.lkj;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(3);
        test.add(2);
        for (int i = 0; i < test.size(); i++)
        {
            System.out.println(test.get(i));
        }
    }
}

