package com.ycx.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //先定义一个逆波兰表达式：(3+4)*5-6  =>  3 4 + 5 * 6 -
        //测试
        // 4 * 5 - 8 + 60 + 8 / 2=> 4 5 * 8 - 60 + 8 2 /
        //为了方便，逆波兰表达式的数字和符号使用空格隔开


         String expression = "3 4 + 5 * 6 -";

        /*
            思路：
               1、将"3 4 + 5 * 6 -" 放到ArrayList中
               2、将ArrayList传递给一个方法，遍历ArrayList，配合栈完成计算
         */

        List<String> list1 = getListString(expression);//放在List中方便取数，如果用index索引 需要一个一个的扫描,比较麻烦
        System.out.println("list1 =" + list1);

        int result1 = calculate(list1);
        System.out.println("计算结果是："+ result1);
    }

    //将一个逆波兰表达式，依次将数据和运算符 放到ArrayList中
    public static List<String> getListString(String expression){
        //将expression用空格分割
        String[] split = expression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split){ //对split进行for循环 每循环一次就取出波兰表达式中的一个元素
            list.add(ele);
        }
        return list;
    }

    /*
       完成逆波兰表达式的步骤：
      1) 从左至右扫描，将3和4压入堆栈;
      2) 遇到+运算符， 因此弹出4和3 (4为栈顶元素，3为次顶元素)，计算出3+4的值，得7，再将7入栈;
      3) 将5入栈;
      4) 接下来是X运算符，因此弹出5和7，计算出7X5=35，将35入栈;
      5) 将6入栈;
      6) 最后是-运算符，计算出35-6的值， 即29，由此得出最终结果
    */

    public  static int calculate(List<String> list2){
        //创建栈
        Stack<String> stack = new Stack<String>();
        //遍历list2
        for (String ele1:list2){
            if(ele1.matches("\\d+")){ //正则表达式：匹配多位数
                //如果是数就入栈
                stack.push(ele1);
            }else {   //如果是字符 就pop出两个数 进行运算
                int num1 = Integer.parseInt(stack.pop()); //stack.pop() 出来的是字符 需要转化成数字
                int num2 = Integer.parseInt(stack.pop());//num2 比 num1 后 pop出 所以注意减法和除法时的顺序
                int result = 0;
                if(ele1.equals("+")){
                    result = num1 + num2;
                }else if(ele1.equals("-")){
                    result = num2 - num1;
                }else if(ele1.equals("*")){
                    result = num1 * num2;
                }else if(ele1.equals("/")){
                    result = num2 / num1;
                }else {
                    throw  new RuntimeException("运算符有误");
                }
                //结果入栈
                stack.push(result+"");//整数转化为字符串
            }
        }
        //最后留在stack中的是运算结果
        return Integer.parseInt(stack.pop());
    }
}

