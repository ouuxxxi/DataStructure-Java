import java.util.List;
import java.util.ArrayList;
import java.util.Random;

//牌的结构
class Card {

     Card(String suit,int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        String strNum = String.format("%d", rank);

        switch (rank){
            case 11:
                strNum="J";
                break;
            case 12:
                strNum="Q";
                break;
            case 13:
                strNum="K";
                break;
        }
        return suit+":"+strNum;
    }
    private String suit;//牌的花色
    private int rank;//牌的大小

}


class CardDemo {
    String color[]={"♥","♦","♠","♣"};

    //买一副牌
    public List<Card> buyCard(){
        List<Card> cards = new ArrayList<>();

        //花色和牌大小
        for(int i=0;i<4;i++){
            for (int j = 1; j <=13 ; j++) {
                Card c = new Card(color[i],j);//一张牌
                cards.add(c);
            }
        }
        return cards;
    }

    //洗牌
    public void shuffle(List<Card> cards){
        //从最后一张牌往前（避免漏掉牌）
        for (int i = cards.size()-1; i >0 ; i--) {

            //随机从[0,i]中取一张牌
            //常见设置随机种子方式：获取系统当前时间
            Random A=new Random(20191226);
            int j =A.nextInt(i);

            // 交换i,j的位置
            swap(cards,i,j);
        }
    }

    private void swap(List<Card> cards, int i, int j){ //i,j代表的是下标
        Card tmp = cards.get(i);//设置临时变量tmp 存放i的值
        cards.set(i,cards.get(j));//将j的值放到i的位置上
        cards.set(j,tmp);
    }
}
public class CardTest {
    public static void main(String[] args) {
        CardDemo CD = new CardDemo();

        //构造一张牌
        List<Card> C=CD.buyCard();
        //打印牌
        System.out.println(C);
        //洗牌
        CD.shuffle(C);
        System.out.println(C);


        //三个人---每人依次抓5张牌

        //先建立三个人
        List<List<Card>> Person =new ArrayList<>();

        for(int i = 0;i<3;i++){
            Person.add(new ArrayList<>());
        }

        for(int i=0;i<5;i++){
            //三个人依次摸牌
            for (int j = 0; j < 3 ; j++) {
                //从牌C中依次取出一张牌
                Card y=C.remove(C.size()-1);

                //将这张牌给人
                Person.get(j).add(y);
            }
        }
        //打印每个人手中的牌
        for (int i = 0; i <=Person.size() ; i++) {
            System.out.println(Person.get(i));
        }

        //打印剩下的牌
        System.out.println(C.size());
        System.out.println(C);
    }
}
