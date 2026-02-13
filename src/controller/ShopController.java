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
}
