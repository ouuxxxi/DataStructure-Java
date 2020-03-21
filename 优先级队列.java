package com.ycx.priorotyqueue;
//优先级队列
import java.util.PriorityQueue;

public class TestPriotyQueue1 {
    static void TestPriorityQueue2() {
        int[] arr = {4, 1, 9, 2, 8, 0, 7, 3, 6, 5};
// 一般在创建优先级队列对象时，如果知道元素个数，建议就直接将底层容量给好
// 否则在插入时需要不多的扩容
// 扩容机制：开辟更大的空间，拷贝元素，这样效率会比较低
        PriorityQueue<Integer> q = new PriorityQueue<>(arr.length);
        for (int e : arr) {
            q.offer(e);
        }
        System.out.println(q.size()); // 打印优先级队列中有效元素个数
        System.out.println(q.peek()); // 获取优先级最高的元素
        // 从优先级队列中删除两个元素之和，再次获取优先级最高的元素
        q.poll();
        q.poll();
        System.out.println(q.size()); // 打印优先级队列中有效元素个数
        System.out.println(q.peek()); // 获取优先级最高的元素
        q.offer(0);
        System.out.println(q.peek()); // 获取优先级最高的元素
// 将优先级队列中的有效元素删除掉，检测其是否为空
        q.clear();
        if (q.isEmpty()) {
            System.out.println("优先级队列已经为空!!!");
        } else {
            System.out.println("优先级队列不为空");
        }
    }
}
