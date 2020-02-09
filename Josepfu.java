package com.ycx.linkedlist;
//先创建节点类，表示一个节点

class Node{
    public int no;//编号
    public Node next;//节点的next域：指向下一个节点，默认是null

    public Node(int no){
        this.no=no;
    }
}


//创建一个环形的单向链表:
class CircleSingleLinkedList{

    public Node first = new Node(-1);
    //创建一个first节点，当前无编号


    //添加小孩节点，构建环形链表(表示需要添加多少个节点)
    public void addBoy(int num) {
        //进行一个num的校验
        if (num < 1) {
            System.out.println("人数不能少于1");
            return;
        }

        Node curBoy = null; //辅助指针，帮助构建环形链表

        //使用for循环创建环形链表（小孩人数大于等于1，所以从1开始）
        for (int i = 1; i <= num; i++) {
            Node boy = new Node(i);//根据编号，创建小孩节点

            //如果是第一个节点（小孩）
            if (i == 1) {
                first = boy; //first指向boy
                first.next=first ; //将第一个节点自身构成环
                curBoy = first; //让curBoy指向第一个小孩
            } else {
                curBoy.next=boy;//指向新的boy
                boy.next=first; //新的boy的next指向first（构成环）
                curBoy = boy;//因为first不能动，因此我们仍然使用一个辅助指针完成遍历
            }
        }
    }

    //遍历当前环形链表
    public void print() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("单项链表为空");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Node curBoy = first;
        while (true){
            System.out.printf("显示小孩的编号 %d \n" , curBoy.no);
            if(curBoy.next== first){ //说明已经遍历完
                break;
            }
            curBoy = curBoy.next; //curBoy后移
        }
    }

    //根据用户的输入，计算出小孩（节点）出圈顺序

    /*

    * startNo:表示从第几个节点开始数数
    * count：表示数几下
    * nums：表示最初环形链表中最开始有几个节点

    */
    public  void countBoy(int startNo,int count,int nums){
        //先对数据进行校验
        if(first==null || startNo<1 || startNo>nums){
            System.out.println("参数有误，请重新输入");
            return;
        }

        //创建一个辅助指针helper（指向最后一个节点）
        Node helper = first;
        while (true){
            if(helper.next==first){ //说明helper指向了最后一个节点
                break;
            }
            helper = helper.next;
        }

        //报数之前，先让first和helper移动k-1次(不一定是从第一个人开始报数的，可能是从第k个人开始报数的)
        for (int i = 0; i <startNo-1 ; i++) {
            first=first.next;
            helper=helper.next;
        }

        //当小孩报数时，让first 和helper指针同时的移动count-1次
        //进行循环，直到只剩下一个节点
        while (true){
            if(helper == first){ //只剩最后一个节点
                break;
            }

        //让first 和helper指针同时的移动count -1次
        for (int i = 0; i <count-1 ; i++) {
            first=first.next;
            helper=helper.next;
        }

        //此时first指向的节点即为出圈的节点
            System.out.printf("小孩%d出圈 \n",first.no);

            first=first.next;
            helper.next=first;//一直循环
        }
        System.out.printf("显示最后在圈中的小孩编号%d\n",first.no);
    }
}

public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//加入5个小孩节点
        circleSingleLinkedList.print();

        //测试小孩出圈  2 4 1 5 3
        circleSingleLinkedList.countBoy(1,2,5);
    }
}




