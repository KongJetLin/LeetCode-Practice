package com.lkj;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<String> test = new HashSet<>();
        test.add("java01");
        test.add("java03");
        test.add("java02");
        Iterator<String> iterator = test.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
}

