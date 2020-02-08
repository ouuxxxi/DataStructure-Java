package com.ycx.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        //测试
        System.out.println("测试数组模拟环形队列");

        //创建一个环形队列
        CircleArray queue = new CircleArray(4);//有一个空位置 有效数据最大是3
        char key = ' ';//接受用户输入
        Scanner input = new Scanner(System.in);
        boolean flag = true; //控制循环 默认死循环

        //输出菜单
        while (flag){
            System.out.println("s(show),显示队列");
            System.out.println("e(exit),退出队列");
            System.out.println("a(add),添加数据到队列");
            System.out.println("g(get),从队列取出数据");
            System.out.println("h(head),查看队列头的数据");

            key=input.next().charAt(0);//接受收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输一个数");
                    int val=input.nextInt();
                    queue.addQueue(val);
                    break;
                case 'g':   //取出数据  因为方法里面抛出了异常 所以这里需要捕获
                    try{
                        int res=queue.getQueue();
                        System.out.printf("取出的数据为%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res=queue.headQueue();
                        System.out.printf("队列头的数据为%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':    //退出程序
                    input.close();//关闭
                    flag=false;
                    break;

                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}


class CircleArray{
    private  int maxSize;
    //表示数组的最大容量
    private  int front;
    //front变量含义做一个调整：指向队列的第一个元素，arr[front]即为队列的第一个元素
    //front=0
    private  int rear;
    //rear变量含义做一个调整：rear指向队列的最后一个元素的后一个位置，预留一个位置
    //rear=0
    private  int[] arr;
    // 该数组用于存放数据，模拟队列


    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front=0;
        rear=0;
    }


    //1.判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    //2.判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }


    //3.添加数据到队列中
    public void addQueue(int n) {
        //判断队列是否为满
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
        }
        arr[rear] = n;
        //直接将数据加入 因为rear本身指的就是 后一个元素
        rear = (rear + 1) % n;
        //将rear 后移 并取模 (环形 当rear走到最后一个位置时，取模就可以到前面的位置)
    }

        //4.获取队列的数据，出队列
        public  int getQueue(){
            if(isEmpty()){
                throw new RuntimeException("队列空，不能取数据");
            }

            //这里需要分析出front是指向队列的第一个元素
            //①. 先把front对应的值保留到一个临时变量；
            //②. 将front后移，考取取模（因为front也可能走到最后一个元素，就会越界）
            //③. 将临时保存的变量返回
            int value = arr[front];
            front = (front+1) % maxSize;
            return value;
        }


        //5.显示当前队列数据
    public void showQueue() {
        //遍历
        if(isEmpty()){
            System.out.println("当前队列为空");
            return;
        }

        //思路: 从front开始遍历 一共有front+有效数据个数
        for (int i = front ;i <front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]); //格式化输出
                                             // i可能也会超出下标（环形）
        }
    }

    //求出当前有效数据个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
        // rear=1 front=0 maxSize=3
    }


    //6.显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front]; //注意：front本身就指向第一个元素
    }



}