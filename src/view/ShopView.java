package view;

import controller.ShopController;
import model.dto.Card;
import model.dto.Item;
import model.dto.PlayerDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

        boolean isBuy1st = false; // 첫번째 아이템을 구매했는지
        boolean isBuy2nd = false; // 두번째 아이템을 구매했는지
        boolean isBuy3rd = false; // 세번째 아이템을 구매했는지

        int reroll = 50;
        int rand = new Random().nextInt(2)+1;
        while(true){
            String ThirdText = "";

            if(rand == 1){
                ThirdText = "[뽑기]새로운 화투패 추가(가격: 100냥)";
            }else{
                ThirdText = "[삭제]덱에서 화투패 삭제(가격: 100냥)";
            }

            System.out.println("\n=========== [ 🏚️ 만물상 (상점) ] ===========");
            System.out.printf("                             보유 엽전: %d냥\n", playerDto.getCurrent_money());


            if(isBuy1st){
                System.out.println("1. 구매 완료");
            }else{
                System.out.printf("""
            1. [%s]%s(가격: %d)
                ㄴ효과: %s""", itemlist.get(0).getType(),itemlist.get(0).getName(),itemlist.get(0).getPrice(),itemlist.get(0).getDescription());
                System.out.println();
            }
            if(isBuy2nd){
                System.out.println("2. 구매 완료");
            }else{
                System.out.printf("""
            2. [%s]%s(가격: %d)
                ㄴ효과: %s""", itemlist.get(1).getType(),itemlist.get(1).getName(),itemlist.get(1).getPrice(),itemlist.get(1).getDescription());
                System.out.println();
            }
            if(isBuy3rd){
                System.out.println("3. 구매 완료");
            }else{
                System.out.printf("3. %s\n", ThirdText);
            }

            System.out.printf("""
               4. 내 덱 확인
               5. 상점 리롤(%d)
               6. 다음라운드로
               --------------------------------------------
               >>선택:""", reroll
            ); System.out.print(" ");

            try {
                int ch = scan.nextInt();
                if(ch == 1 && !isBuy1st){
                    // 플레이어 아이템리스트에 해당 아이템 추가
                    boolean result = sc.addItem(itemlist.get(0).getItem_no(), itemlist.get(0).getPrice());
                    if(result){ System.out.printf("[안내][%s]%s이/가 추가되었습니다.\n", itemlist.get(0).getType(), itemlist.get(0).getName()); isBuy1st = true; }
                    else{ System.out.println("[경고]돈이 부족합니다."); }
                }else if(ch == 2 && !isBuy2nd){
                    // 플레이어 아이템리스트에 해당 아이템 추가
                    boolean result = sc.addItem(itemlist.get(1).getItem_no(), itemlist.get(1).getPrice());
                    if(result){ System.out.printf("[안내][%s]%s이/가 추가되었습니다.\n", itemlist.get(1).getType(), itemlist.get(1).getName()); isBuy2nd = true; }
                    else{ System.out.println("[경고]돈이 부족합니다."); }
                }else if(ch == 3 && rand == 1 && !isBuy3rd){
                    ArrayList<Card> cards = sc.getFiveCard();
                    System.out.printf("""
                    -------------------뽑기 선택-------------------
                    1. [%s]  2. [%s]  3.[%s]
                    4.[%s] 5.[%s]
                    """, cards.get(0).getName(), cards.get(1).getName(), cards.get(2).getName(), cards.get(3).getName(), cards.get(4).getName()
                    );
                    int ch2 = scan.nextInt();
                    if( ch2 >= 1 && ch2 <= 5){
                        //플레이어 덱에 해당 카드를 추가하는 알고리즘을 넣기
                        boolean result = sc.addCard( cards.get(ch2-1).getCard_no(), 100 );
                        if(result){ System.out.printf("[안내][%s]이/가 추가되었습니다.\n", cards.get(ch2-1).getName()); isBuy3rd = true; }
                        else{ System.out.println("[경고]돈이 부족합니다."); }
                    }else{
                        System.out.println("다시 입력해주세요.");
                    }
                }else if(ch == 3 && rand == 2 && !isBuy3rd){
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
                        if(result){
                            System.out.println("[안내]카드 삭제에 성공하셨습니다.");
                            isBuy3rd = true;
                        }
                        else{ System.out.println("[경고]카드 삭제에 실패하셨습니다."); }
                    }
                }else if(ch == 4){ //내 덱 확인
                    scan.nextLine();
                    ArrayList<Card> deck = player.getCard();
                    System.out.println("\n[ 내 덱 카드 (" + deck.size() + "장) ]");
                    int count = 0;
                    for(Card c : deck) {
                        System.out.print("[" + c.getName() + "] ");
                        if(++count % 6 == 0) System.out.println();
                    }
                    System.out.println("\n엔터를 치면 돌아갑니다.");
                    scan.nextLine();
                }else if(ch == 5){ //상점 새로고침(리롤)
                    if(player.getCurrent_money() >= reroll){
                        itemlist = sc.getAmFo(); // 새로운 아이템 리스트 가져오기
                        rand = new Random().nextInt(2)+1; // 추가 or 삭제 다시 랜덤하게
                        player.setCurrent_money(player.getCurrent_money() - reroll); // 50원 차감
                        reroll += 10; //다음 리롤 10원 증가
                        isBuy1st = false;
                        isBuy2nd = false;
                        isBuy3rd = false;
                    }else{
                        System.out.println("[경고]돈이 부족합니다.");
                    }
                } else if(ch == 6){
                    return; //상점 탈출
                }else{
                    System.out.println("(시스템)1~6사이의 숫자로 다시 입력해주세요.");
                }
            }catch (InputMismatchException e){
                scan.nextLine(); //버퍼 비우기
                System.out.println("(시스템)숫자만 입력해 주세요");
            }
        }

    }
}
