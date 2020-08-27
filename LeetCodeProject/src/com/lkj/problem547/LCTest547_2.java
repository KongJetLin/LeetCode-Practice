package com.lkj.problem547;

import java.util.LinkedList;
import java.util.Queue;

/** 广度优先遍历
 * 参考：https://leetcode-cn.com/problems/friend-circles/solution/peng-you-quan-by-leetcode/
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 */
public class LCTest547_2
{
    /**
    广度优先遍历与深度优先遍历其实是一样的，同样是寻找一个 还没有被纳入某一个朋友圈 的结点的“朋友圈”，
     将这个结点朋友圈内的所有结点标记为 visited[该结点下标]=1，每次将一个“朋友圈”的所有结点标记位1后，就将所有朋友圈数量+1
     */
    public int findCircleNum(int[][] M)
    {
        int[] visited = new int[M.length];
        int count=0;

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < M.length ; i++)
        {
            if(visited[i] == 0)
            {
                visited[i] = 1;
                count++;//每次开启一个新的“朋友圈”的访问，数量加1
                queue.add(i);
                while(!queue.isEmpty())
                {
                    int temp = queue.remove();
                    //寻找该结点所在朋友圈的所有 还没有被标记visited=1的结点，将他们的visited标记位1，继续寻找这些结点的朋友
                    for (int j = 0; j < M.length ; j++)
                    {
                        if(M[temp][j]==1 && visited[j]!=1)
                        {
                            visited[j] = 1;//将j结点标记位访问过
                            queue.add(j);//寻找j结点的所有朋友，将其加入队列
                        }
                    }
                }
            }
        }
        return count;
    }
}
