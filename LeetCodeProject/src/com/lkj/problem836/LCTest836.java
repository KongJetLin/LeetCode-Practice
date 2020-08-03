package com.lkj.problem836;

public class LCTest836
{
    //重叠的矩形在 X、Y 上的投影必然重叠
    // 参考：https://leetcode-cn.com/problems/rectangle-overlap/solution/tu-jie-jiang-ju-xing-zhong-die-wen-ti-zhuan-hua-we/
    public boolean isRectangleOverlap(int[] rec1, int[] rec2)
    {
        /**
         在 X/Y 轴上，2个矩形的区间 [a1,b1]、[a2,b2] 的位置情况一共有6种，其中 X、Y不重叠有2种：
         b1<a2 或者 b2<a1，那么我们在X、Y轴上，分别根据这两种情况来排除即可！
         这里需要注意的就是X、Y轴上的坐标在 rec1与rec2中如何取得的问题：
         rec1:[x1起点 ，y1起点 ，x1终点，y1终点]、rec2:[x2起点 ，y2起点 ，x2终点，y2终点]
         */
        //若 rec1[2]<=rec2[0] || rec2[2]<=rec1[0] 都没有出现，即都为false，说明2个矩形在X轴上的投影相交，此时 resultX=true
        // 若 rec1[2]<=rec2[0] || rec2[2]<=rec1[0] 有一个出现，即括号内值为true，说明2个矩形在x轴的投影没有相交，此时 resultX=true
        boolean resultX = !(rec1[2]<=rec2[0] || rec2[2]<=rec1[0]);//注意，要加“=”，因为就算边界相交也不算重叠
        boolean resultY = !(rec1[3]<=rec2[1] || rec2[3]<=rec1[1]);

        //当2个区间的投影在X、Y轴都相交的时候，才算2个矩形重叠，此时 resultX与resultY 都为true
        return resultX && resultY;
    }
}
