package controller;

import model.dto.Card;
import model.dto.Item;
import service.ShopService;

import java.util.ArrayList;

public class ShopController {
    private ShopController(){}
    private static final ShopController instance = new ShopController();
    public static ShopController getInstance(){
        return instance;
    }
    private ShopService ssv = ShopService.getInstance();

    public ArrayList<Item> getAmFo(){
        return ssv.getAmFo();
    }
    public ArrayList<Card> getFiveCard(){
        return ssv.getFiveCards();
    }
    public boolean addItem(int item_no, int price){ return ssv.addItem(item_no, price); }
    public boolean addCard(int card_no, int price){ return ssv.addCard(card_no, price); }
    public boolean removeCard(int card_no, int price){
        //!!추후에 추가 -> 만약 현재 유저의 카드리스트가 0이면 false값 반환
        return ssv.removeCard(card_no, price);
    }
    public ArrayList<Card> getPlayerCard(){
        return ssv.getPlayerCard();
    }
}
