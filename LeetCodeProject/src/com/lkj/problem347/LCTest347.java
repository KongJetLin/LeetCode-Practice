package com.lkj.problem347;

import java.util.TreeMap;

public class LCTest347
{
//--------------------------------------------内部动态数组
    private class Array<E> {

        private E[] data;
        private int size;

        // 构造函数，传入数组的容量capacity构造Array
        public Array(int capacity){
            data = (E[])new Object[capacity];
            size = 0;
        }

        // 无参数的构造函数，默认数组的容量capacity=10
        public Array(){
            this(10);
        }

        public Array(E[] arr){
            data = (E[])new Object[arr.length];
            for(int i = 0 ; i < arr.length ; i ++)
                data[i] = arr[i];
            size = arr.length;
        }

        // 获取数组的容量
        public int getCapacity(){
            return data.length;
        }

        // 获取数组中的元素个数
        public int getSize(){
            return size;
        }

        // 返回数组是否为空
        public boolean isEmpty(){
            return size == 0;
        }

        // 在index索引的位置插入一个新元素e
        public void add(int index, E e){

            if(index < 0 || index > size)
                throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

            if(size == data.length)
                resize(2 * data.length);

            for(int i = size - 1; i >= index ; i --)
                data[i + 1] = data[i];

            data[index] = e;

            size ++;
        }

        // 向所有元素后添加一个新元素
        public void addLast(E e){
            add(size, e);
        }

        // 在所有元素前添加一个新元素
        public void addFirst(E e){
            add(0, e);
        }

        // 获取index索引位置的元素
        public E get(int index){
            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Get failed. Index is illegal.");
            return data[index];
        }

        // 修改index索引位置的元素为e
        public void set(int index, E e){
            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Set failed. Index is illegal.");
            data[index] = e;
        }

        // 查找数组中是否有元素e
        public boolean contains(E e){
            for(int i = 0 ; i < size ; i ++){
                if(data[i].equals(e))
                    return true;
            }
            return false;
        }

        // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
        public int find(E e){
            for(int i = 0 ; i < size ; i ++){
                if(data[i].equals(e))
                    return i;
            }
            return -1;
        }

        // 从数组中删除index位置的元素, 返回删除的元素
        public E remove(int index){
            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Remove failed. Index is illegal.");

            E ret = data[index];
            for(int i = index + 1 ; i < size ; i ++)
                data[i - 1] = data[i];
            size --;
            data[size] = null; // loitering objects != memory leak

            if(size == data.length / 4 && data.length / 2 != 0)
                resize(data.length / 2);
            return ret;
        }

        // 从数组中删除第一个元素, 返回删除的元素
        public E removeFirst(){
            return remove(0);
        }

        // 从数组中删除最后一个元素, 返回删除的元素
        public E removeLast(){
            return remove(size - 1);
        }

        // 从数组中删除元素e
        public void removeElement(E e){
            int index = find(e);
            if(index != -1)
                remove(index);
        }

        public void swap(int i, int j){

            if(i < 0 || i >= size || j < 0 || j >= size)
                throw new IllegalArgumentException("Index is illegal.");

            E t = data[i];
            data[i] = data[j];
            data[j] = t;
        }

        @Override
        public String toString(){

            StringBuilder res = new StringBuilder();
            res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
            res.append('[');
            for(int i = 0 ; i < size ; i ++){
                res.append(data[i]);
                if(i != size - 1)
                    res.append(", ");
            }
            res.append(']');
            return res.toString();
        }

        // 将数组空间的容量变成newCapacity大小
        private void resize(int newCapacity){

            E[] newData = (E[])new Object[newCapacity];
            for(int i = 0 ; i < size ; i ++)
                newData[i] = data[i];
            data = newData;
        }
    }
//----------------------------------------------------内部最大堆
    private class MaxHeap<E extends Comparable<E>> {

        private Array<E> data;

        public MaxHeap(int capacity){
            data = new Array<>(capacity);
        }

        public MaxHeap(){
            data = new Array<>();
        }

        public MaxHeap(E[] arr){
            data = new Array<>(arr);
            for(int i = parent(arr.length - 1) ; i >= 0 ; i --)
                siftDown(i);
        }

        // 返回堆中的元素个数
        public int size(){
            return data.getSize();
        }

