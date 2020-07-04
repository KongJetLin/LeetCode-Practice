package com.lkj.problem535;

import java.util.HashMap;
import java.util.Random;

/** 使用随机数作为键，将 longUrl 存储到哈希表
 1、可加密 URL 的数目由 int范围 决定，因为使用整数作为键。
 2、加密后 URL 的平均长度与 longURL 的长度没有直接关联，因为使用了随机整数。
3、由于加密过程中使用了随机数，就像前面的算法所述，当输入字符串的数目增加时，冲突的次数也会增加，导致算法失效
 4、由于使用了随机数，想根据产生的 URL 推测出加密算法是不可能的。
 */
public class LCtest535_2
{
    HashMap<Integer , String> map = new HashMap<>();
    Random r = new Random();
    int key = r.nextInt(Integer.MAX_VALUE);//key定义在外面，decode方法才能找到

    public String encode(String longUrl) {
        while (map.containsKey(key))
        {
            //如果原来哈希表中已经存在这个key，继续遍历，查找一个不存在的key
            key = r.nextInt(Integer.MAX_VALUE);
        }
        map.put(key , longUrl);
        return "http://tinyurl.com/"+key;//使用key值作为键和 URI
    }


    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/" , "")));
    }
}
