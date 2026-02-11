package view;

import controller.ShopController;

import java.util.Scanner;

public class ShopView {
    private ShopView(){}
    private static final ShopView instance = new ShopView();
    public static ShopView getInstance(){
        return instance;
    }
    private ShopController sc = ShopController.getInstance();
    private Scanner scan = new Scanner(System.in);
}
