package controller;

import service.ShopService;

public class ShopController {
    private ShopController(){}
    private static final ShopController instance = new ShopController();
    public static ShopController getInstance(){
        return instance;
    }
    private ShopService ssv = ShopService.getInstance();
}
