import java.util.Stack;

public class MinStack {
    private Stack<Integer> dataStack;//存放数据
    private Stack<Integer> minStack;//存放最小值

    public MinStack() {
     dataStack = new Stack();
     minStack = new Stack();
    }

    //将元素 x 推入栈中
    public void push(int x) {
     dataStack.push(x);//数据入栈
     if(minStack.empty()|| x<=dataStack.peek()){ //如果最小栈为空||x小于数据栈中的最小值，就进入最小栈中
         minStack.push(x);
     }
    }

    //删除栈顶的元素
    public void pop() {
     if(dataStack.peek().equals(minStack.peek())){
         minStack.pop();
         dataStack.pop();
     }
     dataStack.pop();
    }

    // 获取栈顶元素
    public int top() {
        return dataStack.peek();

    }


    //检索栈中的最小元素
    public int getMin() {
      return minStack.peek();
    }
}
