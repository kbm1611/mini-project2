package service;

public class ShopService {
    private ShopService(){}
    private static final ShopService instance = new ShopService();
    public static ShopService getInstance(){
        return instance;
    }
}
