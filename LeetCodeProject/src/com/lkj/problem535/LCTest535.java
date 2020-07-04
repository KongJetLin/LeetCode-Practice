package com.lkj.problem535;

import java.util.HashMap;

/** 使用 longUrl 哈希值作为键，将 longUrl 存储到哈希表
1、可加密 URL 的数目由 int范围 决定，因为 hashCode 使用整数运算。
 2、加密后 URL 的平均长度与 longURL 的长度没有直接关联。
 3、hashCode() 对于不同的字符串不一定产生独一无二的加密后 URL。像这样对于不同输入产生相同输出的过程叫做冲突。
 因此，如果加密字符串的数目增加，冲突的概率也会增加，最终导致算法失效。
 */
public class LCTest535
{
    HashMap<Integer , String> map = new HashMap<>();

    public String encode(String longUrl) {
        map.put(longUrl.hashCode() , longUrl);
        return "http://tinyurl.com/"+longUrl.hashCode();
    }


    public String decode(String shortUrl) {
        //注意，map获取到的值是字符串，而键是Integer，需要将字符串整数转换为Integer
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/" , "")));
    }
}
