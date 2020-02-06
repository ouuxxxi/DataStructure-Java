package com.ycx.sparsearry;
//稀疏数组的应用
public class SparseArr {

    public static void main(String[] args) {
         //创建一个原始的数组 11*11
        //  0：表示没有棋子 1：表示黑色棋子 2：表示蓝色棋子
        int[][] chessArr= new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        //输出原始的二维数组
        System.out.println("原始二维数组：");
        for (int[] row : chessArr) { //遍历出来先是一维数组 取到了每一行
            for (int data :row) {  //取到了每一个数据
                System.out.printf("%d\t",data);
                //格式化输出 注意是printf
            }
            System.out.println();
        }


        //一、将二维数组 转 稀疏数组

        //1.遍历二维数组 得到非0数据的个数
        int sum=0;
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j <11 ; j++) {
                if(chessArr[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);

        //2.创建对应的稀疏数组
        int[][] sparesArr = new int[sum+1][3];

        //给稀疏数组赋值
        sparesArr[0][0]=11; //第1行第1列：原二维数组的行数
        sparesArr[0][1]=11;//第1行第2列：原二维数组的行数
        sparesArr[0][2]=sum;//第1行第3列：不同值总个数

        //遍历二维数组，将非0的数存到稀疏数组中

        int count=0; // 记录出现的第几个非0个数
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j <11 ; j++) {
                if(chessArr[i][j]!=0){ //非0数据  稀疏数组有3列： 行 列 值（如果不理解，就看笔记上的图）
                    count++;
                    sparesArr[count][0]=i; //它在稀疏数组中的第1列是在原二维数组的行 ：所以是i
                    sparesArr[count][1]=j;//第2列是在原二维数组的列 ：所以是j
                    sparesArr[count][2]=chessArr[i][j];//第三列为这个值本身
                }
            }
        }

        // 输出稀疏数组形式
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i <sparesArr.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparesArr[i][0],sparesArr[i][1],sparesArr[i][2]);
            //格式化输出 所在行的第1、2、3列
        }
        System.out.println();



        //二、将稀疏数组恢复成二维数组


        /*
         1.先读取二维数组的第一行数据，根据第一行数据，创建原始的二维数组，比如 chessArr2=int[11][11];
         2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可。
        */


        //1.先读取二维数组的第一行数据(原二维数组的行和列)
        int r=sparesArr[0][0];
        int c=sparesArr[0][1];
        int[][] chessArr2 = new int[r][c]; //创建原始的二维数组

      //2.在读取稀疏数组后几行的数据，并赋给原始的二维数组

       //因为第一行是原数组的行列及有效数据的个数，所以从第二行开始，即i=1
        for (int i = 1; i <sparesArr.length ; i++) {

            int r1=sparesArr[i][0];//稀疏数组第1列为有效数据在原二维数组中的行
            int r2=sparesArr[i][1];//稀疏数组第2列为有效数据在原二维数组中的列
            chessArr2[r1][r2]=sparesArr[i][2];//稀疏数组第3列为有效数据的值

        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组为：");
        for (int[] row : chessArr) {
            for (int data :row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
