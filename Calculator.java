package com.ycx.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*6-4";

        //创建两个栈：数栈 符号栈
        ArrayStack1 numStack = new ArrayStack1(10);
        ArrayStack1 operStack = new ArrayStack1(10);

        //定义相关变量
        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;//在java中 int 和 char是可以互换的 char的底层比较就是int数值的比较
        int res = 0; //计算的结果
        char ch = ' '; //将每次扫描得到的char保存到ch
        String keepNum = " "; //用于拼接多位数
        //while循环扫描expression
        while (true) {
            //①依次得到expression的每一个操作符
            ch = expression.substring(index, index + 1).charAt(0);
            //substring：依次截取字符串

            //②判断ch是什么符号
            if (operStack.isOper(ch)) {
                if (!operStack.isEmpty()) { //符号栈不为空
                    /*如果当前的操作符的优先级小于或者等于栈中的操作符，
                    就需要从数栈中pop出两个数,在从符号栈中pop出-一个符号，
                    进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    */
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //当前运算符 <= 栈顶的运算符
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //运算结果入栈
                        numStack.push(res);
                        //当前符号入栈
                        operStack.push(ch);
                    } else {
                        /*如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈*/
                        operStack.push(ch);
                    }
                } else {
                    /*如果符号栈为空，直接入栈*/
                    operStack.push(ch);
                }
            } else {
                /*如果是数，则直接入栈,这种情况只适用于个位数

                */
               // numStack.push(ch-48);//扫描到的是字符‘1’: 所以需要减去48 得到数字1
                /*
                多位数的分析思路;
                1、处理多位数时，不能发现是一个数就立即加入，因为它有可能是多位数
                2、处理时，需要向expression的表达式的index后再看一位，如果是数字，就继续扫描，如果是符号才入栈
                3、我们需要定义一个变量字符串，用于拼接
                */

             keepNum += ch;


                //如果ch已经是expression的最后一位，则直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                    //parseInt:字符转数字
                }else{

           /*判断下一个字符是不是数字*/
                    //如果是，就扫描；如果是运算符，则入栈
                    //注意看最后一位
            if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {

                //如果后一位是运算符，则入栈 keepNum = '1'或者'123'
                numStack.push(Integer.parseInt(keepNum.trim()));
                //重要：需要清空
                keepNum = "";
            }
        }
    }
        //让 index+1 ，判断是否扫描到expression最后
            index++;
            if(index >= expression.length()){ //扫描结束
                break;
            }
        }

        /*当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号,并运行*/
        while (true){
            //如果符号栈为空，则已经计算到最后的结果，此时数栈中只有一个数字
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);

            //运算结果入栈
            numStack.push(res);
        }
        //最后输出结果
        int res1 = numStack.pop();
        System.out.printf("表达式结果 %s = %d",expression,res1);
    }
}

//先创建一个栈
//定义一个ArrayStack1 表示栈
class ArrayStack1 {
    private int maxSzie; //栈的大小
    private int[] stack; //数组：数组模拟栈 （数据存放在该数组中）
    private int top = -1;//top: 栈顶 初始化为-1

    //构造器
    public ArrayStack1(int maxSzie) {
        this.maxSzie = maxSzie;
        stack = new int[this.maxSzie]; // 初始化数组
    }

    //1、判断栈满
    public boolean isFull() {
        return top == maxSzie - 1; //因为top初始值为-1
    }

    //2、判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //3、入栈 push
    public void push(int val) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = val;
    }

    //4、出栈 pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空"); // 不用写return 因为抛出异常之后自动return
        }
        int val = stack[top];
        top--;
        return val;
    }

    //5、显示栈（遍历栈）: 需要从栈顶开始遍历
    public void print(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }

        //从栈顶开始
        for (int i = top; i>=0; i--) {
            System.out.printf("stack[%d]=%d\n", i , stack[i]);
        }
    }


    //6、判断符号的优先级（优先级由我们自己决定）
    //可用数字表示优先级：数字越大，优先级越高

    public int priority(int oper){ //在java中 int 和 char是可以互换的 char的底层比较就是int数值的比较
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;//假设当前符号只有 + - * /
        }
    }

    //7、判断是不是一个运算符
    public  boolean isOper(int val){
      if(val == '+' || val =='-' || val == '*' || val == '/') {
          return true;
      }
      return false;
    }


    //8、计算
    public  int cal(int num1,int num2, int oper){
        int res = 0;// res ：存放计算结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 - num1;
                break;
            default:
                break;
        }
        return res;
    }

    //9、增加一个方法，可以返回当前栈顶的值（不是pop）
   public int peek(){
        return stack[top];
   }
}