        // 返回一个布尔值, 表示堆中是否为空
        public boolean isEmpty(){
            return data.isEmpty();
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
        private int parent(int index){
            if(index == 0)
                throw new IllegalArgumentException("index-0 doesn't have parent.");
            return (index - 1) / 2;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
        private int leftChild(int index){
            return index * 2 + 1;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
        private int rightChild(int index){
            return index * 2 + 2;
        }

        // 向堆中添加元素
        public void add(E e){
            data.addLast(e);
            siftUp(data.getSize() - 1);
        }

        private void siftUp(int k){

            while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
                data.swap(k, parent(k));
                k = parent(k);
            }
        }

        // 看堆中的最大元素
        public E findMax(){
            if(data.getSize() == 0)
                throw new IllegalArgumentException("Can not findMax when heap is empty.");
            return data.get(0);
        }

        // 取出堆中最大元素
        public E extractMax(){

            E ret = findMax();

            data.swap(0, data.getSize() - 1);
            data.removeLast();
            siftDown(0);

            return ret;
        }

        private void siftDown(int k){

            while(leftChild(k) < data.getSize()){
                int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
                if( j + 1 < data.getSize() &&
                        data.get(j + 1).compareTo(data.get(j)) > 0 )
                    j ++;
                // data[j] 是 leftChild 和 rightChild 中的最大值

                if(data.get(k).compareTo(data.get(j)) >= 0 )
                    break;

                data.swap(k, j);
                k = j;
            }
        }

        // 取出堆中的最大元素，并且替换成元素e
        public E replace(E e){

            E ret = findMax();
            data.set(0, e);
            siftDown(0);
            return ret;
        }
    }
//---------------------------------------------内部队列接口
    private interface Queue<E> {

        int getSize();
        boolean isEmpty();
        void enqueue(E e);
        E dequeue();
        E getFront();
    }
//--------------------------------------------内部优先队列
    private class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

        private MaxHeap<E> maxHeap;

        public PriorityQueue(){
            maxHeap = new MaxHeap<>();
        }

        @Override
        public int getSize(){
            return maxHeap.size();
        }

        @Override
        public boolean isEmpty(){
            return maxHeap.isEmpty();
        }

        @Override
        public E getFront(){
            return maxHeap.findMax();
        }

        @Override
        public void enqueue(E e){
            maxHeap.add(e);
        }

        @Override
        public E dequeue(){
            return maxHeap.extractMax();
        }
    }

//------------------------------------------------------------------------------------------下面是解决方案
    /**
     * 首先，我们使用自己定义的 使用最大堆实现的优先队列来 完成。
     * 我们首先扫描一遍数组，将数组的n个元素以及他们在数组中出现的次数分别存储到 TreeMap中（key是数字，value是出现次数）
     * 由于我们向获取出现频率前k高的元素，我们必须要将 TreeMap中的数字按出现频率的高低，存储到优先队列中，而这个优先队列
     * 的队首必须存储出现频率较低的数字，每次遇到出现频率比队首数字高的数字，将队首的数字弹出，并将新的数字入队。
     *
     * 总结就是：出现频率较低的数字保存在队首，即在优先队列中，出现次数越低的元素优先级越高，这样才能保证如果遍历到出现频率比队首数字高的数字，
     *          可以将队首数字出队，将新的数字入队。最后留在队列中的k个元素出现频率最高。
     *
     * 实现：为了实现数字出现频率低而优先级高的功能，我们定义一个类 ComTemp，这个类实现Comparable，具有比较性。
     *   这个类有2个成员，num和freq，num我们用来存储数字，freq用来存储数字出现的次数。
     *   然后实现compareTo()方法，使得 freq越小的 ComTemp类的对象越大。然后我们入队的时候，入队 ComTemp 类的对象，
     *   队首存储的就是优先级最大的ComTemp，即freq最小的ComTemp对象，每次来一个新的ComTemp对象，如果它的出现频次比队首的ComTemp大，
     *   此时队首的ComTemp是队列中最大的ComTemp，也是队列中出现频率最小的ComTemp，我们将队首元素出队，将新的元素入队，
     *   这样，就可以实现后面来的出现频率高的 ComTemp对象，将前面队列出现频率最低的ComTemp对象替换，最后遍历结束，
     *   整个队列留下来的就是出现频率前k高的k个ComTemp对象
     */

