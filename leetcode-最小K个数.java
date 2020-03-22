package com.ycx.priorotyqueue1;
//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
import java.util.Arrays;
import java.util.PriorityQueue;

public class FindKNumbers {
    public static void main(String[] args) {
        int[]arr ={1,3,5,7,2,4,6,8};
        int k =4;
        int[] res = smallestK(arr,k);
        System.out.println(Arrays.toString(res));
    }
    public static int[] smallestK(int[] arr, int k) {
        if(k<=0){
            return new int[0];
        }
        PriorityQueue<Integer> p = new PriorityQueue<>(arr.length);

        for(int i = 0; i<arr.length;i++){
            p.offer(arr[i]);
        }

        int[] res = new int[k];
        for(int j = 0; j<res.length;j++){
            res[j] = p.peek();
            p.poll();
        }
        return res;
    }
}
