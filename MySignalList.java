//单链表

// 1、无头单向非循环链表实现 public class SingleLinkedList {    
// 头插法     public void addFirst(int data);  
// 尾插法     public void addLast(int data);    
// 任意位置插入,第一个数据节点为0号下标     public boolean addIndex(int index,int data);    
// 查找是否包含关键字key是否在单链表当中     public boolean contains(int key);    
// 删除第一次出现关键字为key的节点     public void remove(int key);    
// 删除所有值为key的节点     public void removeAllKey(int key);    
// 得到单链表的长度         public int size();     public void display();     public void clear(); }

class ListNode {
    public int data; //data可以是任何类型
    public ListNode next;

    public ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}  //节点类

class MySignalList {
    public ListNode head; //定义头部

    public MySignalList() {
        this.head = null;
    }
    //单链表


    //一、头插法   node 代表该节点的地址
    public void addFirst(int data) {
        ListNode node = new ListNode(data);//列表为空

        // 判断是不是第一插入？ 不是的话就如下代码所示
        if (this.head == null) {
            this.head = node;
        } else {
            node.next = this.head;
            this.head = node;
        }

    }

    //二、尾插法  cur:定义的引用 指向head
    public void addLast(int data) {
        ListNode node = new ListNode(data);
        ListNode cur = this.head;
        if (this.head == null) {
            this.head = node;
        } else {
            while (cur.next != null) {
                cur = cur.next;//不为空，就一直往后走
            }
            cur.next = node;
        }
    }

