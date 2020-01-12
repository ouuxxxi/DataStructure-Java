import java.util.Scanner;
import java.util.Stack;

public class Practice17 {
    public static boolean isValid(String s) {
        Stack<Character> ss = new Stack();
        for(int i = 0 ;i<s.length();i++){
            char c = s.charAt(i);

            //左括号入栈
            if(c =='(' || c=='{' || c=='['){
                ss.push(c);
            }
            //c是右括号
            //检测是否为空：空：右括号比左括号多(匹配不成功)；非空：匹配成功
            else{
                if(ss.empty()){
                    return false;
                }
                //查看栈顶元素
                char cLeft=ss.peek();

                //检测c的右括号与左括号（cLeft）匹配释放
                if(cLeft == '(' && c == ')' ||
                        cLeft =='{' && c == '}' ||
                        cLeft =='[' && c == ']'){
                    ss.pop();//出栈
                }else{
                    //左括号次序匹配有问题
                    return false;
                }
            }
        }
        if(!ss.empty()){
            return false;
        }
       return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        boolean flag=isValid(s);
        System.out.println(flag);
    }
}
