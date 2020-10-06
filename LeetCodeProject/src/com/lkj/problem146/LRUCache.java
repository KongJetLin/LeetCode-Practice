package com.lkj.problem146;

import java.util.LinkedHashMap;
import java.util.Map;

/** 原理分析：通过继承 LinkedHashMap 实现
1、首先是LinkedHashMap的构造函数，它有一个构造函数如下：
 public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
    super(initialCapacity, loadFactor);
    this.accessOrder = accessOrder;
 }

 2、我们首先要将 accessOrder 设置为true（默认是false），从而开启LRU缓存。
 当 accessOrder=true 的时候，当我们访问一个结点（get）的时候，就会触发 afterNodeAccess() 方法，将结点移动到链表尾部，
 即这个结点各个被使用，最近被使用的结点放到链表结尾，而最近最久没有被使用的结点放到链表头部。
 那么我们调用 LinkedHashMap 的构造方法：LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
 传入 数组容量、加载因子（0.75），accessOrder=true。

 3、在设置元素 put 的时候，会触发 afterNodeInsertion()方法，这个方法会将链表头结点移除（即将最久未使用的结点移除，
 实现缓存空间内保存的结点数目不变，而插入结点的时候，会将最久未使用的结点删除）。
 此时如果 removeEldestEntry(Map.Entry<k,v> eldest) 方法返回 true，才会执行删除链表第一个结点。
 因此我们第二步需要重写 removeEldestEntry()方法，在元素个数超过缓存空间的时候（插入一个元素），将其返回true，以移除缓存中的最久未使用结点

 4、需要注意的是，LRUCache 的一个构造方法可以指定缓存空间的容量，也就是用户可以指定缓存容量，这个要注意！

 总结：其实只有2步：（1）基础 LinkedHashMap，调用它的带参构造方法，传入（capacity、加载因子0.75，accessOrder=true）
（2）重写 removeEldestEntry()方法，在元素个数超过缓存空间的时候（插入一个元素），将其返回true，以移除缓存中的最久未使用结点
 */
public class LRUCache extends LinkedHashMap<Integer , Integer>
{
    private int capacity;

    public LRUCache(int capacity)
    {
        //1、调用父类 LinkedHashMap 的带参构造方法，设置缓存容量、加载因子（0.75f，注意加f，float类型），accessOrder=true
        super(capacity , 0.75f , true);
        this.capacity = capacity;//用户可以自定义缓存桶里
    }

    //2、复写 removeEldestEntry(Map.Entry<K,V> eldest)方法、
    @Override
    public boolean removeEldestEntry(Map.Entry<Integer , Integer> eldest)
    {
        //当键值对个数 size()大于缓存容量 capacity 的时候（put操作才会出现），则使得这个方法返回true，
        //即afterNodeInsertion 会移除链表头元素（最近最久未使用结点）
        return super.size()>capacity;//注意，获取元素个数用size()方法（调用LinkedHashMap的size()方法）
    }

    //get方法要求没有找到键值对时返回-1，那么用 getOrDefault() 方法代替
    public int get(int key)
    {
        return super.getOrDefault(key , -1);//调用LinkedHashMap的getOrDefault方法
    }

    //put方法直接复用 LinkedHashMap 即可
    public void put(int key, int value)
    {
        super.put(key , value);
    }
}
