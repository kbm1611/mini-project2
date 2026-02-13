package view;

import controller.ShopController;
import model.dto.Card;
import model.dto.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class ShopView {
    private ShopView(){}
    private static final ShopView instance = new ShopView();
    public static ShopView getInstance(){
        return instance;
    }
    private ShopController sc = ShopController.getInstance();
    private Scanner scan = new Scanner(System.in);

    public void printShopView(){
        ArrayList<Item> itemlist = sc.getAmFo();
        System.out.println("========== [ 🏚️ 만물상 (상점) ] ==========");
        System.out.printf("보유 엽전: %d냥", 500);

        System.out.printf("""
               1. [%s]%s(가격: %d)
                  ㄴ효과: %s
               2. [%s]%s(가격: %d)
                  ㄴ효과: %s
               3. [뽑기]새로운 화투패 추가(가격: 100냥)
               ---------------------------------------------
               >>선택: 
               """, itemlist.get(0).getType(),itemlist.get(0).getName(),itemlist.get(0).getPrice(),itemlist.get(0).getDescription()
        , itemlist.get(1).getType(),itemlist.get(1).getName(),itemlist.get(1).getPrice(),itemlist.get(1).getDescription()
        ); //!!추후에 추가
        int ch = scan.nextInt();
        if(ch == 1){
            // 플레이어 아이템리스트에 해당 아이템 추가
            boolean result = sc.addItem(itemlist.get(0).getItem_no(), itemlist.get(0).getPrice());
            if(result){ System.out.printf("[안내][%s]%s가 추가되었습니다.", itemlist.get(0).getType(), itemlist.get(0).getName()); }
            else{ System.out.println("[경고]아이템 추가에 실패하셨습니다."); }
        }else if(ch == 2){
            // 플레이어 아이템리스트에 해당 아이템 추가
            boolean result = sc.addItem(itemlist.get(1).getItem_no(), itemlist.get(0).getPrice());
            if(result){ System.out.printf("[안내][%s]%s가 추가되었습니다.", itemlist.get(1).getType(), itemlist.get(1).getName()); }
            else{ System.out.println("[경고]아이템 추가에 실패하셨습니다."); }
        }else if(ch == 3){
            ArrayList<Card> cards = sc.getFiveCard();
            System.out.printf("""
                    ---------------------------뽑기 선택---------------------
                    1. [%s]  2. [%s]  3.[%s] 4.[%s] 5.[%s]
                    """, cards.get(0).getName(), cards.get(1).getName(), cards.get(2).getName(), cards.get(3).getName(), cards.get(4).getName()
            );
            int ch2 = scan.nextInt();
            if( ch2 >= 1 && ch2 <= 5){
                //플레이어 덱에 해당 카드를 추가하는 알고리즘을 넣기
                boolean result = sc.addCard( cards.get(ch2).getCard_no(), 100 );
                System.out.printf("%s가 추가되었습니다.", "수정");
            }
        }

    }
}
