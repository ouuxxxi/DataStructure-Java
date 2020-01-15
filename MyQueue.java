import java.util.Stack;
//用栈实现队列
public class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    /** Initialize your data structure here. */
    public MyQueue() {
     s1 = new Stack<>();
     s2 = new Stack<>();
    }


   //push(x) -- 将一个元素放入队列的尾部。
    public void push(int x) {
      s1.push(x);
    }

   //pop() -- 从队列首部移除元素。
    public int pop() {
        if (s2.empty()) { //如果s2为空，就把s1中的元素全部移到s2中
            while (!s1.empty()) { //s1不为空：里面还有元素
                s2.push(s1.pop());//s1中的元素移入s2
            }
        }
        return s2.pop();
    }

    //peek() -- 返回队列首部的元素。
    public int peek() {
        if (s2.empty()) {
            while (!s1.empty()) { //s1不为空：里面还有元素
                s2.push(s1.pop());//s1中的元素移入s2
            }
        }
        return s2.peek();//获取栈顶元素
    }

    //empty() -- 返回队列是否为空。
    public boolean empty() {
      return s1.empty() && s2.empty();
    }
}
