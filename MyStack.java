import java.util.LinkedList;
import java.util.Queue;

//用队列实现栈

public class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public MyStack() {
     q1 = new LinkedList<>();
     q2 = new LinkedList<>();
    }

    //元素 x 入栈
    public void push(int x) {
      q1.offer(x);
    }

   //移除栈顶元素
    public int pop() {
        //1、将q1中的size-1个元素搬到q2中
        while(q1.size() > 1){
            q2.offer(q1.poll()); // q1中的siza-1个元素出队列 然后在进队列q2中
        }

        //2、删除q1中的队头元素
        int ret=q1.poll();

        //3、交换q1 q2
        Queue<Integer> temp= q1;
        q1=q2;
        q2=temp;

        return  ret;
    }

    //获取栈顶元素
    public int top() {
        //1、将q1中的size-1个元素搬到q2中
        while(q1.size() > 1){
            q2.offer(q1.poll()); // q1中的siza-1个元素出队列 然后在进队列q2中
        }
        //2、获取q1中的队头元素
        int ret=q1.peek();

        q2.offer(q1.poll());

        //3、交换q1 q2
        Queue<Integer> temp= q1;
        q1=q2;
        q2=temp;
        return  ret;
    }

    //返回栈是否为空
    public boolean empty() {
      return q1.isEmpty();
    }
}