    //三、查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        ListNode cur = this.head;//从头开始
        while (cur != null) {
            if (cur.data == key) {
                return true;
            }
            cur = cur.next;//一直往后走
        }
        return false;
    }

    //四、得到单链表的长度
    public int getLength() {
        ListNode cur = this.head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //五、任意位置插入,第一个数据节点为0号下标

    private ListNode searchIndex(int index) {
        ListNode cur = this.head;
        //cur -> index-1
        int count = 0;
        while (count < index - 1) {
            count++; //每走一次 count就加一次
            cur = cur.next;
        }
        return cur;
    }

    public boolean addIndex(int index, int data) {

        //判断index是否合法

        if (index < 0 | index > getLength()) {
            System.out.println("index不合法！");
            return false;
        }
        if (index == 0) { //头插法
            addFirst(data);
            return true;
        }

        //找到index-1的位置
        ListNode cur = searchIndex(index);
        ListNode node = new ListNode(data);//每次node引用时，需要先new一个node
        node.next = cur.next;
        cur.next = node;
        return true;
    }


   

    //六、删除第一次出现关键字为key的节点

 //
    private ListNode searchPre(int key) {
        //创建一个方法便于找到前驱
        ListNode pre = this.head;
        //头已经判断过了
        while (pre.next != null) {
            if (pre.next.data == key) {
                return pre;
            }
        pre=pre.next;
        }
        return null;
    }
  
  public void remove(int key) {
        if (this.head == null) {
            System.out.println("单链表为空");
            return;
        }
        //0、删除的节点是否是头结点
        if (this.head.data == key) {
            this.head = this.head.next;
            return;
        }

        //1、找到key的前驱  如果返回空
        ListNode pre = searchPre(key);
        //2、删除节点
        ListNode del = pre.next;
        pre.next = del.next;
    }


    //七、删除所有值为key的节点
    public void removeAllKey(int key) {
        ListNode pre = this.head;
        ListNode cur = this.head.next;
        while (cur != null) {
            if (pre.next.data == key) {
                pre.next = cur.next;
                cur = cur.next;
            } else {    //  if(pre.next.data!=key)
                pre = cur;
                cur = cur.next;
            }
            if (this.head.data == key) { //如果头节点为删除的数
                this.head = this.head.next;
            }
        }

    }

    //打印单链表
    public void display() {
        ListNode cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public void display2(ListNode newHead) {
        ListNode cur = newHead;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    //单链表的反转
//方法一、头插法
//方法二   时间复杂度O（N）
    public ListNode reserveList() {
        ListNode pre = null;
        ListNode newHead = null;
        ListNode cur = this.head;

        while (cur != null) {
            ListNode curNext = cur.next;
            if (curNext == null) {
                newHead = cur;
            }
            cur.next = pre;//为空
            pre = cur;
            cur = curNext;
        }
      return newHead;
    }

    //寻找中间节点
    public ListNode middleNode() {
        ListNode fast = this.head;//快指针
        ListNode slow = this.head;//慢指针
        while (fast != null && fast.next != null) {
            fast = fast.next.next;//快指针走2步
            slow = slow.next;//慢指针走一步
        }
        return slow;
    }

    //找到倒数第K个节点
    public ListNode F(int k) {
        if (k < 0 || k > getLength()) {
            return null;
        }
        ListNode fast = this.head;
        ListNode slow = this.head;

        while (k > 0) {
            if (fast.next != null) {
                fast = fast.next;//fast先走
                k--;
            } else {
                System.out.println("没有这个节点");
                return null;

            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

     //分割单链表
    //以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前
    public ListNode partition(int x) {
        ListNode cur = this.head;
        ListNode beforeStart = null;
        ListNode beforeEnd = null;
        ListNode afterStart = null;
        ListNode afterEnd = null;
        while (cur != null) {

            /*方法一（防止死循环）
               ListNode curNext =cur.next;
               cur.next=null;
           */

            //cur.data < x
            if (cur.data < x) {
                //第一次插入
                if (beforeStart == null) {
                    beforeStart = cur;
                    beforeStart = beforeEnd;
                } else {
                    beforeEnd.next = cur;
                    beforeEnd = beforeEnd.next;
                }
            } else {
                //第一次插入
                if (afterStart == null) {
                    afterStart = cur;
                    afterStart = afterEnd;

                } else {
                    afterEnd.next = cur;
                    afterEnd = afterEnd.next;
                }
            }
            cur = cur.next;
            //cur=curNext;(方法一)
        }
        if (beforeStart == null) {  //第一个区间为空
            return afterStart;
        } else {
            beforeEnd.next = afterStart;
            if (afterStart != null) { //不为空，所以第二个区间不为空，将afterEnd置空
                afterEnd.next = null;//最后一个节点如果小于X，beforeEnd.next = afterStart，则会形成死循环，所以需要置空；
                                      //方法二
            }
            return beforeStart;
        }
    }

     //删除重复的节点
    // 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    public ListNode deleteDuplication() {
        ListNode node = new ListNode(-1);
        ListNode cur = this.head;
        ListNode tmp = node;
        while (cur != null) {
            if (cur.next != null &&
                    cur.data == cur.next.data) {
                //1、循环
                while (cur.next != null && cur.data == cur.next.data) {
                    cur = cur.next;
                }
                //2、退出循环 cur要多走一步
                cur = cur.next;
                // tmp.next=cur;
            } else {
                //当前节点 不等于下一个节点的时候
                tmp.next = cur;
                cur = cur.next;
                tmp = tmp.next;
            }
        }
        tmp.next = null;//不要忘记
        return node.next;
    }


    // 链表的回文结构
    public boolean chkPalindrome() {
        ListNode fast = this.head;
        ListNode slow = this.head;

        //寻找中间节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //反转
        ListNode p = slow.next;
        while (p != null) {
            ListNode pNext = p.next;
            //反转
            p.next = slow;
            slow = p;
            p = pNext;
        }
        //slow往前    head 往后  .data不一样 返回false
        //直到相遇
        while (slow != this.head) {
            if (this.head.data != slow.next.data) {
                return false;
            }
            //偶数
            if (this.head.next == slow) {
                return true;
            }
            this.head = this.head.next;
            slow = slow.next;
        }
        return false;
    }

    //判断是否有环
    //给定一个链表，判断链表中是否有环（一个走一步，一个走两步，总会相遇，但是三步就不一定能遇到）
    public void creatLoop() {
        ListNode cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = this.head.next.next;
    }

    public boolean hasCycle() {
        ListNode fast = this.head;
        ListNode slow = this.head;

        // fast走两步，slow走一步，判断是否相遇
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
               // return true;
                break;
            }
        }
       if (fast == null || fast.next == null){
            return false;
       }
       return true;
        //return false;
    }

// 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
//相遇之后，一个从前走，一个从后走，相遇的地方就是入环的第一个节点

    public ListNode detectCycle() {
    ListNode fast =this.head;
    ListNode slow=this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                //fast=this.head;
                 //while(fast!=slow){
                    //fast=fast.next;
                    //slow=slow.next;
                 //}
                    //if(fast==slow){
                     //  return fast;
                       break;
                    //}
            }
        }
        if(fast ==null || fast.next == null){
            return null;
        }
        //return null;
        fast=this.head;//前面相遇了就说明有环，把slow或者fast其中一个拉到头前，
                       // 再一个一个走，如果相遇了，就说明此为入口环的第一个节点。
        while(fast!=slow){
        fast=fast.next;
        slow=slow.next;
        }
        return fast;
    }

//环的长度

    public int listLen() {
        ListNode fast =this.head;
        ListNode slow=this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        if(fast ==null || fast.next == null){
            return -1;
        }
        int count =1;
        fast=this.head;//已经相遇了，先让fast走一步
        while(fast!=slow){
            fast=fast.next;
            count++;
        }
        return count;
    }
}