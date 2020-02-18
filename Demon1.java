package com.java.arraylist;

import java.util.ArrayList;
import java.util.Random;

//用一个大集合存入20个数字，筛选其中的数字，放到小集合里面。
//要求：使用自定义方法
public class Demon1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Random r = new Random();
        //在集合里随即放入20个数字
        for (int i = 0; i < 20; i++) {
            int num = r.nextInt(100)+1;//放入数字范围是1~100
            list.add(num);
        }
        ArrayList<Integer> list2 = getList(list);
        System.out.println("20个随机数字中偶数的个数是;"+list2.size());
        System.out.println(list2);
    }

    //筛选偶数 此方法接受大集合参数，返回小集合结果
    public  static ArrayList<Integer> getList(ArrayList<Integer> list){
       ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int num1=list.get(i);
            if(num1%2==0){
                list1.add(num1);
            }
        }
        return list1;
    }

}
