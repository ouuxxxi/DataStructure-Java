package com.java.arraylist;

import java.util.ArrayList;
//以指定格式打印ArrayList（要求：用{}括起来，且中间用@符号隔开）
public class Demon3 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("小鱼");
        list.add("小兔");
        list.add("老虎");
        list.add("狮子");
        System.out.println("初始打印格式为：" + list);
       getList(list);
    }

    //只进行打印而已 不需要有返回值
    public static void getList(ArrayList<String> list) {
        System.out.print("{");
        for (int i = 0; i < list.size(); i++) {
            if(i==list.size()-1){
                System.out.println("}");//最后一个元素print 与println 无所谓
            }else{
                System.out.print(list.get(i) + "@");
            }

        }
    }
}