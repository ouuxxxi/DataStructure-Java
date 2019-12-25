import java.util.LinkedList;
import java.util.List;

public class LinkList {
    public static void main(String[] args) {
        List<Integer> L= new LinkedList<>();
        //尾插
        L.add(1);
        L.add(2);
        L.add(3);
        L.add(4);
        L.add(5);
        System.out.println(L);
        System.out.println(L.size());//长度

        //任意位置插入
        L.add(0,0);//将0插入到0号位置
        System.out.println(L);

        //删除0号位置的元素
        L.remove(0);
        System.out.println(L);

        //获取任意位置的元素
        System.out.println(L.get(3));//3号位置的元素
        // System.out.println(L.get(10));数组下标越界

        //将下标 index 位置元素设置为 element
        L.set(2,66);
        System.out.println(L);

        //判断元素是否在线性表中
        if(L.contains(0)){
            System.out.println("删除失败");
        }else{
            System.out.println("删除成功");
        }

        //返回下标
        L.add(0,5);
        System.out.println(L.indexOf(5));//返回第一个 5 所在下标
        System.out.println(L.lastIndexOf(5));//返回最后一个 5 的下标

        //截取 左闭右开 [2, 4)
        List<Integer> arr = L.subList(2,4);
        System.out.println(arr);

        //重新构造
        List<Integer> L1 = new java.util.LinkedList<>(L);
        System.out.println(L1);


    }
}