    //定义一个用于存储 数字 以及 数字出现频率 的临时比较累
    private class ComTemp implements Comparable<ComTemp>
    {
        public int num , freq;
        //创建ComTemp类对象的时候，我们传入一个 num 以及其出现频率 freq 来构造这个ComTemp对象
        public ComTemp(int num , int freq)
        {
            this.num = num;
            this.freq = freq;
        }

        //实现compareTo()方法
        @Override
        public int compareTo(ComTemp anotherComTemp)
        {
            //如果当前ComTemp对象数字的频率大于传入ComTemp对象数字的频率，根据频率越小ComTemp对象在队列中优先级越高的原则，
            // 频率越小，ComTemp对象应该越大，此时应该返回-1，表示当前的ComTemp小于传入的ComTemp对象
            if(this.freq > anotherComTemp.freq)
                return -1;
            else if(this.freq < anotherComTemp.freq)
                return  1;
            else
                return 0;
        }

        /**
         * 当然，如果数组中出现频次相同的数，还可以比较数的大小来分别判断这几个出现次数相同的数优先级的高低，
         * 但是这里没有要求（题目明确数组中前 k 个高频元素的集合是唯一的，没有说明频次相同数怎么比较，说明题目明确数组不会出现频次相同的数）
         */
    }

    //实现347题
    public int[] topKFrequent(int[] nums, int k)
    {
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for (int num : nums)
        {//对于n个数，其containKeys方法的 总时间复杂度教育 O(nlog(n))，因为只有树有n个元素的时候，
            // 查询是否包含才是 O(logn)，而前 n-1 个数containsKey 方法时间复杂度都小于 O(logn)，因此总体查询n个元素是否包含，时间复杂度小于O(nlogn)
            if(tm.containsKey(num))
                tm.put(num , tm.get(num)+1);//如果原来存在这个数，将数的出现次数+1
            else
                tm.put(num , 1);//数第一次出现，将其出现次数设置为1
        }

        //创建一个优先队列，存储ComTemp对象
        PriorityQueue<ComTemp> pq = new PriorityQueue<>();
        //对于tm中的每一个key，也就是数字 ，进行遍历
        for (int key : tm.keySet())
        {
            //首先，先将k个ComTemp对象入队
            /**
             * 注意，pq队列的size初始值为0，当加入第一个元素的时候，size变为1，此时1<2，可以加入第二个元素，此时size变为2,2=2，无法加入第三个元素
             * 也就是说，这里如果我们向添加k个元素，那么必须设置 pq.getSize()<k，而不是pq.getSize()<=k，这样会添加k+1个元素！！
             */
            if(pq.getSize()<k)
            {
                pq.enqueue(new ComTemp(key, tm.get(key)));//将数字（key）与数字出现的频率（value，tm.get(key)）构造成为ComTemp对象放入优先队列
            }
            else if(pq.getFront().freq < tm.get(key))
            {
                //k个数字已经放满，如果后面遍历到的tm中的数字出现的频率高于队首的数字出现的频率，
                // 我们将队首的ComTemp对象出队，将新的数字构造成为ComTemp对象放入队列
                pq.dequeue();
                pq.enqueue(new ComTemp(key , tm.get(key)));
            }
        }

        int[] res = new int[pq.getSize()];
        //结束循环后，队列中保存的就是出现频率前k的数字以及其出现频率所构造成为的ComTemp对象
        /**注意！！！
         * 这里不能使用for循环，因为pq在出队的过程中，size会发生变化，这样便获取不到正确的pq中的数，
         * 这里应该使用 while(!pq.isEmpty)
         */
        int i = 0;
        while(!pq.isEmpty())
        {
            //这里必须使用出队，不能使用获取队首元素，因为获取队首元素每次都是获取一样的元素，而出队后，每次队首元素都不一样！
            res[i] = pq.dequeue().num;//将队列的ComTemp对象的num出队到数组中
            i++;
        }
        return res;
    }

    private static void printList(int[] nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
        int k = 2;
        printList((new com.lkj.problem347.LCTest347()).topKFrequent(nums, k));
    }
}
