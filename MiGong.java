package com.ycx.recursion;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组：模拟迷宫
        int[][] map = new int[8][7];

        //使用数字1表示墙
        for (int i = 0; i <7 ; i++) {
            //第一行和第八行全部置为1
            map[0][i]=1;
            map[7][i]=1;
        }
        for (int i = 0; i <8 ; i++) {
           //第一列和第七列全部置为1
            map[i][0]=1;
            map[i][6]=1;
        }

        //设置挡板 也用1表示
        map[3][1]=1;
        map[3][2]=1;

        //输出
        System.out.println();
        System.out.println("初始迷宫为：");
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map,1,1); //从（1，1）位置开始
        System.out.println("小球走过的迷宫图为：");
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        /*
        1、map 表示迷宫；
        2、i、j 表示从迷宫的哪个位置开始出发（假设从1，1出发）；
        3、如果小球能到 map[6] [5] 的位置，说明小球已找到通路。
        4、设置：
                当 map[i] [j] =0 ：表示该点没有走过；
                当 map[i] [j] =1 ：表示墙和挡板；
                当 map[i] [j] =2 ：表示该点可以走；
                当 map[i] [j] =3：表示该点可以走，但走不通；
        5、小球走迷宫时，需要确定走的方向，假设为 下 → 右 → 上 → 左，如果该点走不通，再回溯。
       */
    }

        public static boolean setWay(int[][] map, int i, int j){
            if(map[6][5]==2){
                return true; //通路已经找到
            }else {
                if(map[i][j]==0){  //如果这个点没有走过
                    //按照下 → 右 → 上 → 左

                    map[i][j]=2; //假设这个点可以走

                    if(setWay(map,i+1,j)){ //向下走
                        return true;
                    }else  if(setWay(map,i,j+1)){ //向右走
                        return true;
                    }else  if(setWay(map,i-1,j)){ //向上走
                        return true;
                    }else  if(setWay(map,i,j-1)){ //向左走
                        return true;
                    }else {    //这个点走不通
                        map[i][j]=3;
                        return false;
                    }
                }else{  //map[i][j]!=0 ,那么可能为1，2，3
                    return false;
                }
            }

    }
}
