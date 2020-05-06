package com.ycx.MapandSet;

//哈希桶：数组 ＋ 链表
// 数组：可以帮助用户快速定位将元素插入哪个链表（数组中存储的元素实际为节点的引用）
// 假设哈希桶中的key唯一 不带头的单链表
public class HashBucket {
    public static  class  Node{
        int key;
        int value;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    //哈希桶中的成员数据
    Node[] table;
    int capacity; //桶的个数
    int size; //有效元素的个数
    double loadFact = 0.75;

    public  HashBucket(int initCapacity) {

        //扩容
        resize();
        //保证哈希桶的初始容量至少为10个
        capacity = initCapacity;
        if (initCapacity < 10) {
            capacity = 10;
        }
            table = new Node[capacity];
            size = 0;
    }
        //一、插入
        public int put (int key , int value){

        //1、通过哈希函数，计算key所在的桶号
            int bucketNo = hasFunc(key);

        // 2、在bucketNo检测Key是否存在
        //    检测方式：遍历链表
            Node cur = table[bucketNo];//找到是第几个位置的链表
            while (cur!=null){
                if(cur.key == key){
                    int oldValue = cur.value; //旧的值保存起来
                    cur.value = value;//新的值赋给它
                    return oldValue;//返回旧的value
                }
                cur = cur.next; //不相等---继续往后走
            }

        //3、key不存在：将key-value插入到哈希桶中
            cur = new Node(key,value);
            cur.next = table[bucketNo];
            table[bucketNo] = cur;
            size++;
            return value;

        }

        //二、删除:将哈希桶中为key的键值对删除
    public boolean remove(int key){
        //1、通过哈希桶计算key的桶号
        int bucketNo = hasFunc(key);

        //2、在bucketNo桶中找到Key所对应的节点，找到后删除
        Node cur = table[bucketNo];
        Node prev = null;
        while (cur!=null){
            //找到与Key所对应的节点
            if(cur.key == key){
                if(prev == null){
                    //删除的是第一个节点
                    table[bucketNo] = cur.next;
                }else {
                    prev.next = cur.next;
                }
                size--;
                return true;
            }
            //没找到对应的节点 往后走
           else{
                prev = cur ;
                cur = cur.next;
            }
        }
        return false;
    }

        private int hasFunc(int key){
            return  key%capacity;
        }

       //三、包含key: O(1)
        public boolean containsKey(int key){
            //1、通过哈希桶计算key的桶号
            int bucketNo = hasFunc(key);

            //2、在bucketNo桶中找到Key所对应的节点，找到后删除
            Node cur = table[bucketNo];

            while (cur!=null){
                if(cur.key == key) {
                    return true;
                }
                cur = cur.next;
                }

            return false;
        }

    //三、包含value:O(n)
    public boolean containsValue(int value){
        //注意：哈希桶是根据key来计算哈希地址的
        //因此找value 不能计算出value在那个桶中
        for (int bucketNo = 0; bucketNo < capacity; bucketNo++) {
            Node cur = table[bucketNo];
            while (cur!=null){
                if(cur.value == value){
                    return true;
                }
                cur = cur.next;
            }
        }
        return false;
    }

    //四、桶中多少个元素
    public int size(){
        return size;
    }

    //五、是否为空
    public  boolean empty(){
        return size == 0;
    }

    //扩容
    private void resize(){
        //装载因子超过0.75时进行扩容----按照2倍的方式进行扩容
        // *10：比如8/10=0 是无效的 所以*10
        if(size*10 /capacity > loadFact *10){

            int newCap = capacity*2;

            Node[] newTable = new Node[capacity * 2];

            //将table中的元素搬移到newTable中
            for (int i = 0; i < capacity ; i++) {
                Node cur = table[i];//拿到第i个桶所对应的链表

                //将table中的i号桶所对应链表中所有节点插入到newTable中
                while (cur!=null){
                    table[i] = cur.next;

                    //将cur节点插入到newTable中

                    //1、计算cur在newTable中的桶号
                    //int bucketNo = hasFunc(cur.key); 不行 hashFunc里面用的是旧容量
                    int bucketNo = cur.key% newCap;

                    //2、将cur节点插入到newTable中
                    cur.next = newTable[bucketNo];
                    newTable[bucketNo] = cur;

                    //取table中的i号桶中下一个节点
                    cur = table[i];
                }
            }

            table = newTable;
            capacity = newCap;
        }
    }

    private void printHashBucket(){
        for (int bucketNo = 0; bucketNo < capacity; bucketNo++){
            System.out.printf("table[%d]---->",bucketNo);

            Node cur = table[bucketNo];
            while (cur!=null){
                System.out.print("["+cur.key + "," + cur.value + "]----->");
                cur =cur.next;
            }
        }
    }
    public static void main(String[] args) {
        HashBucket hashBucket = new HashBucket(5);
        hashBucket.put(1,1);
        hashBucket.put(11,11);
        hashBucket.put(2,2);
        hashBucket.put(6,6);
        hashBucket.put(7,7);
        hashBucket.put(71,71);
        hashBucket.put(81,81);
        hashBucket.put(61,61);
        System.out.println(hashBucket.size());
        hashBucket.printHashBucket();

        //验证扩容
        hashBucket.put(3,3);

        hashBucket.printHashBucket();
        System.out.println(hashBucket.containsKey(6));
        System.out.println(hashBucket.containsValue(11));

        hashBucket.remove(7);
        hashBucket.printHashBucket();
    }
}
