package service;

import constant.GameConst;
import model.dao.ItemDao;
import model.dto.Card;
import model.dto.Item;

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
}
