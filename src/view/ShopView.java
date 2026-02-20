package view;

import controller.ShopController;
import model.dto.Card;
import model.dto.Item;
import model.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ShopView {
    private ShopView(){}
    private static final ShopView instance = new ShopView();
    public static ShopView getInstance(){
        return instance;
    }
    private ShopController sc = ShopController.getInstance();
    private PlayerDto player = PlayerDto.getInstance();
    private Scanner scan = new Scanner(System.in);

    public void printShopView(){
        ArrayList<Item> itemlist = sc.getAmFo();
        PlayerDto playerDto = PlayerDto.getInstance(); //로그인한 유저의 정보 객체

        String ThirdText = "";
        int rand = new Random().nextInt(2)+1;
        if(rand == 1){
            ThirdText = "[뽑기]새로운 화투패 추가(가격: 100냥)";
        }else{
            ThirdText = "[삭제]덱에서 화투패 삭제(가격: 100냥)";
        }


        System.out.println("========== [ 🏚️ 만물상 (상점) ] ==========");
        System.out.printf("보유 엽전: %d냥\n", playerDto.getCurrent_money());


        System.out.printf("""
               1. [%s]%s(가격: %d)
                  ㄴ효과: %s
               2. [%s]%s(가격: %d)
                  ㄴ효과: %s
               3. %s
               ---------------------------------------------
               >>선택:"""
                , itemlist.get(0).getType(),itemlist.get(0).getName(),itemlist.get(0).getPrice(),itemlist.get(0).getDescription()
                , itemlist.get(1).getType(),itemlist.get(1).getName(),itemlist.get(1).getPrice(),itemlist.get(1).getDescription(),
                ThirdText
        ); System.out.print(" ");

        int ch = scan.nextInt();
        if(ch == 1){
            // 플레이어 아이템리스트에 해당 아이템 추가
            boolean result = sc.addItem(itemlist.get(0).getItem_no(), itemlist.get(0).getPrice());
            if(result){ System.out.printf("[안내][%s]%s이/가 추가되었습니다.\n", itemlist.get(0).getType(), itemlist.get(0).getName()); }
            else{ System.out.println("[경고]돈이 부족합니다."); }
        }else if(ch == 2){
            // 플레이어 아이템리스트에 해당 아이템 추가
            boolean result = sc.addItem(itemlist.get(1).getItem_no(), itemlist.get(1).getPrice());
            if(result){ System.out.printf("[안내][%s]%s이/가 추가되었습니다.\n", itemlist.get(1).getType(), itemlist.get(1).getName()); }
            else{ System.out.println("[경고]돈이 부족합니다."); }
        }else if(ch == 3 && rand == 1){
            ArrayList<Card> cards = sc.getFiveCard();
            System.out.printf("""
                    ---------------------------뽑기 선택---------------------
                    1. [%s]  2. [%s]  3.[%s] 4.[%s] 5.[%s]
                    """, cards.get(0).getName(), cards.get(1).getName(), cards.get(2).getName(), cards.get(3).getName(), cards.get(4).getName()
            );
            int ch2 = scan.nextInt();
            if( ch2 >= 1 && ch2 <= 5){
                //플레이어 덱에 해당 카드를 추가하는 알고리즘을 넣기
                boolean result = sc.addCard( cards.get(ch2-1).getCard_no(), 100 );
                if(result){ System.out.printf("[안내][%s]이/가 추가되었습니다.\n", cards.get(ch2-1).getName()); }
                else{ System.out.println("[경고]돈이 부족합니다."); }
            }
        }else if(ch == 3 && rand == 2){
            scan.nextLine();
            ArrayList<Card> cards = player.getCard(); //현재 플레이어의 카드 덱

            boolean result = false;
            while(!result){ //삭제할 때까지 무한반복 -1 입력시 탈출
                System.out.print("삭제할 카드명을 정확하게 입력하세요(되돌아가기 -1): "); String removeCard = scan.nextLine();
                if(removeCard.equals("-1")){ // -1입력시 탈출
                    break;
                }
                for(Card card : cards){
                    if(removeCard.equals(card.getName())){
                        result = sc.removeCard(card.getCard_no(), 100);
                        break;
                    }
                }
                if(result){ System.out.println("[안내]카드 삭제에 성공하셨습니다."); }
                else{ System.out.println("[경고]카드 삭제에 실패하셨습니다."); }
            }
        }

    }
}
