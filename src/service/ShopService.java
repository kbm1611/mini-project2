package service;

import constant.GameConst;
import model.dao.ItemDao;
import model.dto.Card;
import model.dto.Item;
import model.dto.PlayerDto;

import java.util.ArrayList;
import java.util.Random;

public class ShopService {
    private ShopService(){}
    private static final ShopService instance = new ShopService();
    public static ShopService getInstance(){ return instance; }
    private ItemDao itd = ItemDao.getInstance();

    ArrayList<Card> cards = GameConst.BASIC_DECK;

    //랜덤한 부적과 점괘 1개씩 반환
    public ArrayList<Item> getAmFo(){
        ArrayList<Item> itemlist = new ArrayList<>();
        //부적1개, 점괘1개 각각 뽑기
        ArrayList<Item> amulets = itd.getAmulet();
        ArrayList<Item> fortunes = itd.getFortune();
        int rand1 = new Random().nextInt(amulets.size());
        int rand2 = new Random().nextInt(fortunes.size());

        itemlist.add(amulets.get(rand1));
        itemlist.add(fortunes.get(rand2));
        return itemlist;
    }

    //랜덤한 5개의 카드 반환
    public ArrayList<Card> getFiveCards(){
        ArrayList<Card> cardlist = new ArrayList<>();
        //랜덤한 5장의 카드 뽑아서 넣기
        for(int i = 1; i <= 5; i++){
            int rand = new Random().nextInt(48)+1;
            Card card = cards.get(rand);
            cardlist.add(card);
        }
        return cardlist;
    }

    //현재 플레이어의 정보를 담은 싱글톤 객체
    PlayerDto player = PlayerDto.getInstance();

    // 플레이어의 아이템 리스트에 아이템을 추가하는 함수(플레이어의 잔고랑 비교)
    public boolean addItem(int item_no, int price){
        Item item = itd.getItem(item_no);
        //현재 플레이어의 아이템 리스트를 변경해야함.
        //현재 플레이어의 잔고 변경해야함
        if(player.getCurrent_monney() >= price){
            int money = player.getCurrent_monney();
            player.getItem().add(item);
            player.setCurrent_monney(money - price);
            return true;
        }else{
            return false;
        }
    }
    // 플레이어의 카드 리스트에 카드를 추가하는 함수(플레이어의 잔고랑 비교)
    public boolean addCard(int card_no, int price){
        Card card = cards.get(card_no-1);
        //현재 플레이어의 카드 리스트를 변경해야함.
        //현재 플레이어의 잔고 변경해야함
        if(player.getCurrent_monney() >= price){
            int money = player.getCurrent_monney();
            player.getCard().add(card);
            player.setCurrent_monney(money - price);
            return true;
        }else{
            return false;
        }
    }
    // 플레이어의 카드 리스트에 카드를 삭제하는 함수(플레이어의 잔고랑 비교)
    public boolean removeCard(int card_no, int price){
        Card card = cards.get(card_no-1);
        //현재 플레이어의 카드 리스트를 변경해야함.
        //현재 플레이어의 잔고 변경해야함
        if(player.getCurrent_monney() >= price){
            int money = player.getCurrent_monney();
            player.getCard().add(card);
            player.setCurrent_monney(money - price);
            return true;
        }else{
            return false;
        }
    }

}
