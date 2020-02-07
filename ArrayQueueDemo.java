package com.ycx.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
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

//一、使用数组模拟队列-编写一个ArrayQueue类

class ArrayQueue{
    private  int maxSize;//表示数组的最大容量
    private  int front; //队列头
    private  int rear; // 队列尾
    private  int[] arr; // 该数组用于存放数据，模拟队列


    //创建队列的构造器
    public ArrayQueue(int arrMaxsize){
        maxSize=arrMaxsize;
        arr = new int[maxSize];// 初始化数组
        front=-1;//指向队列头部，分析出front是指向队列头的前一个位置 有效数据的位置
        rear=-1;//指向队列尾，指向队列尾的数据（即就是队列最后一个位置 ）
    }

    //1.判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;//因为rear是从-1开始的（如果不理解就看笔记上的图）
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //2.添加数据到队列中
    public void addQueue(int n){
        //判断队列是否为满
        if(isFull()){
            System.out.println("队列已满，不能加入数据");
        }
        rear++;//rear 后移
        arr[rear]=n; //也可以直接写成 arr[++rear]：rear先加再取值
    }

    //3.获取队列的数据，出队列
    public  int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        front++; //front 后移 因为front指向的是前一个元素（front=-1）
        return arr[front];
    }

    //4.显示当前队列数据
    public void showQueue() {
         //遍历
         if(isEmpty()){
             System.out.println("当前队列为空");
             return;
         }
        for (int i = 0; i <arr.length ; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]); //格式化输出
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1]; //注意：front需要加1
    }
}