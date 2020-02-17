import java.util.Arrays;
// 打印顺序表     public void display() {    }    
// 在 pos 位置新增元素     public void add(int pos, int data) {  }    
// 判定是否包含某个元素     public boolean contains(int toFind) { return true; }    
// 查找某个元素对应的位置     public int search(int toFind) { return -1; }    
// 获取 pos 位置的元素     public int getPos(int pos) { return -1; }    
// 给 pos 位置的元素设为 value     public void setPos(int pos, int value) {    }    
// 删除第一次出现的关键字key     public void remove(int toRemove) {    }    
// 获取顺序表长度     public int size() { return 0; }    
// 清空顺序表     public void clear() {    } }


 // 顺序表
    class MyArrayList {
        public int usedSize;//usedSize 数组中元素的个数
        public int[] elem;

        public final int CAPACITY = 10; //数组的容量为10

        public MyArrayList() {
            this.elem = new int[CAPACITY]; //没有初始化数组，其默认值为0
            //数组new了一个新对象
        }


        //看数组是否满了；此处this.usedSize不能等于容量 ；满了就扩容
        private boolean isFull() {
            if (this.usedSize == this.elem.length) {
                return true;
            }
            return false;// 方法一
            //方法二： return this.usedSize == this.elem.length；
        }

        // 一、在pos位置新增函数
        public void add(int pos, int data) {

            //二倍扩容
            if (isFull()) {
                this.elem = Arrays.copyOf(this.elem, this.elem.length * 2);
                //（被拷贝的数组；新数组的长度）
            }

            //1、pos是否合法  pos代表下标 所插入那个位置的下标 （如果想把新增的元素放入下标为2的位置，那么pos=2）
            if ((pos < 0) || pos > this.usedSize) {
                System.out.println("pos位置不合法");
                return;  // 方法1
                // throw new IndexOutOfBoundsException("pos位置不合法"); 抛出一个异常  // 方法2
            }

            //2、挪数据
            for (int i = usedSize - 1; i >= pos; i--) { // 从最后一个开始，如果i比pos大，就需要往右移动
                elem[i + 1] = elem[i]; // 往右移动了，所以该元素赋值到下一个
            }

            //3、插入数据
            this.elem[pos] = data; //把插入元素的值放到pos位置

            // usedSize++ 数组元素个数加1

            usedSize++;
        }

        // 打印顺序表 ;
        public void display() {
            for (int i = 0; i < this.usedSize; i++) {
                System.out.print(elem[i] + " ");
            }
            System.out.println();
        }

        //二、判定是否包含某个元素
        public boolean contains(int toFind) {
            for (int i = 0; i < this.usedSize; i++) {
                if (this.elem[i] == toFind) {
                    return true;
                }
            }
            return false;
        }

        //三、查找某个元素对应的位置
        public int search(int toFind) {

        /* 判断是否为空 可以直接在search中判断

        if (isEmpty()){
          System.out.println("列表为空");
            return -1 ;

         }

         */
            for (int i = 0; i < this.usedSize; i++) {
                if (this.elem[i] == toFind) {
                    return i;
                }
            }
            return -1;
        }

        // 四、获取 pos 位置的元素    
        public int getPos(int pos) {
            if ((pos < 0) || pos > this.usedSize) {
                return -1;
            }
            return pos;
        }

        // 五、清空顺序表
        public void clear() {
            this.usedSize = 0;
        }

        //六、删除第一次出现的关键字key

        //1.判断是否为空
        private boolean isEmpty() {
            if (this.usedSize == 0) {
                return true;
            }
            return false;//方法一
            // 方法二：直接return: return this.useSize==0;
        }

        public void remove(int toRemove) {

            //2.查找删除的元素(remove的下标)

         /* 方法一
            int index = search(toRemove);
            if(index == -1){
            System.out.println("没有你要删除的数字!");
            return;
        */

            //方法二
            int temp = 0;
            if (isEmpty()) {
                System.out.println("列表为空");
                return;
            } else {
                for (int i = 0; i < this.usedSize; i++) {
                    if (this.elem[i] == toRemove) {
                        temp = i;
                    }
                }

                //3.删除
                for (int i = temp; i < this.usedSize - 1; i++) {
                    elem[i] = elem[i + 1]; //删除就是把后面的往前移动
                }
                usedSize--;
            }
        }
    }

public class SeqList  {
    public static void main(String[] args) {
        MyArrayList myArraylist = new MyArrayList();
        myArraylist.add(0,1);//插入元素
        myArraylist.add(1,2);
        myArraylist.add(2,3);
        myArraylist.add(3,2);
        myArraylist.add(4,1);
        myArraylist.display();//打印

        /* 打印第二个顺序表
         MyArrayList myarraylist2 = new MyArrayList();
        myArraylist2.add(0,1);//插入元素
        myArraylist2.add(1,2);
        myArraylist2..add(2,3);
        myArraylist2.add(3,4);
        myArraylist2.add(4,5);
        myArraylist2.display();//打印
         */

        //查找4号元素
        System.out.println(myArraylist.search(4));

        //删除2号元素，在对数组进行打印
        myArraylist.remove(2);
        myArraylist.display();
    }
}
