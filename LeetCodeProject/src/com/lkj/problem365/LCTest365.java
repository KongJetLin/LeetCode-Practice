package com.lkj.problem365;

import java.util.*;

/** DFS 深度优先遍历
 观察题目可知，在任意一个时刻，此问题的状态可以由两个数字决定：X 壶中的水量，以及 Y 壶中的水量。
 当 X 壶中的水量=Z，或者 Y 壶中的水量=Z，或者 X+Y=Z 的时候，我们就获取到需要的Z量的水。

 在任意一个时刻，我们可以且仅可以采取以下几种操作：
     1）把 X 壶的水灌进 Y 壶，直至灌满或倒空；
     2）把 Y 壶的水灌进 X 壶，直至灌满或倒空；
     3）把 X 壶灌满；
     4）把 Y 壶灌满；
     5）把 X 壶倒空；
     6）把 Y 壶倒空。
那么本题可以使用深度优先搜索来解决，我们从 X、Y壶中的水量为0开始，选择以上6步执行，先选择（1），然后（1）又可以分化出相同的6步，处理这6步，
这6步又会分化出相同的6步。但是这样有一个问题，就是会无限循环下去
 我们知道，2个水壶中水的状态数是有限的，我们在每一层都考虑所有的6种可能，最后一定会遍历完所有X、Y可能的状态组合。
 那么我们每找到一种新的X、Y的状态组合，就将其添加到Set中，下次我们经过某次操作，如果发现这次操作完后的状态之前出现过，
 说明前面出现的该 X、Y 的状态组合经过若干层的处理后，并没有找到相应的Z（如果找到已经返回退出），此时又回到这个状态。
 那么我们就不执行这次操作，使得这个重复出现的状态组合不出现，那么也就不会再添加新的6个状态，即这条深度优先遍历的线结束。

 经过上面的处理，我们会找到所有 X、Y的可能状态，同时，如果我们一直遍历，由于X、Y状态组合有限，无限遍历必然会遍历到重复的X、Y状态组合，
 这时我们会停止使得X、Y状态组合重复的操作，这时不添加新的6步操作，那么这一条线的深度优先遍历结束，不会继续遍历下去。
 因此，最后所有的深度优先遍历的线，都会因为出现重复的X、Y状态组合重复而停止！

 如果某天你写了一个BFS版本的搜索，如何最快的速度再写一份DFS版本发的呢？只需要把queue改为stack就可以了~

 时间复杂度：O(xy)，状态数最多有 (x+1)(y+1) 种（每个壶中水的数量只能是整数，0-x共(x+1)种），
 对每一种状态进行深度优先搜索的时间复杂度为 O(1)，因此总时间复杂度为 O(xy)。

 空间复杂度：O(xy)，由于状态数最多有 (x+1)(y+1)种，哈希集合中最多会有 (x+1)(y+1) 项，因此空间复杂度为 O(xy)。

  */
public class LCTest365
{
    public boolean canMeasureWater(int x, int y, int z)
    {
        //特判
        if (z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }

        //1、我们用一个长度为2的数组保存 X、Y 此时的水量，用一个Stack保存X、Y所有可能出现的状态组合（DFS用stack）
        Stack<int[]> stack = new Stack();
        //2、用一个Set保存所有已经处理过的状态组合，由于Set不能出场数组，使用 Map.Entry<Integer,Integer>替代
        Set<Map.Entry<Integer , Integer>> set = new HashSet<>();

        //3、首先，将初始X、Y均为0的情况，添加到stack中
        stack.push(new int[]{0 , 0});

        //4、开始遍历，当 X、Y所有可能的状态组合遍历完，就结束遍历，说明没有找到，return false
        while (!stack.isEmpty())
        {
            //4.1 先取出一个状态，判断这个状态是否满足
            int[] arr = stack.pop();
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
            stack.push(new int[]{0 , arr[1]});// 倒空x
            stack.push(new int[]{arr[0] , 0});// 倒空y

            stack.push(new int[]{x , arr[1]});// 加满x
            stack.push(new int[]{arr[0] , y});// 加满y

            // x往y中倒水
            if((arr[0]+arr[1]) > y)
                stack.add(new int[]{arr[0]-(y-arr[1]) , y});// 如果会倒满，x中会有剩余，y满
            else
                stack.add(new int[]{0 , arr[0]+arr[1]});// 如果不会倒满，x空，y是当前两桶水之和

            // y往x中倒水
            if((arr[0]+arr[1]) > x)
                stack.add(new int[]{x , arr[1]-(x-arr[0])});// 如果会倒满，y中会有剩余，x满
            else
                stack.add(new int[]{arr[0]+arr[1] , 0});// 如果不会倒满，y空，x是当前两桶水之和
        }
        return false;
    }
}
