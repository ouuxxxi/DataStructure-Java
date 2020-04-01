package com.ycx.sort;

import java.util.Arrays;

//希尔排序
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {2,45,3,4,8,6,12,0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void shellSort(int[] arr){
        int gap = arr.length;
        while (gap>1){
            gap = gap/3+1;

            for (int i = gap ; i <arr.length ; i++) { //从1开始（举例：表示手中已有一张牌了）
                int key = arr[i];
                int end = i-gap;
                while (end>=0 && key<arr[end]){ //当前这个数小于间隔的那个数
                    arr[end + gap] = arr[end];//该数向前，最后一个数向后移动一位
                    end -= gap; //key 继续向前比较
                }

                arr[end+gap] = key;
            }
            //gap--;
        }

    }
}
