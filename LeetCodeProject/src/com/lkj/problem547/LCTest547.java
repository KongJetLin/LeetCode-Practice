package com.lkj.problem547;

/** 深度优先遍历
 * 参考：https://leetcode-cn.com/problems/friend-circles/solution/peng-you-quan-by-leetcode/
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
public class LCTest547
{
    public int findCircleNum(int[][] M)
    {
        //“朋友圈”的数量，其实就是 连通块 的数量
        int[] visited = new int[M.length];
        int count=0;
        //寻找每一个 还没有被纳入某一个朋友圈 的结点的“朋友圈”，将这个结点朋友圈内的所有结点标记为 visited[该结点下标]=1
        // 每次将一个“朋友圈”的结点标记位1后，就将所有朋友圈数量+1
        for (int i = 0; i < M.length ; i++)
        {
            if(visited[i] == 0)//若 visited[i]  1，说明i结点已经在其他朋友圈内，不需要再次计算
            {
                //深度优先遍历，将 包含i结点的朋友圈 的所有结点的 visited 标记位1（即在某一个朋友圈内）
                dfs(M , visited , i);
                count++;
            }
        }
        return count;
    }

    //查找下标为i的元素的朋友圈内的所有结点
    private void dfs(int[][] M , int[] visited , int i)
    {
        //遍历所有结点，查看他们是不是i的朋友
        for (int j = 0; j < M.length; j++)
        {
            /**
            如果某一个结点是i的朋友，即M[i][j]=1（M[j][i]也一样，该矩阵对角线对称），且 visited[j]!=1，
             那么将该结点 visited[j]=1，并深度优先遍历寻找j的朋友，继续标记visited，直到该 连通块 所有结点标记完毕。

             为什么要限制 visited[j]!=1才访问？
             因为若 M[i][j]=1，说明i与j连接，若 visited[j]=1，说明j已经被该朋友圈内的其他结点遍历过，即该 连通块 内形成一个环的形状（如下）
                        i —— j
                         \ /
                          k
             如果我们对  M[i][j]=1 且 visited[j]=1 的结点 j 也进行dfs，那么 j又会在下一次dfs中 遍历i，k，而i在下下次dfs
             中遍历 j/k...如果便会无限循环下去！！
             因此，对于 M[i][j]=1 且 visited[j]=1 已经纳入本朋友圈的结点，它已经被深搜完毕，不需要重复深搜！
             */
            if(M[i][j]==1 && visited[j]==0)
            {
                //对于“朋友圈”内之前没有深搜过的结点，进行深搜，寻找他的朋友结点。
                visited[j] = 1;
                dfs(M , visited , j);
            }
        }
    }
}
