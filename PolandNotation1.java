package com.ycx.stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation1 {
    public static void main(String[] args) {
        /*
        完成中缀表达式转后缀表达式
        1、1+(2+3)*4-5 => 1 2 3 + 4 * 5 -
        2、直接对str操作，不方便；所以将" 1+(2+3)*4-5" 转成中缀的List
            " 1+(2+3)*4-5"  => ArrayList[1, +, (, 2, +, 3, ), *, 4, -, 5]
       3、将中缀表达式对应的List => 后缀表达式对应的List
             ArrayList[1, +, (, 2, +, 3, ), *, 4, -, 5] =>ArrayList[1, 2, 3, +, 4, *, 5, -]
        */

        String expression = "1+(2+3)*4-5 ";
        List<String> expression1 = expressionList(expression);
        System.out.println("中缀表达式："+ expression1); // [1, +, (, 2, +, 3, ), *, 4, -, 5]

        List<String> expression2 = expressionList1(expression1);
        System.out.println("后缀表达式："+ expression2);
    }


    /* 将中缀表达式转成对应的List  s = "1+(2+3)*4-5" */

        public static List<String> expressionList (String s){
            //定义一个List，存放中缀表达式对应的内容
            List<String> list = new ArrayList<>();
            int i = 0;  //指针：遍历中缀表达式字符串
            String str; //多位数的拼接
            char c; //每遍历到一个字符，就放入c
            do {
                //如果c是一个非数字，需要加入到list
                if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {   // '0' → 48    '9' → 57
                    list.add(c + "");
                    i++; // i后移
                } else {  //否则c是一个数（需要考虑多位数）
                    str = ""; //将str置空
                    while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                        str += c;//拼接
                        i++;
                    }
                    list.add(str);
                }
            } while (i < s.length());
            return list;
        }




     /* 1) 初始化两个栈:运算符栈s1和储存中间结果的栈s2;
        2) 从左至右扫描中缀表达式;
        3) 遇到操作数时，将其压s2;
        4) 遇到运算符时，比较其与s1栈顶运算符的优先级:
            1.如果s1为空，或栈顶运算符为左括号“("，则直接将此运算符入栈;
            2.否则，若优先级比栈顶运算符的高，也将运算符压入s1;
            3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到1与s1中新的栈顶运算符相比较;
        5) 遇到括号时:
            1.如果是左括号“(”，则直接压入s1
            2.如果是右括号“)"，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
        6) 重复步骤2至5，直到表达式的最右边
        7) 将s1中剩余的运算符依次弹出并压入s2
        8) 依次弹出s2中的元素并输出，结果的逆脚为中缀表达式对应的后缀表达式 */

    // ArrayList[1, +, (, 2, +, 3, ), *, 4, -, 5] =>ArrayList[1, 2, 3, +, 4, *, 5, -]
    //将中缀表达式对应的List => 后缀表达式对应的List

    public static List<String> expressionList1(List<String> list){
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        /*
        说明：
            s2这个栈在整个转换过程中，没有出栈操作，而且最后还需要逆序输出，比较麻烦，
        所以我们不用Stack<String> ，直接用List<String>；
       */
        List<String> s2 = new ArrayList<String>();

        //遍历list
        for(String ele:list){
            if(ele.matches("\\d+")){  //如果是一个数字
                s2.add(ele);
            }else if(ele.equals("(")){
                s1.push(ele);
            }else if(ele.equals(")")){  //如果是右括号“)"
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());//依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止
                }
                s1.pop();//弹出"("     将这一对括号丢弃
            }else {  //如果是运算符
                /*当ele的优先级小于栈顶操作符时：*/
                while (s1.size()!=0 && Operation.getValue(ele) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop()); // 将s1栈顶的运算符弹出并压入到s2中,再次转到1与s1中新的栈顶运算符相比较
                }
                s1.push(ele);//再将ele压入栈
            }
        }
        //将s1中剩余运算符依次弹出并加入s2中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }
}

//编写一个类：比较符号的优先级（数字大的优先级高）
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    //写一个方法返回
    public static int getValue(String ss){
        int result = 0 ;
        switch (ss){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                result=0;
                break;
        }
        return result;
    }
}


