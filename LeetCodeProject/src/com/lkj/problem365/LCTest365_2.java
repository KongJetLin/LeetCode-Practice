package com.lkj.problem365;

import java.util.*;

/**
BFS 广度优先遍历
 */
public class LCTest365_2
{
    public boolean canMeasureWater(int x, int y, int z)
    {
        //1、我们用一个长度为2的数组保存 X、Y 此时的水量，用一个Stack保存X、Y所有可能出现的状态组合（DFS用stack）
//        Stack<int[]> stack = new Stack();
        Queue<int[]> queue = new ArrayDeque<>();

        //2、用一个Set保存所有已经处理过的状态组合，由于Set不能出场数组，使用 Map.Entry<Integer,Integer>替代
        Set<Map.Entry<Integer , Integer>> set = new HashSet<>();

        //3、首先，将初始X、Y均为0的情况，添加到stack中
        queue.add(new int[]{0 , 0});

        //4、开始遍历，当 X、Y所有可能的状态组合遍历完，就结束遍历，说明没有找到，return false
        while (!queue.isEmpty())
        {
            //4.1 先取出一个状态，判断这个状态是否满足
            int[] arr = queue.remove();
            if(arr[0]==z || arr[1]==z || arr[0]+arr[1]==z)
                return true;//如果该状态下X、Y的水量能找出Z，直接返回true

            /*
            4.2 否则，将该状态构造为 Map.Entry<Integer , Integer>，判断Set集合中是否已经存储该状态，
            1）如果已经存储该状态，我们就跳过这个状态的处理，那么该状态下面的6步操作所得到的新的状态也不会添加到stack，
                这一条线的DFS结束。同时，跳过这个状态，也表示上一层的遍历中，产生该状态的操作也被取消，接下来处理上一层
                遍历其他操作所导致的状态。
            2）如果该状态没有被存储，那么将该状态添加到Set，同时，根据该状态进行6步操作，产生新的6个状态，添加到stack，
                在下面的循环中，会处理这新的6个状态。
             */
            Map.Entry<Integer , Integer> currentEntry = new AbstractMap.SimpleEntry<>(arr[0] , arr[1]);
            if(set.contains(currentEntry))
                continue;
            set.add(currentEntry);

            //进行6个操作，并将新的状态添加到stack
            queue.add(new int[]{0 , arr[1]});// 倒空x
            queue.add(new int[]{arr[0] , 0});// 倒空y

            queue.add(new int[]{x , arr[1]});// 加满x
            queue.add(new int[]{arr[0] , y});// 加满y

            // x往y中倒水
            if((arr[0]+arr[1]) > y)
                queue.add(new int[]{arr[0]-(y-arr[1]) , y});// 如果会倒满，x中会有剩余，y满
            else
                queue.add(new int[]{0 , arr[0]+arr[1]});// 如果不会倒满，x空，y是当前两桶水之和

            // y往x中倒水
            if((arr[0]+arr[1]) > x)
                queue.add(new int[]{x , arr[1]-(x-arr[0])});// 如果会倒满，y中会有剩余，x满
            else
                queue.add(new int[]{arr[0]+arr[1] , 0});// 如果不会倒满，y空，x是当前两桶水之和
        }
        return false;
    }
}
