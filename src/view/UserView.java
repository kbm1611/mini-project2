package view;

import controller.UserController;

public class UserView {
    private UserView(){}
    private static final UserView instance = new UserView();
    public static UserView getInstance(){return instance;}

    private UserController uc = UserController.getInstance();


}
